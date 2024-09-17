package de.affenherzog.knockbackffa.player.item.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

@Getter
public class KnockbackStickData {

  private final Component displayName;

  @JsonCreator
  public KnockbackStickData(@JsonProperty("displayName") String displayName) {
    this.displayName = MiniMessage.miniMessage().deserialize(displayName);
  }

}
