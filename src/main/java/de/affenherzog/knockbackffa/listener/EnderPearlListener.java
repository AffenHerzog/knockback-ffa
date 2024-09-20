package de.affenherzog.knockbackffa.listener;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import de.affenherzog.knockbackffa.player.kit.logic.EnderPearlKit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EnderPearlListener implements Listener {

  @EventHandler
  public void onEnderPearl(PlayerInteractEvent event) {
    final Player player = event.getPlayer();
    final ItemStack item = event.getItem();

    if (item != null && item.getType() == Material.ENDER_PEARL && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {

      if (player.hasCooldown(Material.ENDER_PEARL)) {
        return;
      }

      final KffaPlayer kffaPlayer = Kffa.getInstance().getPlayerHashMap().get(player);

      if (kffaPlayer.getKit() instanceof EnderPearlKit enderPearlKit) {
        enderPearlKit.getKitAbility().executeIfReady();
      }

    }

  }

}
