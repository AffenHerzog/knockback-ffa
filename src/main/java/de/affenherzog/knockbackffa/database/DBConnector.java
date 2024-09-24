package de.affenherzog.knockbackffa.database;

import com.zaxxer.hikari.HikariDataSource;
import de.affenherzog.knockbackffa.Kffa;
import de.chojo.sadu.datasource.DataSourceCreator;
import de.chojo.sadu.mariadb.databases.MariaDb;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

public final class DBConnector {

  @Getter
  private final static DBConnector INSTANCE = new DBConnector();

  @Getter
  private final HikariDataSource dataSource;

  @Getter
  private final Connection connection;


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
        .withMaximumPoolSize(10)
        .withMinimumIdle(3)
        .build();

    try {
      this.connection = ((DataSource) dataSource).getConnection();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  public void close() {
    dataSource.close();
  }


}
