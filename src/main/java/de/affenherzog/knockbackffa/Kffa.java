package de.affenherzog.knockbackffa;

import de.affenherzog.knockbackffa.command.MapSkipCommand;
import de.affenherzog.knockbackffa.config.json.CustomConfigLoader;
import de.affenherzog.knockbackffa.config.json.KitConfig;
import de.affenherzog.knockbackffa.config.json.MapConfig;
import de.affenherzog.knockbackffa.database.DBConnector;
import de.affenherzog.knockbackffa.game.Game;
import de.affenherzog.knockbackffa.listener.DoubleJumpListener;
import de.affenherzog.knockbackffa.listener.EnderPearlListener;
import de.affenherzog.knockbackffa.listener.KffaPlayerDeathListener;
import de.affenherzog.knockbackffa.listener.KffaPlayerHitListener;
import de.affenherzog.knockbackffa.listener.PlayerDamageListener;
import de.affenherzog.knockbackffa.listener.PlayerDropItemListener;
import de.affenherzog.knockbackffa.listener.PlayerJoinListener;
import de.affenherzog.knockbackffa.listener.PlayerMoveListener;
import de.affenherzog.knockbackffa.listener.PlayerQuitListener;
import de.affenherzog.knockbackffa.listener.WeatherChangeListener;
import de.affenherzog.knockbackffa.map.MapContainer;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import de.affenherzog.knockbackffa.player.kit.KitDataContainer;
import de.affenherzog.knockbackffa.util.InFightReset;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
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

  @Getter
  private KitDataContainer kitDataContainer;

  @Override
  public void onEnable() {
    instance = this;

    getConfig().options().copyDefaults(true);
    saveConfig();

    DBConnector.getINSTANCE();

    CustomConfigLoader.copyConfigs();

    startInFightResetTask();

    startGameAsync();

    registerCommands();
    registerListener();
  }

  private void startInFightResetTask() {
    Bukkit.getScheduler().runTaskTimer(Kffa.getInstance(), new InFightReset(), 0L, 10L);
  }

  private void startGameAsync() {

    Kffa.getInstance().getScheduler().runTaskAsynchronously(Kffa.getInstance(), () -> {

      this.mapContainer = (MapContainer) CustomConfigLoader.CONFIGS.get(MapConfig.class).loadObject();
      this.kitDataContainer = (KitDataContainer) CustomConfigLoader.CONFIGS.get(KitConfig.class).loadObject();

      Kffa.getInstance().getScheduler().runTask(Kffa.getInstance(), () -> {
        this.game = new Game();
      });

    });
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
    pluginManager.registerEvents(new WeatherChangeListener(), this);
    pluginManager.registerEvents(new PlayerMoveListener(), this);
    pluginManager.registerEvents(new KffaPlayerHitListener(), this);
    pluginManager.registerEvents(new KffaPlayerDeathListener(), this);
    pluginManager.registerEvents(new PlayerDropItemListener(), this);
    pluginManager.registerEvents(new DoubleJumpListener(), this);
    pluginManager.registerEvents(new EnderPearlListener(), this);
  }

  @Override
  public void onDisable() {
    DBConnector.getINSTANCE().close();
  }
}
