package de.affenherzog.knockbackffa.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.jetbrains.annotations.NotNull;

public class WeatherChangeListener implements Listener {

  @EventHandler
  public void onWeatherChange(@NotNull WeatherChangeEvent event) {
    event.setCancelled(true);
  }

}
