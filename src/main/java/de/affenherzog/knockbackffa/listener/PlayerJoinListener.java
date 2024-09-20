package de.affenherzog.knockbackffa.listener;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import de.affenherzog.knockbackffa.player.PlayerRepositoryImpl;
import de.affenherzog.knockbackffa.player.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerJoinListener implements Listener {

  @EventHandler
  public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
    final Player player = event.getPlayer();

    PlayerRepositoryImpl.getINSTANCE().findByUUID(player.getUniqueId()).thenAccept(playerStats -> {

      final KffaPlayer kffaPlayer = playerStats
          .map(stats -> new KffaPlayer(player, stats))
          .orElseGet(() -> registerPlayer(player));

      Bukkit.getScheduler().runTask(Kffa.getInstance(), () -> {
        kffaPlayer.init();
        Kffa.getInstance().getPlayerHashMap().put(player, kffaPlayer);
      });
    });

    Kffa.getInstance().getGame().teleport(player);
  }

  private @NotNull KffaPlayer registerPlayer(@NotNull Player player) {
    final KffaPlayer kffaPlayer = new KffaPlayer(player, PlayerStats.FIRST_JOIN_PLAYER_STATS);
    PlayerRepositoryImpl.getINSTANCE().insert(kffaPlayer);
    return kffaPlayer;
  }


}
