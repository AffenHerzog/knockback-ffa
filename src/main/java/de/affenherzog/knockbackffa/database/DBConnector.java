package de.affenherzog.knockbackffa.database;

import com.zaxxer.hikari.HikariDataSource;
import de.affenherzog.knockbackffa.Kffa;
import de.chojo.sadu.datasource.DataSourceCreator;
import de.chojo.sadu.mariadb.databases.MariaDb;
import org.bukkit.configuration.file.FileConfiguration;

public final class DBConnector {

  private static HikariDataSource DATA_SOURCE;

  public static HikariDataSource getDataSource() {
    if (DATA_SOURCE == null) {
      return new HikariDataSource();
    }
    return DATA_SOURCE;
  }

  private DBConnector() {
    final FileConfiguration config = Kffa.getInstance().getConfig();

    DATA_SOURCE = DataSourceCreator.create(MariaDb.get())
        .configure(dbConfig -> dbConfig.host(config.getString("database.host"))
            .port(config.getInt("database.port"))
            .user(config.getString("database.user"))
            .password(config.getString("database.password"))
            .database(config.getString("database.database"))
        )
        .create()
        .withMaximumPoolSize(3)
        .withMinimumIdle(1)
        .build();
  }


}
