package de.crashmash.citybuild.storage;

import com.zaxxer.hikari.HikariDataSource;
import de.crashmash.citybuild.data.Data;
import net.pretronic.databasequery.api.Database;
import net.pretronic.databasequery.api.collection.DatabaseCollection;
import net.pretronic.databasequery.api.collection.field.FieldOption;
import net.pretronic.databasequery.api.datatype.DataType;
import net.pretronic.databasequery.api.driver.DatabaseDriver;
import net.pretronic.databasequery.api.driver.DatabaseDriverFactory;
import net.pretronic.databasequery.sql.dialect.Dialect;
import net.pretronic.databasequery.sql.driver.config.SQLDatabaseDriverConfigBuilder;
import org.bukkit.Bukkit;

import java.net.InetSocketAddress;

public class Storage {

    private DatabaseDriver databaseDriver;
    private Database database;

    private DatabaseCollection startKickCollection;

    public void createConnection() {
        Bukkit.getLogger();
        this.databaseDriver = DatabaseDriverFactory.create(Data.MYSQL_CONNECTION, new SQLDatabaseDriverConfigBuilder()
                .setDialect(Dialect.MYSQL)
                .setAddress(new InetSocketAddress(Data.MYSQL_HOST, Data.MYSQL_PORT))
                .setDataSourceClassName(HikariDataSource.class.getName())
                .setUsername(Data.MYSQL_USER)
                .setPassword(Data.MYSQL_PASSWORD)
                .build());
        this.databaseDriver.connect();
        this.database = databaseDriver.getDatabase(Data.MYSQL_DATABASE);

        createCollections();
    }

    public void createCollections() {
        this.startKickCollection = database.createCollection("StartKick")
                .field("UUID", DataType.UUID, FieldOption.PRIMARY_KEY)
                .field("Reason", DataType.STRING)
                .field("Duration", DataType.LONG)
                .create();
    }

    public boolean isConnected() {
        return this.databaseDriver.isConnected();
    }

    public void deleteConnection() {
        this.databaseDriver.disconnect();
    }

}
