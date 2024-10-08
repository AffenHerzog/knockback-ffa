package de.affenherzog.knockbackffa.player.kit.logic;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import de.affenherzog.knockbackffa.player.abilities.Ability;
import de.affenherzog.knockbackffa.player.abilities.DoubleJump;
import de.affenherzog.knockbackffa.player.abilities.ItemBasedAbility;
import de.affenherzog.knockbackffa.player.item.KnockbackStick;
import de.affenherzog.knockbackffa.player.kit.KitType;
import de.affenherzog.knockbackffa.player.kit.data.KitData;
import de.affenherzog.knockbackffa.player.kit.upgrade.KitUpgrade;
import de.affenherzog.knockbackffa.util.TriFunction;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public abstract class Kit {

  protected final KffaPlayer kffaPlayer;

  protected final KitData kitData;

  @Getter
  protected final DoubleJump doubleJump;
  protected final KitUpgrade kitUpgrade;

  @Getter
  protected Ability kitAbility;

  protected final KnockbackStick knockbackStick;

  private static final Map<KitType, TriFunction<KffaPlayer, KitData, KitUpgrade, Kit>> kitMap = new HashMap<>();

  static {
    kitMap.put(KitType.DEFAULT, DefaultKit::new);
    kitMap.put(KitType.ENDER_PEARL, EnderPearlKit::new);
  }

  public static Kit buildKit(KitType type, KffaPlayer kffaPlayer) {
    final KitData kitData = Kffa.getInstance().getKitDataContainer().getKitData(type);

    final KitUpgrade upgrade = kitData.getKitUpgrades()
        .get(kffaPlayer.getPlayerKitSettings().getKitLevels().get(type));

    final TriFunction<KffaPlayer, KitData, KitUpgrade, Kit> constructor = kitMap.get(type);

    if (constructor != null) {
      return constructor.apply(kffaPlayer, kitData, upgrade);
    }

    throw new IllegalStateException("Unexpected value: " + type);
  }


  public Kit(
      @NotNull KffaPlayer kffaPlayer,
      @NotNull KitData kitData,
      @NotNull KitUpgrade kitUpgrade
  ) {

    this.kffaPlayer = kffaPlayer;
    this.kitData = kitData;
    this.kitUpgrade = kitUpgrade;

    this.doubleJump = new DoubleJump(kffaPlayer, this.kitUpgrade.getDoubleJumpCooldown());

    this.knockbackStick = new KnockbackStick(
        kitData.getStick().getDisplayName(),
        this.kitUpgrade.getStickKnockback()
    );

  }

  public void init() {

  }

  public void setGameInventory() {
    final Player player = kffaPlayer.getPlayer();
    final Inventory inv = player.getInventory();

    if (kitAbility instanceof ItemBasedAbility itemBased) {
      itemBased.setItem(itemBased.buildItemStack());
    }

    inv.setItem(0, this.knockbackStick.buildItemStack());
  }


}
