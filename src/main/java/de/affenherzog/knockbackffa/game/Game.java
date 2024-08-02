package de.affenherzog.knockbackffa.game;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.map.Map;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Game {

  @Getter
  private Map map;

  public Game() {
    mapChange();
  }

  public void mapChange() {

    if (this.map != null) {
      map.unloadWorld();
    }

    this.map = Kffa.getInstance().getMapContainer().getRandomMap(map);
    map.loadWorld();
    teleportPlayer();
  }

  private void teleportPlayer() {
    final Location location = new Location(
        Bukkit.getWorld(map.name()),
        map.spawnLocation().x(),
        map.spawnLocation().y(),
        map.spawnLocation().z());

    Bukkit.getOnlinePlayers().forEach(player -> {
      player.teleportAsync(location);
    });
  }

}
