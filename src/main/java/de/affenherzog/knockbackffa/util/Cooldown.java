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

  public void start() {
    this.startTime = System.currentTimeMillis();
  }

  public void reset() {
    this.startTime = 0L;
  }

}
