package de.affenherzog.knockbackffa.player;

import de.affenherzog.knockbackffa.player.kit.KitType;
import de.affenherzog.knockbackffa.player.kit.logic.DefaultKit;
import de.affenherzog.knockbackffa.player.kit.logic.EnderPearlKit;
import de.affenherzog.knockbackffa.player.kit.logic.Kit;
import java.util.HashMap;
import lombok.Getter;

public class PlayerKitSettings {

  @Getter
  private final HashMap<Class<? extends Kit>, Integer> kitLevels = new HashMap<>();

  @Getter
  private final KitType currentKit;

  public PlayerKitSettings() {

    //TODO ADD DB

    this.currentKit = KitType.ENDER_PEARL;

    this.kitLevels.put(DefaultKit.class, 0);
    this.kitLevels.put(EnderPearlKit.class, 1);
  }

}
