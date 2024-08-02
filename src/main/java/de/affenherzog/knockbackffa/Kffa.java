package de.affenherzog.knockbackffa;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.affenherzog.knockbackffa.game.Game;
import de.affenherzog.knockbackffa.map.MapContainer;
import java.io.File;
import java.io.IOException;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class Kffa extends JavaPlugin {

  @Getter
  private static Kffa instance;

  @Getter
  private final BukkitScheduler scheduler = Bukkit.getScheduler();

  @Getter
  private Game game;

  @Getter
  private MapContainer mapContainer;

  private static final String MAPS_FILE_NAME = "maps.json";

  @Override
  public void onEnable() {
    instance = this;

    copyMapsFile();

    startGame();
  }

  private void copyMapsFile() {
    if (!new File(getDataFolder(), "/" + MAPS_FILE_NAME).exists()) {
      saveResource(MAPS_FILE_NAME, false);
    }
  }

  private void startGame() {
    Kffa.getInstance().getScheduler().runTaskAsynchronously(Kffa.getInstance(), () -> {
      loadMaps();

      Kffa.getInstance().getScheduler().runTask(Kffa.getInstance(), () -> {
        this.game = new Game();
      });

    });
  }

  private void loadMaps() {
    final ObjectMapper mapper = new ObjectMapper();

    try {
      this.mapContainer = mapper.readValue(
          new File(getDataFolder() + "/" + MAPS_FILE_NAME),
          MapContainer.class);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }
}
