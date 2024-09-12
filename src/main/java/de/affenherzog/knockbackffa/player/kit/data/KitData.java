package de.affenherzog.knockbackffa.player.kit.data;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.affenherzog.knockbackffa.player.item.KnockbackStick;
import de.affenherzog.knockbackffa.player.kit.KitType;
import de.affenherzog.knockbackffa.player.kit.upgrade.KitUpgrade;
import java.util.ArrayList;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = DefaultKitData.class, name = DefaultKitData.FIELD_TYPE),
    @JsonSubTypes.Type(value = EnderPearlKitData.class, name = EnderPearlKitData.FIELD_TYPE)
})

@Getter
public abstract class KitData {

  protected final Component displayName;

  protected final KnockbackStick stick;

  protected final String specialItem;

  protected final ArrayList<KitUpgrade> kitUpgrades;

  protected final KitType type;

  public KitData(
      String displayName,
      KnockbackStick stick,
      String specialItem,
      ArrayList<KitUpgrade> kitUpgrades,
      KitType type
  ) {

    this.displayName = MiniMessage.miniMessage().deserialize(displayName);
    this.stick = stick;
    this.specialItem = specialItem;
    this.kitUpgrades = kitUpgrades;
    this.type = type;
  }
}
