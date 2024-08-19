package de.affenherzog.knockbackffa.game;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.map.Map;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Game {

  @Getter
  private Map map;

  private Location spawnLocation;

  public Game() {
    mapChange();
  }

  public void teleport(@NotNull Player player) {
    player.teleportAsync(this.spawnLocation);
  }

  public void mapChange() {

    if (this.map != null) {
      map.unloadWorld();
    }

    this.map = Kffa.getInstance().getMapContainer().getRandomMap(map);
    map.prepareWorld();

    this.spawnLocation = generateLocation();
    teleportPlayer();
  }

  private Location generateLocation() {
    return new Location(
        Bukkit.getWorld(map.name()),
        map.spawnLocation().x(),
        map.spawnLocation().y(),
        map.spawnLocation().z());
  }

  private void teleportPlayer() {
    Kffa.getInstance().getPlayerHashMap().keySet().forEach(this::teleport);
  }

}
