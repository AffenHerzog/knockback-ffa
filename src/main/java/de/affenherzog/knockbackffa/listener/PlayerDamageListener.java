package de.affenherzog.knockbackffa.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerDamageListener implements Listener {
  @EventHandler
  public void onPlayerDamage(@NotNull EntityDamageEvent event) {
    final Entity entity = event.getEntity();

    if (!(entity instanceof Player)) {
      return;
    }

    event.setCancelled(true);

  }

}
