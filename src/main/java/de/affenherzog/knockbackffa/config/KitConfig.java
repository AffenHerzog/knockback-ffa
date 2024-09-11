package de.affenherzog.knockbackffa.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.affenherzog.knockbackffa.player.kit.KitContainer;
import java.io.File;
import java.io.IOException;
import org.jetbrains.annotations.Nullable;

public class KitConfig extends CustomConfig {

  public KitConfig(String fileName) {
    super(fileName);
  }

  @Override
  public @Nullable KitContainer loadObject() {
    final ObjectMapper mapper = new ObjectMapper();

    try {
      return mapper.readValue(
          new File(this.kffa.getDataFolder() + "/" + this.fileName),
          KitContainer.class);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
