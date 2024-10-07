package de.affenherzog.knockbackffa.util;

import lombok.Getter;

public class Cooldown {

  @Getter
  private final long cooldownDurationMillis;

  private long startTime;


  public Cooldown(int cooldownDurationMillis) {
    this.cooldownDurationMillis = cooldownDurationMillis * 1000L;
  }

  public boolean isRunning() {
    return this.startTime + (cooldownDurationMillis) > System.currentTimeMillis();
  }

  public void start() {
    this.startTime = System.currentTimeMillis();
  }

  public void reset() {
    this.startTime = 0L;
  }

  public long getRemainingTime() {
    long elapsed = System.currentTimeMillis() - startTime;
    return Math.max(cooldownDurationMillis - elapsed, 0);
  }

  public double getProgress() {
    return 1.0 - (double) getRemainingTime() / cooldownDurationMillis;
  }

}
