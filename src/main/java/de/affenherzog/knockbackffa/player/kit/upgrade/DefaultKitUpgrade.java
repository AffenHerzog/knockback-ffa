package de.affenherzog.knockbackffa.player.kit.upgrade;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DefaultKitUpgrade extends KitUpgrade {

  public static final String FIELD_TYPE = "DefaultKitUpgrade";

  @JsonCreator
  public DefaultKitUpgrade(
      @JsonProperty("price") int price,
      @JsonProperty("doubleJumpCooldown") int doubleJumpCooldown,
      @JsonProperty("stickKnockback") int stickKnockback
  ) {

    super(price, doubleJumpCooldown, stickKnockback);
  }
}
