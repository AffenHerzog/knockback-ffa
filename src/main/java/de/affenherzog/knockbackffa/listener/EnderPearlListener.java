package de.affenherzog.knockbackffa.listener;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import de.affenherzog.knockbackffa.player.PlayerState;
import de.affenherzog.knockbackffa.player.abilities.EnderPearl;
import de.affenherzog.knockbackffa.player.kit.logic.EnderPearlKit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EnderPearlListener implements Listener {

  @EventHandler
  public void onEnderPearl(@NotNull PlayerInteractEvent event) {
    final Player player = event.getPlayer();
    final ItemStack item = event.getItem();

    if (item != null && item.getType() == Material.ENDER_PEARL
        && (event.getAction() == Action.RIGHT_CLICK_AIR
        || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {

      final KffaPlayer kffaPlayer = Kffa.getInstance().getPlayerHashMap().get(player);

      if (kffaPlayer.getKit() instanceof EnderPearlKit enderPearlKit) {
        enderPearlKit.getKitAbility().executeIfAllowed();
      }

    }

  }

  @EventHandler
  public void onPlayerTeleport(@NotNull PlayerTeleportEvent event) {
    final Player player = event.getPlayer();
    final KffaPlayer kffaPlayer = Kffa.getInstance().getPlayerHashMap().get(player);

    if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
      if (cancelTeleportationIfInSpawnState(event)) {
        return;
      }

      if (kffaPlayer.getKit() instanceof EnderPearlKit enderPearlKit) {
        ((EnderPearl) enderPearlKit.getKitAbility()).applyBlindnessEffect();
      }
    }
  }

  private boolean cancelTeleportationIfInSpawnState(@NotNull PlayerTeleportEvent event) {
    final Player player = event.getPlayer();
    final KffaPlayer kffaPlayer = Kffa.getInstance().getPlayerHashMap().get(player);

    if (kffaPlayer.getPlayerState().equals(PlayerState.SPAWN)) {
      event.setCancelled(true);
      return true;
    }
    return false;
  }

}
