package de.affenherzog.knockbackffa.map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.util.SimpleLocation;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

public record Map(
    Component displayName,
    Component description,
    String name,
    SimpleLocation spawnLocation) {

  @JsonCreator
  public Map(
      @JsonProperty("displayName") String displayName,
      @JsonProperty("description") String description,
      @JsonProperty("name") String name,
      @JsonProperty("spawnLocation") SimpleLocation spawnLocation) {

    this(
        MiniMessage.miniMessage().deserialize(displayName),
        MiniMessage.miniMessage().deserialize(description),
        name,
        spawnLocation);
  }

  public void loadWorld() {
    if (Bukkit.getWorld(this.name) == null) {
      Bukkit.createWorld(new WorldCreator(this.name));
    }
  }

  private static final int UNLOAD_DELAY_TICS = 40;

  public void unloadWorld() {
    if (Bukkit.getWorld(this.name) != null) {
      Kffa.getInstance().getScheduler().runTaskLater(Kffa.getInstance(), () -> {
        if (!Kffa.getInstance().getGame().getMap().equals(this)) {
          Bukkit.unloadWorld(this.name, false);
        }
      }, UNLOAD_DELAY_TICS);
    }
  }
}
