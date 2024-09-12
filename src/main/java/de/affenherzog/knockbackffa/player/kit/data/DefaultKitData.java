package de.affenherzog.knockbackffa.player.kit.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.affenherzog.knockbackffa.player.item.KnockbackStick;
import de.affenherzog.knockbackffa.player.kit.KitType;
import de.affenherzog.knockbackffa.player.kit.upgrade.KitUpgrade;
import java.util.ArrayList;

public class DefaultKitData extends KitData {

  public static final String FIELD_TYPE = "DefaultKit";

  @JsonCreator
  public DefaultKitData(
      @JsonProperty("displayName") String displayName,
      @JsonProperty("stick") KnockbackStick stick,
      @JsonProperty("specialItem") String specialItem,
      @JsonProperty("upgrades") ArrayList<KitUpgrade> kitUpgrades
  ) {

    super(displayName, stick, specialItem, kitUpgrades, KitType.DEFAULT);
  }


}
