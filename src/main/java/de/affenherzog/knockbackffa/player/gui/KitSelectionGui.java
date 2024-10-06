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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class KitSelectionGui extends KffaGui {

  private static final int ROWS = 3;

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

      GuiItem guiItem = buildGuiItem(kitData, kitItemStack);

      guiItem.setAction(action -> setAction(kitData, action));

      itemQueue.add(guiItem);
    }

    for (int i = 2; i < ROWS; i++) {
      for (int j = 2; j < 9; j++) {
        final GuiItem item = itemQueue.poll();
        if (item == null) {
          return;
        }

        gui.setItem(i, j, item);
      }
    }
  }

  private void setAction(@NotNull KitData kitData, InventoryClickEvent event) {
    if (this.kffaPlayer.getKit().getKitData().getType().equals(kitData.getType())) {
      playSound(DENY_CLICK);
      return;
    }
    this.kffaPlayer.updateKit(kitData.getType());
    playSound(ALLOW_CLICK);
    addContent();
    this.gui.update();
  }

  private @NotNull GuiItem buildGuiItem(@NotNull KitData kitData, @NotNull ItemStack kitItemStack) {
    if (this.kffaPlayer.getKit().getKitData().getType().equals(kitData.getType())) {
      return ItemBuilder.from(kitItemStack)
          .name(kitData.getDisplayName().append(Component.text()))
          .glow(true)
          .asGuiItem();
    }

    return ItemBuilder.from(kitItemStack)
        .name(kitData.getDisplayName())
        .disenchant(Enchantment.LUCK_OF_THE_SEA)
        .glow(false)
        .asGuiItem();
  }

  @Override
  protected void fillGui() {
    this.gui.getFiller().fill(KffaGui.FILLER);
  }
}
