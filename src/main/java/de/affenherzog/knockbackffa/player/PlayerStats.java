package de.affenherzog.knockbackffa.player;

import de.affenherzog.knockbackffa.Kffa;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class PlayerStats {

  public static final PlayerStats FIRST_JOIN_PLAYER_STATS = fetchFirstJoinPlayerStats();

  @Getter @Setter
  private int kills, deaths, money, rankPoints;

  private static @NotNull PlayerStats fetchFirstJoinPlayerStats() {
    final FileConfiguration config = Kffa.getInstance().getConfig();
    final String path = "player-first-join-values.";

    return new PlayerStats(
        config.getInt(path + "kills"),
        config.getInt(path + "deaths"),
        config.getInt(path + "money"),
        config.getInt(path + "rankPoints"));
  }

  public PlayerStats(int kills, int deaths, int money, int rankPoints) {
    this.kills = kills;
    this.deaths = deaths;
    this.money = money;
    this.rankPoints = rankPoints;
  }

}
