package de.affenherzog.knockbackffa.player.kit;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.affenherzog.knockbackffa.config.Container;
import java.util.ArrayList;
import java.util.Random;

public record KitContainer(@JsonProperty ArrayList<Kit> kits) implements Container {

  public KitContainer(@JsonProperty("kits") ArrayList<Kit> kits) {
    this.kits = kits;
  }

  @Override
  public Kit getRandom(Object currentKit) {
    if (kits.isEmpty()) {
      throw new RuntimeException("No kit found");
    }

    final Random random = new Random();
    Kit kit;

    do {
      kit = kits.get(random.nextInt(kits.size()));
    } while (kit.equals(currentKit) && kits.size() > 1);

    return kit;
  }

}
