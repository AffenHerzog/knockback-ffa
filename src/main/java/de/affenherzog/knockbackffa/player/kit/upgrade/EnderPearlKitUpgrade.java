package de.affenherzog.knockbackffa.player.kit.upgrade;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class EnderPearlKitUpgrade extends KitUpgrade {

  public static final String FIELD_TYPE = "EnderPearlKitUpgrade";

  @Getter
  private final int enderPearlCooldown, enderPearlAmount;

  @Getter
  private final boolean enderPearlBlindness;

  @JsonCreator
  public EnderPearlKitUpgrade(
      @JsonProperty("price") int price,
      @JsonProperty("doubleJumpCooldown") int doubleJumpCooldown,
      @JsonProperty("enderPearlCooldown") int enderPearlCooldown,
      @JsonProperty("enderPearlAmount") int enderPearlAmount,
      @JsonProperty("enderPearlBlindness") boolean enderPearlBlindness
  ) {

    super(price, doubleJumpCooldown);

    this.enderPearlCooldown = enderPearlCooldown;
    this.enderPearlAmount = enderPearlAmount;
    this.enderPearlBlindness = enderPearlBlindness;
  }


}
