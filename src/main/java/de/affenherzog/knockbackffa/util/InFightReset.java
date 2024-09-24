package de.affenherzog.knockbackffa.util;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.player.KffaPlayer;

public class InFightReset implements Runnable {

  @Override
  public void run() {

    for (InFightTracker tracker : Kffa.getInstance().getPlayerHashMap().values().stream()
        .map(KffaPlayer::getInFightTracker).toList()) {

      if (tracker.getLastHitTime() == -1) {
        continue;
      }

      if (tracker.getLastHitTime() +
          (Kffa.getInstance().getConfig().getInt("in-fight-reset-time-sec") * 1000L)
          < System.currentTimeMillis()) {

        tracker.reset();
      }

    }
  }
}
