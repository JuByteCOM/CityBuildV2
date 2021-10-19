package com.jubyte.citybuild.commands;

import com.jubyte.citybuild.CityBuildV2;
import com.jubyte.citybuild.data.ConfigData;
import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.citybuild.manager.food.FoodLocation;
import com.jubyte.citybuild.storage.FoodSQL;
import com.jubyte.developerapi.commands.AbstractCommand;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import java.util.Objects;

public class FoodCommand extends AbstractCommand {

  public FoodCommand() {
    super(
        ConfigData.CONFIG_COMMAND_FOOD_NAME,
        null,
        "Enter /food for an overview of commands!",
        ConfigData.CONFIG_COMMAND_FOOD_ALIASES);
  }

  @Override
  public boolean onCommand(
      CommandSender commandSender, Command command, String s, String[] strings) {
    if (!(commandSender instanceof Player)) {
      commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
    } else {
      Player player = (Player) commandSender;
      if (player.hasPermission(MessagesData.FOOD_COMMAND_PERMISSION_USE)) {
        if (strings.length == 0 || strings.length > 2) {
          player.sendMessage(MessagesData.FOOD_COMMAND_MESSAGE_HELP);
        } else if (strings.length == 1) {
          if (strings[0].equalsIgnoreCase("set")) {
            LivingEntity entity = player.getWorld().spawn(player.getLocation(), Pig.class);
            entity.setCustomName(MessagesData.FOOD_COMMAND_MESSAGE_NAME);
            entity.setAI(false);
            entity.setHealth(MessagesData.FOOD_COMMAND_SETTINGS_HEALTH);
            double LocX = player.getLocation().getX();
            double LocY = player.getLocation().getY();
            double LocZ = player.getLocation().getZ();
            float Yaw = player.getLocation().getYaw();
            float Pitch = player.getLocation().getPitch();
            String World = Objects.requireNonNull(player.getLocation().getWorld()).getName();
            FoodSQL.createFood(LocX, LocY, LocZ, Yaw, Pitch, World);
            FoodLocation.FOOD_LOCATIONS.clear();
            CityBuildV2.getPLUGIN().loadLocations();
            player.sendMessage(MessagesData.FOOD_COMMAND_MESSAGE_SPAWNED);
          } else if (strings[0].equalsIgnoreCase("list")) {
            for (int iD : FoodLocation.getLocationNames()) {
              double x = FoodLocation.getLocation(iD).getX();
              double y = FoodLocation.getLocation(iD).getY();
              double z = FoodLocation.getLocation(iD).getZ();
              TextComponent textComponent =
                  new TextComponent(
                      TextComponent.fromLegacyText(
                          MessagesData.FOOD_COMMAND_MESSAGE_LIST.replace(
                              "[id]", String.valueOf(iD))));
              textComponent.setHoverEvent(
                  new HoverEvent(
                      HoverEvent.Action.SHOW_TEXT,
                      new ComponentBuilder(MessagesData.FOOD_COMMAND_MESSAGE_HOVER).create()));
              textComponent.setClickEvent(
                  new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
              player.spigot().sendMessage(textComponent);
            }
          }
        } else if (strings[0].equalsIgnoreCase("remove")) {
          try {
            if (FoodLocation.exitsLocation(Integer.parseInt(strings[1]))) {
              Location location = FoodLocation.getLocation(Integer.parseInt(strings[1]));
              World world = FoodLocation.getLocation(Integer.parseInt(strings[1])).getWorld();
              for (Entity entity : world.getNearbyEntities(location, 1, 1, 1)) {
                if (entity.getType() == EntityType.PIG
                    && entity
                        .getCustomName()
                        .equalsIgnoreCase(MessagesData.FOOD_COMMAND_MESSAGE_NAME)) {
                  entity.remove();
                }
              }
              FoodLocation.FOOD_LOCATIONS.remove(Integer.parseInt(strings[1]));
              FoodSQL.deleteFood(Integer.parseInt(strings[1]));
              player.sendMessage(
                  MessagesData.FOOD_COMMAND_MESSAGE_REMOVED.replace("[id]", strings[1]));
            }
          } catch (NumberFormatException exception) {
            player.sendMessage(MessagesData.FOOD_COMMAND_MESSAGE_NUMBERFORMATEXCEPTION);
          }
        } else {
          player.sendMessage(MessagesData.NOPERMS);
        }
      }
    }
    return false;
  }
}
