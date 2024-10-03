package de.affenherzog.knockbackffa.player.gui;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.config.yml.MessageManager;
import de.affenherzog.knockbackffa.player.KffaPlayer;
import de.affenherzog.knockbackffa.player.kit.data.KitData;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class KitSelectionGui extends KffaGui {

  private static final int ROWS = 5;

  private static final Component title = MessageManager.getInstance()
      .getMiniMessage("gui.kit-selection.title");

  public KitSelectionGui(KffaPlayer kffaPlayer) {
    super(
        Gui.gui()
            .title(title)
            .rows(ROWS)
            .type(GuiType.CHEST)
            .disableAllInteractions()
            .create(),
        kffaPlayer
    );

    initGui();
  }

  @Override
  protected void addContent() {
    final ArrayList<KitData> kitDataList = Kffa.getInstance().getKitDataContainer().kitData();
    final Queue<GuiItem> itemQueue = new LinkedList<>();

    for (KitData kitData : kitDataList) {
      ItemStack kitItemStack = kitData.getSpecialItem().getItem();

      if (kitItemStack == null) {
        kitItemStack = new ItemStack(Material.STICK);
      }

      GuiItem guiItem = ItemBuilder.from(kitItemStack).name(kitData.getDisplayName()).asGuiItem();
      guiItem.setAction(action -> this.kffaPlayer.updateKit(kitData.getType()));

      itemQueue.add(guiItem);
    }

    for (int i = 1; i < ROWS; i++) {
      for (int j = 1; j < 7; j++) {
        final GuiItem item = itemQueue.poll();
        if (item == null) {
          return;
        }

        gui.setItem(i, j, item);
      }
    }
  }

  @Override
  protected void fillGui() {
    this.gui.getFiller().fill(KffaGui.FILLER);
  }
}
