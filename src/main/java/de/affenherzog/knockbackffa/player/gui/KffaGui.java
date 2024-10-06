package de.affenherzog.knockbackffa.player.gui;

import de.affenherzog.knockbackffa.player.KffaPlayer;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.sound.Sound.Source;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

public abstract class KffaGui {

  public static final GuiItem FILLER = ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).name(
      Component.empty()).asGuiItem();

  public static final Sound ALLOW_CLICK = Sound.sound(Key.key("block.metal.break") , Source.BLOCK, 1f, 1f);
  public static final Sound DENY_CLICK = Sound.sound(Key.key("entity.villager.hurt") , Source.BLOCK, 1f, 1f);

  protected Gui gui;

  protected KffaPlayer kffaPlayer;

  public KffaGui(Gui gui, KffaPlayer kffaPlayer) {
    this.gui = gui;
    this.kffaPlayer = kffaPlayer;
  }

  public void initGui() {
    addContent();
    fillGui();
  }

  protected abstract void addContent();

  protected abstract void fillGui();

  public void open() {
    this.gui.open(this.kffaPlayer.getPlayer());
  }

  public void playSound(Sound sound) {
    this.kffaPlayer.getPlayer().playSound(sound);
  }

}
