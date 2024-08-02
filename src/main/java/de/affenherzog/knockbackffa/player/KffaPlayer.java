package de.affenherzog.knockbackffa.player;

import de.affenherzog.knockbackffa.game.Game;
import org.bukkit.entity.Player;

public class KffaPlayer {
  final Player player;
  final Game game;

  public KffaPlayer(Player player, Game game) {
    this.player = player;
    this.game = game;
  }


}
