package de.crashmash.citybuild.storage;

import com.zaxxer.hikari.HikariDataSource;
import de.crashmash.citybuild.data.MySQLData;
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
    private DatabaseCollection statusCollection;
    private DatabaseCollection boosterCollection;
    private DatabaseCollection foodCollection;
    private DatabaseCollection cooldownCollection;

    public void createConnection() {
        Bukkit.getLogger();
        this.databaseDriver = DatabaseDriverFactory.create(MySQLData.MYSQL_CONNECTION, new SQLDatabaseDriverConfigBuilder()
                .setDialect(Dialect.MYSQL)
                .setAddress(new InetSocketAddress(MySQLData.MYSQL_HOST, MySQLData.MYSQL_PORT))
                .setDataSourceClassName(HikariDataSource.class.getName())
                .setUsername(MySQLData.MYSQL_USER)
                .setPassword(MySQLData.MYSQL_PASSWORD)
                .build());
        this.databaseDriver.connect();
        this.database = databaseDriver.getDatabase(MySQLData.MYSQL_DATABASE);

        createCollections();
    }

    public void createCollections() {
        this.startKickCollection = database.createCollection("StartKick")
                .field("UUID", DataType.UUID, FieldOption.PRIMARY_KEY)
                .field("Reason", DataType.STRING)
                .field("Duration", DataType.LONG)
                .field("Cooldown", DataType.LONG)
                .create();

        this.statusCollection = database.createCollection("Status")
                .field("UUID", DataType.UUID, FieldOption.PRIMARY_KEY)
                .field("Status", DataType.STRING)
                .create();

        this.boosterCollection = database.createCollection("Booster")
                .field("UUID", DataType.UUID, FieldOption.PRIMARY_KEY)
                .field("BoosterID", DataType.INTEGER)
                .field("Amount", DataType.INTEGER)
                .create();

        this.foodCollection = database.createCollection("Food")
                .field("ID", DataType.INTEGER, FieldOption.AUTO_INCREMENT, FieldOption.PRIMARY_KEY)
                .field("LocX", DataType.DOUBLE)
                .field("LocY", DataType.DOUBLE)
                .field("LocZ", DataType.DOUBLE)
                .field("Yaw", DataType.FLOAT)
                .field("Pitch", DataType.FLOAT)
                .field("World", DataType.STRING)
                .create();

        this.cooldownCollection = database.createCollection("Cooldowns")
                .field("UUID", DataType.UUID, FieldOption.PRIMARY_KEY)
                .field("Head", DataType.LONG)
                .field("BreakBlock", DataType.LONG)
                .field("MuteP", DataType.LONG)
                .create();
    }

    public boolean isConnected() {
        return this.databaseDriver.isConnected();
    }

    public void deleteConnection() {
        this.databaseDriver.disconnect();
    }

    public DatabaseCollection getStatusCollection() {
        return statusCollection;
    }

    public DatabaseCollection getBoosterCollection() {
        return boosterCollection;
    }

    public DatabaseCollection getFoodCollection() {
        return foodCollection;
    }

    public DatabaseCollection getStartKickCollection() {
        return startKickCollection;
    }

    public DatabaseCollection getcooldownCollection() {
        return cooldownCollection;
    }
}
