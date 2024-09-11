package de.affenherzog.knockbackffa.player.kit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DefaultKit extends Kit {

  public static final String FIELD_TYPE = "DefaultKit";

  @JsonCreator
  public DefaultKit(
      @JsonProperty("displayName") String displayName,
      @JsonProperty("stick") KnockbackStick stick,
      @JsonProperty("specialItem") String specialItem
  ) {

    super(displayName, stick, specialItem);
  }


}
