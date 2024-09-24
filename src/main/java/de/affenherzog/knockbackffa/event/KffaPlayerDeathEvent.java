package de.affenherzog.knockbackffa.event;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class KffaPlayerDeathEvent extends Event {

  private static final HandlerList HANDLER_LIST = new HandlerList();

  public static HandlerList getHandlerList() {
    return HANDLER_LIST;
  }

  @Getter
  private final @NotNull Player victimPlayer;

  public KffaPlayerDeathEvent(@NotNull Player victimPlayer) {
    this.victimPlayer = victimPlayer;
  }

  @Override
  public @NotNull HandlerList getHandlers() {
    return HANDLER_LIST;
  }

}
