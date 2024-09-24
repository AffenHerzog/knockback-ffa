package de.affenherzog.knockbackffa.player.abilities;

import org.bukkit.inventory.ItemStack;

public interface ItemBasedAbility {

  int ABIlITY_ITEM_SLOT = 1;

  ItemStack buildItemStack();
  void setItem(ItemStack item);
  void applyCooldown();
}
