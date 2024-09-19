package de.affenherzog.knockbackffa.listener;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.jetbrains.annotations.NotNull;

public class DoubleJumpListener implements Listener {

  @EventHandler
  public void onPlayerMove(@NotNull PlayerMoveEvent event) {
    final Player player = event.getPlayer();

    if (player.isOnGround()) {
      player.setAllowFlight(true);
    }
  }

  @EventHandler
  public void onToggleFly(@NotNull PlayerToggleFlightEvent event) {
    final Player player = event.getPlayer();

    player.setAllowFlight(false);
    event.setCancelled(true);

    final KffaPlayer kffaPlayer = Kffa.getInstance().getPlayerHashMap().get(player);

    if (kffaPlayer != null) {
      kffaPlayer.getKit().getDoubleJump().executeIfReady();
    }

  }

}
