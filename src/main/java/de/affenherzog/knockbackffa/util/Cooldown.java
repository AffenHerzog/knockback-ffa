package de.affenherzog.knockbackffa.util;

public class Cooldown {

  private final int cooldownTimeInSeconds;

  private long startTime;


  public Cooldown(int cooldownTimeInSeconds) {
    this.cooldownTimeInSeconds = cooldownTimeInSeconds;
  }

  public boolean isRunning() {
    return this.startTime + (cooldownTimeInSeconds * 1000L) > System.currentTimeMillis();
  }

  public void startCooldown() {

    if (isRunning()) {
      return;
    }

    this.startTime = System.currentTimeMillis();
  }

}
