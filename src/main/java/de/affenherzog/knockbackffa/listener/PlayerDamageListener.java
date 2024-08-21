package de.affenherzog.knockbackffa.listener;

import de.affenherzog.knockbackffa.event.KffaPlayerHitEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.jetbrains.annotations.NotNull;

public class PlayerDamageListener implements Listener {

  @EventHandler
  public void onPlayerDamage(@NotNull EntityDamageEvent event) {
    final Entity entity = event.getEntity();
    final DamageCause cause = event.getCause();

    if (!(entity instanceof Player)) {
      return;
    }

    final Player targetPlayer = (Player) event.getEntity();

    if (cause.equals(DamageCause.FALL)) {
      event.setCancelled(true);
    }

    if (cause.equals(DamageCause.ENTITY_ATTACK)) {
      if (event.getDamageSource().getCausingEntity() instanceof Player hitterPlayer) {
        KffaPlayerHitEvent kffaPlayerHitEvent = new KffaPlayerHitEvent(hitterPlayer, targetPlayer);
        Bukkit.getPluginManager().callEvent(kffaPlayerHitEvent);
      }
    }

    event.setDamage(0);

  }

}
