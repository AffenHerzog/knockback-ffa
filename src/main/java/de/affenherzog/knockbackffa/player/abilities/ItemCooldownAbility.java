package de.affenherzog.knockbackffa.player.abilities;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class ItemCooldownAbility extends Ability {

  int ABIlITY_ITEM_SLOT = 1;

  private final Material cooldownMaterial;

  private int cooldownTime;

  public ItemCooldownAbility(KffaPlayer player, Material cooldownMaterial, int cooldownTime) {
    super(player);
    this.cooldownMaterial = cooldownMaterial;
    this.cooldownTime = cooldownTime;
  }

  @Override
  public boolean executeIfAllowed() {

    if (this.player.getPlayer().hasCooldown(cooldownMaterial)) {
      return false;
    }

    setItem(buildItemStack());
    applyCooldown();

    return true;
  }

  public abstract ItemStack buildItemStack();

  public void setItem(ItemStack item) {
    Bukkit.getScheduler().runTaskLater(
        Kffa.getInstance(), () -> this.player.getPlayer().getInventory()
            .setItem(ABIlITY_ITEM_SLOT, item), 1);
  }

  @Override
  public void applyCooldown() {
    Bukkit.getScheduler().runTaskLater(
        Kffa.getInstance(), () -> this.player.getPlayer().
            setCooldown(Material.ENDER_PEARL, cooldownTime * 20), 1);
  }

  @Override
  public void resetCooldown() {
    this.player.getPlayer().setCooldown(this.cooldownMaterial, 0);
  }
}
