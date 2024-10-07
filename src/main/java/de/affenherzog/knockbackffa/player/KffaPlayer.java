package de.affenherzog.knockbackffa.player;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.game.Game;
import de.affenherzog.knockbackffa.player.gui.hotbar_item.HotbarItemManager;
import de.affenherzog.knockbackffa.player.kit.KitType;
import de.affenherzog.knockbackffa.player.kit.logic.Kit;
import de.affenherzog.knockbackffa.util.InFightTracker;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;

public class KffaPlayer {

  @Getter
  private final Player player;
  private final Game game;

  @Getter
  private final PlayerStats playerStats;

  @Getter
  private final InFightTracker inFightTracker;

  @Getter
  private Kit kit;

  @Getter
  private final PlayerKitSettings playerKitSettings;

  @Getter
  private final HotbarItemManager hotbarItemManager;

  @Getter
  @Setter
  private PlayerState playerState;

  public KffaPlayer(Player player, PlayerStats playerStats) {
    this.player = player;
    this.playerStats = playerStats;
    this.game = Kffa.getInstance().getGame();
    this.inFightTracker = new InFightTracker();
    this.playerKitSettings = new PlayerKitSettings();
    this.hotbarItemManager = new HotbarItemManager(this);
  }

  public void init() {
    initPlayer();
    this.playerState = PlayerState.SPAWN;
    this.hotbarItemManager.init();
    this.kit = Kit.buildKit(playerKitSettings.getCurrentKitType(), this);
    this.kit.init();
    setSpawnInventory();
  }

  private void initPlayer() {
    this.player.setHealthScaled(true);
    this.player.setHealthScale(6);
    this.player.setSaturation(20);
    this.player.setPlayerWeather(WeatherType.CLEAR);
  }

  public void handleDeath() {
    this.playerState = PlayerState.SPAWN;
    this.kit.resetCooldowns();
    this.kit.getDoubleJump().cancelDisplayCooldown();
    DeathHandler.handleDeath(player);
    setSpawnInventory();
  }

  //This is a method called once when the player jumps off the spawn island and starts playing.
  public void handlePlaying() {
    this.playerState = PlayerState.PLAYING;
    this.kit.setGameInventory();
    this.player.getInventory().close();
    this.kit.getDoubleJump().displayCooldown();
  }

  private void setSpawnInventory() {
    this.player.getInventory().clear();
    hotbarItemManager.setItems();
  }

  public void updateKit(KitType kitType) {
    this.kit = Kit.buildKit(kitType, this);
  }

}
