package de.affenherzog.knockbackffa.map;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.affenherzog.knockbackffa.config.Container;
import java.util.ArrayList;
import java.util.Random;

public class MapContainer implements Container {

  @JsonProperty
  final ArrayList<Map> maps;

  public MapContainer(@JsonProperty("maps") ArrayList<Map> maps) {
    this.maps = maps;
  }

  @Override
  public Map getRandom(Object currentMap) {
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
