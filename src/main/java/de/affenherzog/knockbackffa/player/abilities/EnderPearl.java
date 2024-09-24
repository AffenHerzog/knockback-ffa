package de.affenherzog.knockbackffa.player.abilities;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import de.affenherzog.knockbackffa.player.kit.upgrade.EnderPearlKitUpgrade;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class EnderPearl extends Ability implements ItemBasedAbility {

  private final EnderPearlKitUpgrade kitUpgrade;

  public EnderPearl(KffaPlayer player, EnderPearlKitUpgrade kitUpgrade) {
    super(player);
    this.kitUpgrade = kitUpgrade;
  }

  @Override
  protected void executeAbility() {
    setItem(buildItemStack());
  }

  private void blind() {

  }

  @Override
  public ItemStack buildItemStack() {
    return new ItemStack(Material.ENDER_PEARL);
  }

  @Override
  public void setItem(ItemStack item) {
    Bukkit.getScheduler().runTaskLater(Kffa.getInstance(), () -> {
      this.player.getPlayer().getInventory().setItem(ABIlITY_ITEM_SLOT, item);
      this.player.getPlayer().setCooldown(Material.ENDER_PEARL, this.kitUpgrade.getEnderPearlCooldown() * 20);
    }, 1);
  }
}
