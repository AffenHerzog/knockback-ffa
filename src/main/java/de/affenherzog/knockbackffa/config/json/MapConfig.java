package de.affenherzog.knockbackffa.config.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.affenherzog.knockbackffa.map.MapContainer;
import java.io.File;
import java.io.IOException;
import org.jetbrains.annotations.Nullable;

public class MapConfig extends CustomConfig {

  public MapConfig(String fileName) {
    super(fileName);
  }

  @Override
  public @Nullable MapContainer loadObject() {
    final ObjectMapper mapper = new ObjectMapper();

    try {
      return mapper.readValue(
          new File(this.kffa.getDataFolder() + "/" + this.fileName),
          MapContainer.class);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}
