package de.affenherzog.knockbackffa.player;

import de.affenherzog.knockbackffa.database.DBConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public class PlayerRepositoryImpl implements PlayerRepository {

  @Getter
  private static final PlayerRepositoryImpl INSTANCE = new PlayerRepositoryImpl();

  private PlayerRepositoryImpl() {

  }

  @Override
  public CompletableFuture<Void> update(@NotNull KffaPlayer kffaPlayer) {
    final PlayerStats stats = kffaPlayer.getPlayerStats();
    final UUID uuid = kffaPlayer.player.getUniqueId();

    return CompletableFuture.runAsync(() -> {
      updatePlayerStats(stats, uuid);
      updatePlayer(kffaPlayer, uuid);
    });
  }

  private void updatePlayer(@NotNull KffaPlayer player, @NotNull UUID uuid) {
    try (var stmt = DBConnector.getINSTANCE().getConnection().prepareStatement(
        "update kffa.player "
            + "set name = ? "
            + "where uuid = ?;")
    ) {

      stmt.setString(1, player.player.getName());
      stmt.setString(2, uuid.toString());

      stmt.executeUpdate();

    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private void updatePlayerStats(@NotNull PlayerStats stats, @NotNull UUID uuid) {
    try (var stmt = DBConnector.getINSTANCE().getConnection().prepareStatement(
        "UPDATE player_stats "
            + "INNER JOIN player p ON player_stats.id = p.player_stats_fk "
            + "SET kills = ?, deaths = ?, money = ?, rank_points = ? "
            + "WHERE uuid = ?;")
    ) {

      stmt.setInt(1, stats.getKills());
      stmt.setInt(2, stats.getDeaths());
      stmt.setInt(3, stats.getMoney());
      stmt.setInt(4, stats.getRankPoints());

      stmt.setString(5, uuid.toString());

      stmt.executeUpdate();


    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public CompletableFuture<Void> insert(@NotNull KffaPlayer kffaPlayer) {
    final PlayerStats stats = kffaPlayer.getPlayerStats();

    return CompletableFuture.runAsync(() -> {
      try (var con = DBConnector.getINSTANCE().getConnection()) {
        con.setAutoCommit(false);

        try (var player_stats_stmt = con.prepareStatement(
            "INSERT INTO player_stats (kills, deaths, money, rank_points) "
                + "VALUES (?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS)
        ) {

          player_stats_stmt.setInt(1, stats.getKills());
          player_stats_stmt.setInt(2, stats.getDeaths());
          player_stats_stmt.setInt(3, stats.getMoney());
          player_stats_stmt.setInt(4, stats.getRankPoints());

          player_stats_stmt.executeUpdate();

          final ResultSet rs = player_stats_stmt.getGeneratedKeys();
          int lastId = 0;
          if (rs.next()) {
            lastId = rs.getInt(1);
          }

          try (var player_stmt = con.prepareStatement(
              "INSERT INTO player (uuid, name, player_stats_fk) "
                  + "VALUES (?, ?, ?)")
          ) {

            player_stmt.setString(1, kffaPlayer.player.getUniqueId().toString());
            player_stmt.setString(2, kffaPlayer.player.getName());
            player_stmt.setInt(3, lastId);

            player_stmt.executeUpdate();
          }

          con.commit();
        }
        catch (SQLException e) {
          con.rollback();
          throw e;
        }
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
  }


  @Override
  public CompletableFuture<Optional<PlayerStats>> findByUUID(@NotNull UUID uuid) {
    return CompletableFuture.supplyAsync(() -> {

      try (var stmt = DBConnector.getINSTANCE().getConnection().prepareStatement(
          "SELECT ps.kills, ps.deaths, ps.money, ps.rank_points FROM player "
              + "INNER JOIN player_stats ps ON player.player_stats_fk = ps.id "
              + "WHERE player.uuid = ?")
      ) {

        stmt.setString(1, uuid.toString());

        final ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
          return Optional.of(
              new PlayerStats(
                  rs.getInt(1),
                  rs.getInt(2),
                  rs.getInt(3),
                  rs.getInt(4)
              )
          );
        }

        return Optional.empty();

      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
  }

}
