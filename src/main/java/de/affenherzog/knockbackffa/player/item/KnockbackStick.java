package de.affenherzog.knockbackffa.player.item;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KnockbackStick {

  private final Component displayName;

  @Getter
  private final int knockbackLevel;

  public KnockbackStick(Component displayName, int knockbackLevel) {
    this.displayName = displayName;
    this.knockbackLevel = knockbackLevel;
  }

  public ItemStack buildItemStack() {
    final ItemStack stick = new ItemStack(Material.STICK);
    final ItemMeta stickMeta = stick.getItemMeta();

    stickMeta.displayName(this.displayName);
    stickMeta.addEnchant(Enchantment.KNOCKBACK, this.knockbackLevel, true);
    stick.setItemMeta(stickMeta);

    return stick;
  }
}
