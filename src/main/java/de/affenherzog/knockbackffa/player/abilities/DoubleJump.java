package de.affenherzog.knockbackffa.player.abilities;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class DoubleJump extends Ability implements Listener {

  public DoubleJump(KffaPlayer player, int cooldownTime) {
    super(player, cooldownTime);
  }

  @Override
  public void executeAbility() {
    final Vector jumpVector = this.player.getPlayer().getLocation().getDirection()
        .multiply(Kffa.getInstance().getConfig().getDouble("double-jump.multiply"))
        .setY(Kffa.getInstance().getConfig().getDouble("double-jump.y"));
    this.player.getPlayer().setVelocity(jumpVector);
  }

  @EventHandler
  public void onPlayerMove(@NotNull PlayerMoveEvent event) {
    final Player player = event.getPlayer();

    if (!this.player.getPlayer().equals(player)) {
      return;
    }

    if (player.isOnGround()) {
      player.setAllowFlight(true);
    }
  }

  @EventHandler
  public void onToggleFly(@NotNull PlayerToggleFlightEvent event) {
    final Player player = event.getPlayer();

    if (!this.player.getPlayer().equals(player)) {
      return;
    }

    player.setAllowFlight(false);
    event.setCancelled(true);
    executeIfReady();
  }

}
