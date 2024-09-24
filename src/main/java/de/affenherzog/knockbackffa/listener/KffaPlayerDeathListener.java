package de.affenherzog.knockbackffa.listener;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.event.KffaPlayerDeathEvent;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class KffaPlayerDeathListener implements Listener {

  @EventHandler
  public void onKffaPlayerDeath(@NotNull KffaPlayerDeathEvent event) {
    final KffaPlayer victimPlayer = Kffa.getInstance().getPlayerHashMap()
        .get(event.getVictimPlayer());

    if (victimPlayer == null) {
      return;
    }

    victimPlayer.getInFightTracker().reset();
  }

}
