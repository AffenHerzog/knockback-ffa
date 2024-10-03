package de.affenherzog.knockbackffa.player.gui.hotbar_item;

import de.affenherzog.knockbackffa.player.KffaPlayer;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

public abstract class HotbarItem {

  @Getter
  protected ItemStack hotbarItem;

  protected KffaPlayer kffaPlayer;

  public HotbarItem(KffaPlayer kffaPlayer) {
    this.kffaPlayer = kffaPlayer;
    this.hotbarItem = buildGuiItem();
  }

  protected abstract ItemStack buildGuiItem();


  public abstract void rightClick();
}
