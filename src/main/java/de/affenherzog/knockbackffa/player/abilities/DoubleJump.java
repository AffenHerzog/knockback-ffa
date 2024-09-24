package de.affenherzog.knockbackffa.player.abilities;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

public class DoubleJump extends Ability implements Listener {

  public DoubleJump(KffaPlayer player, int cooldownTime) {
    super(player, cooldownTime);
  }

  @Override
  protected void executeAbility() {
    final Vector jumpVector = this.player.getPlayer().getLocation().getDirection()
        .multiply(Kffa.getInstance().getConfig().getDouble("double-jump.multiply"))
        .setY(Kffa.getInstance().getConfig().getDouble("double-jump.y"));
    this.player.getPlayer().setVelocity(jumpVector);
  }

}
