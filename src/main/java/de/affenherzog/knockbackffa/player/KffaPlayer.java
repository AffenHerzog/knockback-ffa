package de.affenherzog.knockbackffa.player;

import de.affenherzog.knockbackffa.game.Game;
import org.bukkit.entity.Player;

public class KffaPlayer {
  final Player player;
  final Game game;

  final PlayerStats playerStats;

  public KffaPlayer(Player player, Game game) {
    this.player = player;
    this.game = game;
    this.playerStats = fetchPlayerStats();
  }

  private PlayerStats fetchPlayerStats() {
    //TODO ADD DB
    return null;
  }


}
