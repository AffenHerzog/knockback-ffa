package de.affenherzog.knockbackffa.map;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Random;

public class MapContainer {

  @JsonProperty
  final ArrayList<Map> maps;

  public MapContainer(@JsonProperty("maps") ArrayList<Map> maps) {
    this.maps = maps;
  }

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
