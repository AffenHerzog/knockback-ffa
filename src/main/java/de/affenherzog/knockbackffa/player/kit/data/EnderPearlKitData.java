package de.affenherzog.knockbackffa.player.kit.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.affenherzog.knockbackffa.player.item.SpecialItem;
import de.affenherzog.knockbackffa.player.item.data.KnockbackStickData;
import de.affenherzog.knockbackffa.player.kit.KitType;
import de.affenherzog.knockbackffa.player.kit.upgrade.KitUpgrade;
import java.util.ArrayList;

public class EnderPearlKitData extends KitData {

  public static final String FIELD_TYPE = "EnderPearlKit";

  @JsonCreator
  public EnderPearlKitData(
      @JsonProperty("displayName") String displayName,
      @JsonProperty("stick") KnockbackStickData stick,
      @JsonProperty("specialItem") SpecialItem specialItem,
      @JsonProperty("upgrades") ArrayList<KitUpgrade> kitUpgrades
  ) {

    super(displayName, stick, specialItem, kitUpgrades, KitType.ENDER_PEARL);
  }

}
