package com.jubyte.citybuild.storage;

import com.jubyte.citybuild.CityBuildV2;
import com.zaxxer.hikari.HikariDataSource;
import net.pretronic.databasequery.api.Database;
import net.pretronic.databasequery.api.collection.DatabaseCollection;
import net.pretronic.databasequery.api.collection.field.FieldOption;
import net.pretronic.databasequery.api.datatype.DataType;
import net.pretronic.databasequery.api.driver.DatabaseDriver;
import net.pretronic.databasequery.api.driver.DatabaseDriverFactory;
import net.pretronic.databasequery.sql.dialect.Dialect;
import net.pretronic.databasequery.sql.driver.config.SQLDatabaseDriverConfigBuilder;

import java.io.File;
import java.net.InetSocketAddress;

public class Storage {

  private DatabaseDriver databaseDriver;
  private Database database;

  private DatabaseCollection startKickCollection;
  private DatabaseCollection statusCollection;
  private DatabaseCollection boosterCollection;
  private DatabaseCollection foodCollection;
  private DatabaseCollection cooldownCollection;
  private DatabaseCollection mutePCollection;
  private DatabaseCollection glowCollection;
  private DatabaseCollection locationCollection;
  private DatabaseCollection playerInformation;

  public void createConnection() {
    this.databaseDriver =
        DatabaseDriverFactory.create(
            CityBuildV2.getPlugin().getMySQLConfig().getString("MySQL.Connection"),
            new SQLDatabaseDriverConfigBuilder()
                .setDialect(Dialect.byName(CityBuildV2.getPlugin().getMySQLConfig().getString("MySQL.dialectName")))
                .setLocation(new File(CityBuildV2.getPlugin().getDataFolder() + "/datafolder"))
                .setAddress(new InetSocketAddress(CityBuildV2.getPlugin().getMySQLConfig().getString("MySQL.Host"),
                        CityBuildV2.getPlugin().getMySQLConfig().getInt("MySQL.Port")))
                .setDataSourceClassName(HikariDataSource.class.getName())
                .setUsername(CityBuildV2.getPlugin().getMySQLConfig().getString("MySQL.User"))
                .setPassword(CityBuildV2.getPlugin().getMySQLConfig().getString("MySQL.Password"))
                .setUseSSL(CityBuildV2.getPlugin().getMySQLConfig().getBoolean("MySQL.useSSL"))
                .build());

    this.databaseDriver.connect();
    this.database = databaseDriver.getDatabase(CityBuildV2.getPlugin().getMySQLConfig().getString("MySQL.Database"));

    createCollections();
  }

  public void createCollections() {
    this.startKickCollection =
        database
            .createCollection("StartKick")
            .field("UUID", DataType.UUID, FieldOption.PRIMARY_KEY)
            .field("Reason", DataType.STRING)
            .field("Duration", DataType.LONG)
            .field("Cooldown", DataType.LONG)
            .create();

    this.statusCollection =
        database
            .createCollection("Status")
            .field("UUID", DataType.UUID, FieldOption.PRIMARY_KEY)
            .field("Status", DataType.STRING)
            .create();

    this.boosterCollection =
        database
            .createCollection("Booster")
            .field("UUID", DataType.UUID, FieldOption.PRIMARY_KEY)
            .field("BoosterID", DataType.INTEGER)
            .field("Amount", DataType.INTEGER)
            .create();

    this.foodCollection =
        database
            .createCollection("Food")
            .field("ID", DataType.INTEGER, FieldOption.AUTO_INCREMENT, FieldOption.PRIMARY_KEY)
            .field("LocX", DataType.DOUBLE)
            .field("LocY", DataType.DOUBLE)
            .field("LocZ", DataType.DOUBLE)
            .field("Yaw", DataType.FLOAT)
            .field("Pitch", DataType.FLOAT)
            .field("World", DataType.STRING)
            .create();

    this.cooldownCollection =
        database
            .createCollection("Cooldowns")
            .field("UUID", DataType.UUID, FieldOption.PRIMARY_KEY)
            .field("Head", DataType.LONG)
            .field("BreakBlock", DataType.LONG)
            .field("GiftRank", DataType.LONG)
            .field("ClearChat", DataType.LONG)
            .create();

    this.mutePCollection =
        database
            .createCollection("MuteP")
            .field("UUID", DataType.UUID, FieldOption.PRIMARY_KEY)
            .field("Reason", DataType.STRING)
            .field("Duration", DataType.LONG)
            .field("Cooldown", DataType.LONG)
            .field("Creator", DataType.UUID)
            .create();

    this.glowCollection =
        database
            .createCollection("Glow")
            .field("UUID", DataType.UUID, FieldOption.PRIMARY_KEY)
            .field("State", DataType.BOOLEAN)
            .create();

    this.locationCollection =
        database
            .createCollection("Locations")
            .field("Name", DataType.STRING, FieldOption.PRIMARY_KEY)
            .field("LocX", DataType.DOUBLE)
            .field("LocY", DataType.DOUBLE)
            .field("LocZ", DataType.DOUBLE)
            .field("Yaw", DataType.FLOAT)
            .field("Pitch", DataType.FLOAT)
            .field("World", DataType.STRING)
            .create();

    this.playerInformation =
        database
            .createCollection("PlayerInformations")
            .field("UUID", DataType.UUID, FieldOption.PRIMARY_KEY)
            .field("FirstJoin", DataType.LONG)
            .field("LastJoin", DataType.LONG)
            .field("Playtime", DataType.LONG)
            .create();
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

  public DatabaseCollection getMutePCollection() {
    return mutePCollection;
  }

  public DatabaseCollection getPlayerInformation() {
    return playerInformation;
  }

  public DatabaseCollection getGlowCollection() {
    return glowCollection;
  }

  public DatabaseCollection getLocationCollection() {
    return locationCollection;
  }
}
