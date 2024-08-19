package de.affenherzog.knockbackffa;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.affenherzog.knockbackffa.command.MapSkipCommand;
import de.affenherzog.knockbackffa.database.DBConnector;
import de.affenherzog.knockbackffa.game.Game;
import de.affenherzog.knockbackffa.listener.PlayerDamageListener;
import de.affenherzog.knockbackffa.listener.PlayerJoinListener;
import de.affenherzog.knockbackffa.listener.PlayerQuitListener;
import de.affenherzog.knockbackffa.map.MapContainer;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class Kffa extends JavaPlugin {

  @Getter
  private static Kffa instance;

  @Getter
  private final BukkitScheduler scheduler = Bukkit.getScheduler();

  @Getter
  private final HashMap<Player, KffaPlayer> playerHashMap = new HashMap<>();

  @Getter
  private Game game;

  @Getter
  private MapContainer mapContainer;

  private static final String MAPS_FILE_NAME = "maps.json";

  @Override
  public void onEnable() {
    instance = this;

    getConfig().options().copyDefaults(true);
    saveConfig();

    DBConnector.getINSTANCE();

    copyMapsFile();

    registerCommands();
    registerListener();

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

  private void registerCommands() {
    LifecycleEventManager<Plugin> manager = this.getLifecycleManager();
    manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
      final Commands commands = event.registrar();
      commands.register("skip", "skip the current map", new MapSkipCommand());
    });
  }

  private void registerListener() {
    final PluginManager pluginManager = this.getServer().getPluginManager();
    pluginManager.registerEvents(new PlayerJoinListener(), this);
    pluginManager.registerEvents(new PlayerQuitListener(), this);
    pluginManager.registerEvents(new PlayerDamageListener(), this);
  }

  @Override
  public void onDisable() {
    DBConnector.getINSTANCE().close();
  }
}
