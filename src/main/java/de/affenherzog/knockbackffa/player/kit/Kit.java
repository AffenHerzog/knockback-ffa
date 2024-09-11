package de.affenherzog.knockbackffa.player.kit;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = DefaultKit.class, name = DefaultKit.FIELD_TYPE),
    @JsonSubTypes.Type(value = EnderPearlKit.class, name = EnderPearlKit.FIELD_TYPE)
})

@Getter
public abstract class Kit {

  private final Component displayName;

  private final KnockbackStick stick;

  private final String specialItem;

  public Kit(String displayName, KnockbackStick stick, String specialItem) {
    this.displayName = MiniMessage.miniMessage().deserialize(displayName);
    this.stick = stick;
    this.specialItem = specialItem;
  }
}
