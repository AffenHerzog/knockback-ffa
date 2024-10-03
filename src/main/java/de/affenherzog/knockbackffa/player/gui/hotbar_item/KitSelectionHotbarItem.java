package de.affenherzog.knockbackffa.player.gui.hotbar_item;

import de.affenherzog.knockbackffa.config.yml.MessageManager;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import de.affenherzog.knockbackffa.player.gui.KitSelectionGui;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class KitSelectionHotbarItem extends HotbarItem {

  public KitSelectionHotbarItem(KffaPlayer kffaPlayer) {
    super(kffaPlayer);
  }

  @Override
  protected ItemStack buildGuiItem() {
    return ItemBuilder.from(Material.CHEST).name(MessageManager.getInstance().getMiniMessage("gui.kit-selection.hotbar-item")).asGuiItem().getItemStack();
  }

  @Override
  public void rightClick() {
    new KitSelectionGui(kffaPlayer).open();
  }

}
