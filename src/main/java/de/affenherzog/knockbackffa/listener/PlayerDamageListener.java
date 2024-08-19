package de.affenherzog.knockbackffa.listener;

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

    if (!(entity instanceof Player)) {
      return;
    }

    if (event.getCause().equals(DamageCause.FALL)) {
      event.setCancelled(true);
    }

    event.setDamage(0);

  }

}
