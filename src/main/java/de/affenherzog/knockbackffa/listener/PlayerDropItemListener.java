package de.affenherzog.knockbackffa.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerDropItemListener implements Listener {
  @EventHandler
  public void onPlayerDropItem(@NotNull PlayerDropItemEvent event) {
    event.setCancelled(true);
  }

}
