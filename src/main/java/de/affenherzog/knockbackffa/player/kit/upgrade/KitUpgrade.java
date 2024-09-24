package de.affenherzog.knockbackffa.player.kit.upgrade;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = DefaultKitUpgrade.class, name = DefaultKitUpgrade.FIELD_TYPE),
    @JsonSubTypes.Type(value = EnderPearlKitUpgrade.class, name = EnderPearlKitUpgrade.FIELD_TYPE)
})

public abstract class KitUpgrade {

  @Getter
  private final int price, doubleJumpCooldown, stickKnockback;

  public KitUpgrade(int price, int doubleJumpCooldown, int stickKnockback) {
    this.price = price;
    this.doubleJumpCooldown = doubleJumpCooldown;
    this.stickKnockback = stickKnockback;
  }

}
