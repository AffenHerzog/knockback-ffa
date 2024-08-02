package de.affenherzog.knockbackffa.listener;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.game.Game;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerJoinListener implements Listener {

  @EventHandler
  public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
    final Player player = event.getPlayer();
    final Game game = Kffa.getInstance().getGame();

    Kffa.getInstance().getPlayerHashMap().put(player, new KffaPlayer(player, game));
    Kffa.getInstance().getGame().teleport(player);
  }


}
