package de.affenherzog.knockbackffa.player.gui.hotbar_item;

import de.affenherzog.knockbackffa.player.KffaPlayer;
import java.util.HashMap;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public final class HotbarItemManager {

  private final KffaPlayer kffaPlayer;

  private final HashMap<Integer, HotbarItem> items = new HashMap<>();

  public HotbarItemManager(KffaPlayer kffaPlayer) {
    this.kffaPlayer = kffaPlayer;
  }

  public void init() {
    items.put(4, new KitSelectionHotbarItem(kffaPlayer));
  }

  public void setItems() {
    items.forEach((k, v) -> {
      kffaPlayer.getPlayer().getInventory().setItem(k, v.hotbarItem);
    });
  }

  public @Nullable ItemStack getItemStack(int slot) {

    HotbarItem hotbarItem = items.get(slot);

    if (hotbarItem == null) {
      return null;
    }

    return items.get(slot).hotbarItem;
  }

  public @Nullable HotbarItem getHotbarItem(int slot) {
    return items.get(slot);
  }

}
