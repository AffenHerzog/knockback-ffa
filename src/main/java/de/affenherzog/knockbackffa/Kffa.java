package de.affenherzog.knockbackffa;

import de.affenherzog.knockbackffa.game.Game;
import de.affenherzog.knockbackffa.map.MapManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Kffa extends JavaPlugin {

    @Getter
    private static Kffa instance;

    @Getter
    private Game game;

    @Getter
    private MapManager mapManager;

    @Override
    public void onEnable() {
        instance = this;

        this.mapManager = new MapManager();
        this.game = new Game();
        this.game.start();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
