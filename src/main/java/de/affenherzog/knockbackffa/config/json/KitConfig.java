package de.affenherzog.knockbackffa.config.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.affenherzog.knockbackffa.player.kit.KitDataContainer;
import java.io.File;
import java.io.IOException;
import org.jetbrains.annotations.Nullable;

public class KitConfig extends CustomConfig {

  public KitConfig(String fileName) {
    super(fileName);
  }

  @Override
  public @Nullable KitDataContainer loadObject() {
    final ObjectMapper mapper = new ObjectMapper();

    try {
      return mapper.readValue(
          new File(this.kffa.getDataFolder() + "/" + this.fileName),
          KitDataContainer.class);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
