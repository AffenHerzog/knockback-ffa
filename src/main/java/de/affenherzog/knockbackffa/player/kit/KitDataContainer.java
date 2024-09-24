package de.affenherzog.knockbackffa.player.kit;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.affenherzog.knockbackffa.config.Container;
import de.affenherzog.knockbackffa.player.kit.data.KitData;
import java.util.ArrayList;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public record KitDataContainer(@JsonProperty ArrayList<KitData> kitData) implements Container {

  public KitDataContainer(@JsonProperty("kits") ArrayList<KitData> kitData) {
    this.kitData = kitData;
  }

  public @NotNull KitData getKitData(KitType type) {
    for (KitData kitData : kitData) {
      if (kitData.getType() == type) {
        return kitData;
      }
    }
    throw new IllegalArgumentException("No such kit: " + type);
  }

  @Override
  public KitData getRandom(Object currentKit) {
    if (kitData.isEmpty()) {
      throw new RuntimeException("No kit found");
    }

    final Random random = new Random();
    KitData randomKitData;

    do {
      randomKitData = kitData.get(random.nextInt(kitData.size()));
    } while (randomKitData.equals(currentKit) && kitData.size() > 1);

    return randomKitData;
  }

}
