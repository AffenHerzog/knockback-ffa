package de.affenherzog.knockbackffa.player.item;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.affenherzog.knockbackffa.config.yml.MessageManager;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class SpecialItem {

  @Getter
  @JsonIgnore
  private final ItemStack item;

  @JsonCreator
  public SpecialItem(
      @JsonProperty("title") String title,
      @JsonProperty("material") String material
  ) {

    this.item = buildItemStack(
        MessageManager.MINI_MESSAGE.deserialize(title),
        Material.valueOf(material));
  }

  private @NotNull ItemStack buildItemStack(@NotNull Component title, @NotNull Material material) {
    final ItemStack itemStack = new ItemStack(material);

    final ItemMeta meta = itemStack.getItemMeta();
    meta.displayName(title);
    itemStack.setItemMeta(meta);

    return itemStack;
  }
}
