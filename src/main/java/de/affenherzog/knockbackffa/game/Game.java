package de.affenherzog.knockbackffa.game;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.map.Map;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Game {

  private Map map;

  public void start() {
    mapChange();
  }

  public void mapChange() {
    this.map = Kffa.getInstance().getMapManager().getRandomMap(map);
    map.loadWorld();
    teleportPlayer();
  }

  private void teleportPlayer() {
    Bukkit.getOnlinePlayers().forEach(player -> {
      player.teleport(
          new Location(
              Bukkit.getWorld(map.name()),
              map.spawnLocation().x(),
              map.spawnLocation().y(),
              map.spawnLocation().z()
          ));
    });
  }

}
