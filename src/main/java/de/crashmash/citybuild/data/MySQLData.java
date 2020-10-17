package de.crashmash.citybuild.data;

import de.crashmash.citybuild.CityBuildV2;

public class MySQLData {

    public static String MYSQL_CONNECTION = CityBuildV2.getPlugin().getMySQLConfig().getString("MySQL.Connection");
    public static String MYSQL_HOST = CityBuildV2.getPlugin().getMySQLConfig().getString("MySQL.Host");
    public static int MYSQL_PORT = CityBuildV2.getPlugin().getMySQLConfig().getInt("MySQL.Port");
    public static String MYSQL_DATABASE = CityBuildV2.getPlugin().getMySQLConfig().getString("MySQL.Database");
    public static String MYSQL_USER = CityBuildV2.getPlugin().getMySQLConfig().getString("MySQL.User");
    public static String MYSQL_PASSWORD = CityBuildV2.getPlugin().getMySQLConfig().getString("MySQL.Password");

}
