package de.affenherzog.knockbackffa.player.abilities;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import de.affenherzog.knockbackffa.player.kit.upgrade.EnderPearlKitUpgrade;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EnderPearl extends Ability implements ItemBasedAbility {
  
  private final static double BLINDNESS_RADIUS = 5;
  private final static int BLINDNESS_DURATION = 3 * 20;
  private final static int BLINDNESS_AMPLIFIER = 1;

  private final EnderPearlKitUpgrade kitUpgrade;

  public EnderPearl(KffaPlayer player, EnderPearlKitUpgrade kitUpgrade) {
    super(player);
    this.kitUpgrade = kitUpgrade;
  }

  @Override
  protected void executeAbility() {
    setItem(buildItemStack());
    applyCooldown();
  }

  private boolean shouldApplyBlindness() {
    return kitUpgrade.isEnderPearlBlindness();
  }

  public void applyBlindnessEffect() {

    if (!shouldApplyBlindness()) {
      return;
    }

    Bukkit.getScheduler().runTaskLater(Kffa.getInstance(), () -> {
      final List<Entity> nearbyEntities = player.getPlayer().getNearbyEntities(
          BLINDNESS_RADIUS, BLINDNESS_RADIUS, BLINDNESS_RADIUS);

      nearbyEntities.forEach(e -> {
        if (e instanceof Player nearbyPlayer) {
          nearbyPlayer.addPotionEffect(new PotionEffect(
              PotionEffectType.BLINDNESS, BLINDNESS_DURATION, BLINDNESS_AMPLIFIER));
        }
      });
    }, 1);
  }

  @Override
  public ItemStack buildItemStack() {
    return new ItemStack(Material.ENDER_PEARL);
  }

  @Override
  public void setItem(ItemStack item) {
    Bukkit.getScheduler().runTaskLater(Kffa.getInstance(), () -> {
      this.player.getPlayer().getInventory().setItem(ABIlITY_ITEM_SLOT, item);
    }, 1);
  }

  @Override
  public void applyCooldown() {
    Bukkit.getScheduler().runTaskLater(Kffa.getInstance(), () -> {
      this.player.getPlayer()
          .setCooldown(Material.ENDER_PEARL, this.kitUpgrade.getEnderPearlCooldown() * 20);
    }, 1);
  }
}
