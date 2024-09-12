package de.affenherzog.knockbackffa.player;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.game.Game;
import de.affenherzog.knockbackffa.player.kit.KitType;
import de.affenherzog.knockbackffa.player.kit.logic.EnderPearlKit;
import de.affenherzog.knockbackffa.player.kit.logic.Kit;
import de.affenherzog.knockbackffa.util.InFightTracker;
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
  private final InFightTracker inFightTracker;

  @Getter
  private Kit kit;

  public KffaPlayer(Player player, PlayerStats playerStats) {
    this.player = player;
    this.playerStats = playerStats;
    this.game = Kffa.getInstance().getGame();

    this.inFightTracker = new InFightTracker();

    this.kit = new EnderPearlKit(this, Kffa.getInstance().getKitDataContainer().getKitData(KitType.DEFAULT));
    this.kit.init();

    initPlayer();
  }

  private void initPlayer() {
    player.setHealthScaled(true);
    player.setHealthScale(6);
    player.setSaturation(20);
    player.setPlayerWeather(WeatherType.CLEAR);
  }

}
