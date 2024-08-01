package de.affenherzog.knockbackffa.map;

import java.util.ArrayList;
import java.util.Random;

public class MapManager {

  final ArrayList<Map> maps = new ArrayList<>();

  public Map getRandomMap(Map currentMap) {
    if (maps.isEmpty()) {
      throw new RuntimeException("No maps found");
    }

    final Random random = new Random();
    Map map;

    do {
      map = maps.get(random.nextInt(maps.size()));
    } while (map.equals(currentMap) && maps.size() > 1);

    return map;
  }

}
