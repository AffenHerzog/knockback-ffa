package de.affenherzog.knockbackffa.listener;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.player.DeathHandler;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import de.affenherzog.knockbackffa.player.PlayerRepositoryImpl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerQuitListener implements Listener {

  @EventHandler
  public void onPlayerQuit(@NotNull PlayerQuitEvent e) {
    final Player player = e.getPlayer();
    final KffaPlayer kffaPlayer = Kffa.getInstance().getPlayerHashMap().get(player);

    if (kffaPlayer != null) {
      checkInFightLeave(kffaPlayer);
      PlayerRepositoryImpl.getINSTANCE().update(kffaPlayer);
      Kffa.getInstance().getPlayerHashMap().remove(player);
    }

  }

  private void checkInFightLeave(@NotNull KffaPlayer kffaPlayer) {
    if (kffaPlayer.getInFightTracker().isInFight()) {
      DeathHandler.handleDeath(kffaPlayer.getPlayer());
    }
  }

}
