package de.affenherzog.knockbackffa.player.abilities;

import de.affenherzog.knockbackffa.player.KffaPlayer;

public abstract class Ability {

  protected final KffaPlayer player;

  public Ability(KffaPlayer player) {
    this.player = player;
  }

  public abstract boolean executeIfAllowed();

  public abstract void applyCooldown();

  public abstract void resetCooldown();

}

