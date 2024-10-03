package de.affenherzog.knockbackffa.player.abilities;

import de.affenherzog.knockbackffa.player.KffaPlayer;
import de.affenherzog.knockbackffa.util.Cooldown;

public abstract class ExternalCooldownAbility extends Ability {

  protected final Cooldown cooldown;

  public ExternalCooldownAbility(KffaPlayer player, int cooldownTime) {
    super(player);
    this.cooldown = new Cooldown(cooldownTime);
  }

  @Override
  public boolean executeIfAllowed() {

    if (this.cooldown.isRunning()) {
      return false;
    }

    applyCooldown();
    executeAbility();

    return true;
  }

  protected abstract void executeAbility();

  public void applyCooldown() {
    this.cooldown.start();
  }

  @Override
  public void resetCooldown() {
    this.cooldown.reset();
  }


}
