package de.affenherzog.knockbackffa.map;

import de.affenherzog.knockbackffa.util.SimpleLocation;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

public record Map(Component displayName, Component description, String name, SimpleLocation spawnLocation) {

  public void loadWorld() {
    if (Bukkit.getWorld(this.name) == null) {
      Bukkit.createWorld(new WorldCreator(this.name));
    }
  }

  public void unloadWorld() {
    if (Bukkit.getWorld(this.name) != null) {
      Bukkit.unloadWorld(this.name, false);
    }
  }
}
