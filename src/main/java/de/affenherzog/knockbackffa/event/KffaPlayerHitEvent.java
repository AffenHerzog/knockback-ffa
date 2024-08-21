package de.affenherzog.knockbackffa.event;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class KffaPlayerHitEvent extends Event {

  private static final HandlerList HANDLER_LIST = new HandlerList();

  public static HandlerList getHandlerList() {
    return HANDLER_LIST;
  }

  private final Player hitterPlayer, targetPlayer;

  public KffaPlayerHitEvent(Player hitterPlayer, Player targetPlayer) {
    this.hitterPlayer = hitterPlayer;
    this.targetPlayer = targetPlayer;
  }

  @Override
  public @NotNull HandlerList getHandlers() {
    return HANDLER_LIST;
  }

}
