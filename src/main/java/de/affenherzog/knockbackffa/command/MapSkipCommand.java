package de.affenherzog.knockbackffa.command;

import de.affenherzog.knockbackffa.Kffa;
import de.affenherzog.knockbackffa.game.Game;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MapSkipCommand implements BasicCommand {

  @Override
  public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] strings) {
    final Game game = Kffa.getInstance().getGame();
    game.mapChange();
  }

  @Override
  public @Nullable String permission() {
    return "knockbackffa.map.skip";
  }

}
