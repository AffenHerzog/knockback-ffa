package de.affenherzog.knockbackffa.player.kit.logic;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import de.affenherzog.knockbackffa.player.abilities.DoubleJump;
import de.affenherzog.knockbackffa.player.item.KnockbackStick;
import de.affenherzog.knockbackffa.player.kit.KitType;
import de.affenherzog.knockbackffa.player.kit.data.KitData;
import de.affenherzog.knockbackffa.player.kit.upgrade.KitUpgrade;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public abstract class Kit {

  private static final Map<KitType, BiFunction<KffaPlayer, KitData, Kit>> kitMap = new HashMap<>();


  protected final KffaPlayer kffaPlayer;

  protected final KitData kitData;
  protected final KitUpgrade kitUpgrade;
  protected final DoubleJump doubleJump;
  protected final KnockbackStick knockbackStick;


  static {
    kitMap.put(KitType.DEFAULT, DefaultKit::new);
    kitMap.put(KitType.ENDER_PEARL, EnderPearlKit::new);
  }

  public static Kit buildKit(KitType type, KffaPlayer kffaPlayer) {
    final KitData kitData = Kffa.getInstance().getKitDataContainer().getKitData(type);
    final BiFunction<KffaPlayer, KitData, Kit> constructor = kitMap.get(type);

    if (constructor != null) {
      return constructor.apply(kffaPlayer, kitData);
    }
    throw new IllegalStateException("Unexpected value: " + type);
  }


  public Kit(@NotNull KffaPlayer kffaPlayer, @NotNull KitData kitData) {
    this.kffaPlayer = kffaPlayer;
    this.kitData = kitData;


    this.kitUpgrade = this.kitData.getKitUpgrades().get(this.kffaPlayer.getPlayerKitSettings().getKitLevels().get(this.getClass()));
    this.doubleJump = new DoubleJump(kffaPlayer, this.kitUpgrade.getDoubleJumpCooldown());
    this.knockbackStick = new KnockbackStick(kitData.getStick().getDisplayName(), this.kitUpgrade.getStickKnockback());

    System.out.println(kitUpgrade.getPrice() + " " + kitUpgrade.getStickKnockback() + " " + kitUpgrade.getDoubleJumpCooldown());
  }

  public void init() {
    Bukkit.getPluginManager().registerEvents(doubleJump, Kffa.getInstance());
  }

  public void setGameInventory() {
    final Player player = kffaPlayer.getPlayer();
    final Inventory inv = player.getInventory();

    inv.setItem(0, this.knockbackStick.buildItemStack());
  }


}
