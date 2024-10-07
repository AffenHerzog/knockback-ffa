package de.affenherzog.knockbackffa.player.abilities;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class DoubleJump extends ExternalCooldownAbility {

  private final static int MAX_CHARACTER_DISPLAY = 20;

  private BukkitTask task;

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

  public void displayCooldown() {
    this.task = Bukkit.getScheduler().runTaskTimer(Kffa.getInstance(), () -> {
      Component actionBarComponent = Component.text("");
      int progressCharacters = (int) (cooldown.getProgress() * MAX_CHARACTER_DISPLAY);

      for (int i = 0; i < MAX_CHARACTER_DISPLAY; i++) {
        float progressRatio = (float) i / MAX_CHARACTER_DISPLAY;

        int red = (int) Math.max(255 * (1 - progressRatio), 0);
        int green = (int) Math.max(255 * progressRatio, 0);

        TextColor color = TextColor.color(red, green, 0);

        if (i < progressCharacters) {
          actionBarComponent = actionBarComponent.append(Component.text("⬢")
              .color(color)
              .decorate(TextDecoration.BOLD));
        } else {
          actionBarComponent = actionBarComponent.append(Component.text("⬢")
              .color(TextColor.color(100, 100, 100))
              .decorate(TextDecoration.BOLD));
        }
      }

      this.player.getPlayer().sendActionBar(actionBarComponent);
    }, 0, 1);
  }

  public void cancelDisplayCooldown() {
    this.task.cancel();
    this.player.getPlayer().sendActionBar(Component.empty());
  }

}
