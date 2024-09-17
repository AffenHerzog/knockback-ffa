package de.affenherzog.knockbackffa.player.item;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import net.kyori.adventure.text.Component;

public class KnockbackStick {

  private final Component displayName;

  @Getter
  private final int knockbackLevel;

  @JsonCreator
  public KnockbackStick(Component displayName, int knockbackLevel) {
    this.displayName = displayName;
    this.knockbackLevel = knockbackLevel;
  }
}
