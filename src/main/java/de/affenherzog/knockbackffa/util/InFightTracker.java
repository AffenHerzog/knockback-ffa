package de.affenherzog.knockbackffa.util;

import de.affenherzog.knockbackffa.player.KffaPlayer;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;;

@Getter
public class InFightTracker {

  private KffaPlayer lastHitPlayer;

  private long lastHitTime = -1;

  private boolean inFight;

  public void reset() {
    this.lastHitPlayer = null;
    this.lastHitTime = -1;
    this.inFight = false;
  }

  public void hit(@NotNull KffaPlayer player) {
    this.lastHitPlayer = player;
    this.lastHitTime = System.currentTimeMillis();
    this.inFight = true;
  }

}
