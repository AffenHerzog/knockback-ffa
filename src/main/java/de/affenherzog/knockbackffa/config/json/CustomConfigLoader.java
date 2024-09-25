package de.affenherzog.knockbackffa.config.json;

import java.util.HashMap;

public class CustomConfigLoader {

  public static final HashMap<Class<? extends CustomConfig>, CustomConfig> CONFIGS = new HashMap<>();

  public static void copyConfigs() {
    if (CONFIGS.isEmpty()) {
      initConfigs();
    }

    for (CustomConfig customConfig : CONFIGS.values()) {
      customConfig.copyFile();
    }
  }

  private static void initConfigs() {
    CONFIGS.put(MapConfig.class, new MapConfig("maps.json"));
    CONFIGS.put(KitConfig.class, new KitConfig("kits.json"));
  }

}
