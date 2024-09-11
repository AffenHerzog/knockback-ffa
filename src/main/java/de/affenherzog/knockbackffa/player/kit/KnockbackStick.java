package de.affenherzog.knockbackffa.player.kit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class KnockbackStick {

  private final Component displayName;

  private final int knockbackLevel;

  @JsonCreator
  public KnockbackStick(
      @JsonProperty("displayName") String displayName,
      @JsonProperty("knockbackLevel") int knockbackLevel
  ) {
    this.displayName = MiniMessage.miniMessage().deserialize(displayName);
    this.knockbackLevel = knockbackLevel;
  }
}
