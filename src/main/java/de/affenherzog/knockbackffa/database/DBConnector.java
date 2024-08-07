package de.affenherzog.knockbackffa.database;

import com.zaxxer.hikari.HikariDataSource;
import de.affenherzog.knockbackffa.Kffa;
import de.chojo.sadu.datasource.DataSourceCreator;
import de.chojo.sadu.mapper.RowMapperRegistry;
import de.chojo.sadu.mariadb.databases.MariaDb;
import de.chojo.sadu.mariadb.mapper.MariaDbMapper;
import de.chojo.sadu.queries.configuration.QueryConfiguration;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public final class DBConnector {

  @Getter
  private final static DBConnector INSTANCE = new DBConnector();

  @Getter
  private final HikariDataSource dataSource;


  private DBConnector() {
    final FileConfiguration config = Kffa.getInstance().getConfig();

    this.dataSource = DataSourceCreator.create(MariaDb.get())
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

    QueryConfiguration queryConfiguration = QueryConfiguration.builder(dataSource)
        .setExceptionHandler(
            err -> Bukkit.getLogger().severe("An error occured during a database request" + err))
        .setThrowExceptions(true)
        .setAtomic(true)
        .setRowMapperRegistry(new RowMapperRegistry().register(MariaDbMapper.getDefaultMapper()))
        .build();

    QueryConfiguration.setDefault(queryConfiguration);
  }


}
