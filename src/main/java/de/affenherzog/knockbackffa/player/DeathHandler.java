package de.affenherzog.knockbackffa.player;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.event.KffaPlayerDeathEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DeathHandler {

  public static void handleDeath(Player victimPlayer) {
    final KffaPlayerDeathEvent deathEvent = new KffaPlayerDeathEvent(victimPlayer);
    handlePlayer(victimPlayer);
    Bukkit.getPluginManager().callEvent(deathEvent);
  }

  private static void handlePlayer(Player victimPlayer) {
    final KffaPlayer victimKffaPlayer = Kffa.getInstance().getPlayerHashMap().get(victimPlayer);

    if (victimKffaPlayer == null) {
      return;
    }

    handleVictim(victimKffaPlayer);
    handleKiller(victimKffaPlayer);
  }

  private static void handleVictim(@NotNull KffaPlayer victimKffaPlayer) {
    Kffa.getInstance().getGame().teleport(victimKffaPlayer.getPlayer());
    victimKffaPlayer.getPlayerStats().increaseDeaths();
  }

  private static void handleKiller(@NotNull KffaPlayer victimKffaPlayer) {
    final KffaPlayer killerPlayer = victimKffaPlayer.getInFightTracker().getLastHitPlayer();
    if (killerPlayer != null) {
      killerPlayer.getPlayerStats().increaseKills();
    }
  }

}
