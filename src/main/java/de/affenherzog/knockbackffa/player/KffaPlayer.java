package de.affenherzog.knockbackffa.player;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.game.Game;
import de.affenherzog.knockbackffa.util.LastHitTracker;
import lombok.Getter;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;

public class KffaPlayer {

  @Getter
  final Player player;
  final Game game;

  @Getter
  private final PlayerStats playerStats;

  @Getter
  private final LastHitTracker lastHitTracker;

  public KffaPlayer(Player player, PlayerStats playerStats) {
    this.player = player;
    this.playerStats = playerStats;
    this.game = Kffa.getInstance().getGame();

    this.lastHitTracker = new LastHitTracker();

    initPlayer();
  }

  private void initPlayer() {
    player.setHealthScaled(true);
    player.setHealthScale(6);
    player.setSaturation(20);
    player.setPlayerWeather(WeatherType.CLEAR);
  }

}
