package de.affenherzog.knockbackffa.player.kit.logic;

import de.affenherzog.knockbackffa.player.KffaPlayer;
import de.affenherzog.knockbackffa.player.abilities.EnderPearl;
import de.affenherzog.knockbackffa.player.kit.data.KitData;
import de.affenherzog.knockbackffa.player.kit.upgrade.EnderPearlKitUpgrade;
import de.affenherzog.knockbackffa.player.kit.upgrade.KitUpgrade;

public class EnderPearlKit extends Kit {

  public EnderPearlKit(KffaPlayer kffaPlayer, KitData kitData, KitUpgrade upgrade) {
    super(kffaPlayer, kitData, upgrade);
    this.kitAbility = new EnderPearl(kffaPlayer, (EnderPearlKitUpgrade) upgrade);
  }
}
