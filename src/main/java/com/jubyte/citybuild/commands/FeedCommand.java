package com.jubyte.citybuild.commands;

import com.jubyte.citybuild.data.ConfigData;
import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.developerapi.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KeinByte
 * @since 12.06.2021
 */
public class FeedCommand extends AbstractCommand {

  public FeedCommand() {
    super(
        ConfigData.CONFIG_COMMAND_FEED_NAME,
        null,
        "Footed an other player",
        ConfigData.CONFIG_COMMAND_FEED_ALIASES);
  }

  @Override
  public boolean onCommand(
      CommandSender commandSender, Command command, String s, String[] strings) {

    if (!(commandSender instanceof Player)) {
      commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
    } else {

      Player player = (Player) commandSender;

      if (player.hasPermission(MessagesData.FEED_COMMAND_PERMISSION_SELF)) {
        if (strings.length == 0) {

          player.setFoodLevel(20);
          player.sendMessage(MessagesData.FEED_COMMAND_MESSAGE_SELF);

        } else if (strings.length == 1) {
          if (player.hasPermission(MessagesData.FEED_COMMAND_PERMISSION_OTHER)) {

            Player target = Bukkit.getPlayer(strings[0]);

            if (target != null) {

              target.setFoodLevel(20);
              target.sendMessage(MessagesData.FEED_COMMAND_MESSAGE_SELF);
              player.sendMessage(
                  MessagesData.FEED_COMMAND_MESSAGE_OTHER.replace("[targetPlayer]", strings[0]));

            } else {
              player.sendMessage(MessagesData.FEED_COMMAND_MESSAGE_TARGETPLAYER_OFFLINE);
            }

          } else {
            player.sendMessage(MessagesData.NOPERMS);
          }
        } else {
          player.sendMessage(MessagesData.FEED_COMMAND_MESSAGE_USAGE);
        }
      } else {
        player.sendMessage(MessagesData.NOPERMS);
      }
    }

    return false;
  }

  @Override
  public List<String> onTabComplete(
      CommandSender commandSender, Command command, String s, String[] strings) {
    if (strings.length == 1) {
      final List<String> players = new ArrayList<>();
      for (Player all : Bukkit.getOnlinePlayers()) {
        players.add(all.getName());
      }
      return players;
    }
    return null;
  }
}
