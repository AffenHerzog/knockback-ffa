package de.affenherzog.knockbackffa.player;

import de.affenherzog.knockbackffa.Kffa;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

@Getter
public class PlayerStats {

  public static final PlayerStats FIRST_JOIN_PLAYER_STATS = fetchFirstJoinPlayerStats();

  private int kills, deaths, money, rankPoints;

  public void increaseKills() {
    this.kills ++;
    updateRankPoints(20);
  }

  public void increaseDeaths() {
    this.deaths ++;
    updateRankPoints(-10);
  }

  public boolean updateMoney(int money) {
    if (this.money + money > 0) {
      this.money = this.money + money;
      return true;
    }
    return false;
  }

  private void updateRankPoints(int rankPoints) {
    this.rankPoints = this.rankPoints + rankPoints;

    if (this.rankPoints < 0) {
      this.rankPoints = 0;
    }
  }

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
