package de.affenherzog.knockbackffa.player;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.game.Game;
import lombok.Getter;
import org.bukkit.entity.Player;

public class KffaPlayer {
  final Player player;
  final Game game;

  @Getter
  private final PlayerStats playerStats;

  public KffaPlayer(Player player, PlayerStats playerStats) {
    this.player = player;
    this.playerStats = playerStats;
    this.game = Kffa.getInstance().getGame();
  }

}
