package de.affenherzog.knockbackffa.player.gui;

import de.affenherzog.knockbackffa.player.KffaPlayer;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

public abstract class KffaGui {

  public static final GuiItem FILLER = ItemBuilder.from(Material.BLUE_STAINED_GLASS_PANE).name(
      Component.empty()).asGuiItem();

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

}
