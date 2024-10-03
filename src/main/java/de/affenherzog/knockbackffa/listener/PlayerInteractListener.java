package de.affenherzog.knockbackffa.listener;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import de.affenherzog.knockbackffa.player.gui.hotbar_item.HotbarItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    final Player player = event.getPlayer();
    final KffaPlayer kffaPlayer = Kffa.getInstance().getPlayerHashMap().get(player);

    if (event.getAction() == Action.RIGHT_CLICK_BLOCK
        || event.getAction() == Action.RIGHT_CLICK_AIR) {
      if (event.getItem() == null || event.getItem().getType() == Material.AIR) {
        return;
      }

      final int slot = player.getInventory().getHeldItemSlot();
      final HotbarItem hotbarItem = kffaPlayer.getHotbarItemManager().getHotbarItem(slot);

      if (hotbarItem == null) {
        return;
      }

      if (hotbarItem.getHotbarItem().equals(event.getItem())) {
        hotbarItem.rightClick();
        event.setCancelled(true);
      }
    }
  }

}
