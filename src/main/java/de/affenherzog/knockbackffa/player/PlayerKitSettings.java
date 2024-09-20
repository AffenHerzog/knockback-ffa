package de.affenherzog.knockbackffa.player;

import de.affenherzog.knockbackffa.player.kit.KitType;
import java.util.HashMap;
import lombok.Getter;

public class PlayerKitSettings {

  @Getter
  private final HashMap<KitType, Integer> kitLevels = new HashMap<>();

  @Getter
  private final KitType currentKitType;

  public PlayerKitSettings() {

    //TODO ADD DB

    this.currentKitType = KitType.ENDER_PEARL;

    this.kitLevels.put(KitType.DEFAULT, 0);
    this.kitLevels.put(KitType.ENDER_PEARL, 1);
  }

}
