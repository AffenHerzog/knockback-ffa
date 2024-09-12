package de.affenherzog.knockbackffa.player.abilities;

import de.affenherzog.knockbackffa.player.KffaPlayer;
import de.affenherzog.knockbackffa.util.Cooldown;

public abstract class Ability {

  protected final KffaPlayer player;

  protected final Cooldown cooldown;

  public Ability(KffaPlayer player, int cooldownTime) {
    this.player = player;
    this.cooldown = new Cooldown(cooldownTime);
  }

  public boolean executeIfReady() {
    if (this.cooldown.isRunning()) {
      return false;
    }

    this.cooldown.startCooldown();
    this.executeAbility();

    return true;
  }

  protected abstract void executeAbility();


}
