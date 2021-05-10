package de.jubyte.citybuild.data;

import de.jubyte.citybuild.CityBuildV2;

public class MySQLData {

    public static String MYSQL_CONNECTION = CityBuildV2.getPLUGIN().getMySQLConfig().getString("MySQL.Connection");
    public static String MYSQL_HOST = CityBuildV2.getPLUGIN().getMySQLConfig().getString("MySQL.Host");
    public static int MYSQL_PORT = CityBuildV2.getPLUGIN().getMySQLConfig().getInt("MySQL.Port");
    public static String MYSQL_DATABASE = CityBuildV2.getPLUGIN().getMySQLConfig().getString("MySQL.Database");
    public static String MYSQL_USER = CityBuildV2.getPLUGIN().getMySQLConfig().getString("MySQL.User");
    public static String MYSQL_PASSWORD = CityBuildV2.getPLUGIN().getMySQLConfig().getString("MySQL.Password");

}
