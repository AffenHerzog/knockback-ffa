package de.affenherzog.knockbackffa.player.kit.logic;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import de.affenherzog.knockbackffa.player.abilities.DoubleJump;
import de.affenherzog.knockbackffa.player.kit.data.KitData;
import org.bukkit.Bukkit;

public abstract class Kit {

  protected KitData kitData;

  protected DoubleJump doubleJump;

  public Kit(KffaPlayer kffaPlayer, KitData kitData) {
    this.kitData = kitData;
    this.doubleJump = new DoubleJump(kffaPlayer, 2);
  }

  public void init() {
    Bukkit.getPluginManager().registerEvents(doubleJump, Kffa.getInstance());
  }


}
