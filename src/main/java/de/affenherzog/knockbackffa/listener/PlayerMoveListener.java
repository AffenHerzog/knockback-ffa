package de.affenherzog.knockbackffa.listener;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.game.Game;
import de.affenherzog.knockbackffa.player.DeathHandler;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import de.affenherzog.knockbackffa.player.PlayerState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerMoveListener implements Listener {

  @EventHandler
  public void onPlayerMove(@NotNull PlayerMoveEvent event) {
    final Player player = event.getPlayer();
    final KffaPlayer kffaPlayer = Kffa.getInstance().getPlayerHashMap().get(player);
    final Game game = Kffa.getInstance().getGame();

    if (player.getY() < game.getMap().spawnLocation().y() - 10 && !kffaPlayer.getPlayerState().equals(PlayerState.PLAYING)) {
      kffaPlayer.handlePlaying();
    }

    if (player.getY() < game.getMap().deathZoneY()) {
      kffaPlayer.handleDeath();
    }

  }

}
