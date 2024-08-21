package de.affenherzog.knockbackffa.listener;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.event.KffaPlayerHitEvent;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class KffaPlayerHitListener implements Listener {

  @EventHandler
  public void onKffaPlayerHit(@NotNull KffaPlayerHitEvent event) {
    final KffaPlayer targetPlayer = Kffa.getInstance().getPlayerHashMap().get(event.getTargetPlayer());
    final KffaPlayer hitterPlayer = Kffa.getInstance().getPlayerHashMap().get(event.getHitterPlayer());

    if (targetPlayer == null || hitterPlayer == null) {
      return;
    }

    targetPlayer.getLastHitTracker().setLastHit(hitterPlayer);
  }
}
