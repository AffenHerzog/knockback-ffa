package de.affenherzog.knockbackffa.config.yml;

import de.affenherzog.knockbackffa.Kffa;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

public class MessageManager {

  private static MessageManager instance;
  private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

  @Getter
  private FileConfiguration config;
  private final File configFile;
  private final Logger logger;

  private MessageManager(File configFile, Logger logger) {
    this.configFile = configFile;
    this.logger = logger;
    reloadConfig();
  }

  public static synchronized void initialize(File configFile, Logger logger) {
    if (instance == null) {
      instance = new MessageManager(configFile, logger);
    }
  }

  public static MessageManager getInstance() {
    if (instance == null) {
      throw new IllegalStateException("ConfigManager is not initialized");
    }
    return instance;
  }

  public void reloadConfig() {
    config = YamlConfiguration.loadConfiguration(configFile);
  }

  public @NotNull String getMessage(String path) {
    return validOrErrorMessage(path);
  }

  public @NotNull Component getMiniMessage(String path) {
    return MINI_MESSAGE.deserialize(validOrErrorMessage(path));
  }

  private @NotNull String validOrErrorMessage(String path) {
    String value = config.getString(path);

    if (value == null) {
      value = config.getString("error.no-such-config-path");
    }

    if (value == null) {
      value = "No value found for path: " + path;
    }

    return value;
  }

  public void saveDefaultConfig() {
    if (!configFile.exists()) {
      try {
        Kffa.getInstance().saveResource(configFile.getName(), true);
        FileConfiguration defaultConfig = YamlConfiguration.loadConfiguration(configFile);
        defaultConfig.options().copyDefaults(true);
        defaultConfig.save(configFile);
        config.setDefaults(defaultConfig);
      } catch (IOException e) {
        logger.severe("Could not create configuration file: " + e.getMessage());
      }
    }
  }
}
