package com.jubyte.citybuild.commands;

import com.jubyte.citybuild.CityBuildV2;
import com.jubyte.citybuild.data.ConfigData;
import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.developerapi.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Justin_SGD
 * @since 19.04.2023
 *
 * Bugfix: this command previously did nothing at all - it wasn't registered in
 * CityBuildV2#loadCommands(), had a constructor signature that didn't match how every other
 * command is instantiated (new XCommand() with no args), referenced permission/message keys
 * that didn't exist in messages.yml, and its onCommand() body never actually vanished anyone.
 * It has been reimplemented to match the rest of the codebase and to actually toggle
 * visibility for the executing player.
 */
public class VanishCommand extends AbstractCommand {

  private static final Set<UUID> VANISHED_PLAYERS = new HashSet<>();

  public VanishCommand() {
    super(
        ConfigData.CONFIG_COMMAND_VANISH_NAME,
        null,
        "Go into vanish mode.",
        ConfigData.CONFIG_COMMAND_VANISH_ALIASES);
  }

  public static Set<UUID> getVanishedPlayers() {
    return Collections.unmodifiableSet(VANISHED_PLAYERS);
  }

  public static boolean isVanished(UUID uuid) {
    return VANISHED_PLAYERS.contains(uuid);
  }

  /** Removes a player from the vanish list, e.g. when they log out, to avoid stale entries. */
  public static void clearVanish(UUID uuid) {
    VANISHED_PLAYERS.remove(uuid);
  }

  @Override
  public boolean onCommand(
      CommandSender commandSender, Command command, String s, String[] strings) {

    if (!(commandSender instanceof Player)) {
      commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
      return false;
    }

    Player player = (Player) commandSender;

    if (!player.hasPermission(MessagesData.VANISH_COMMAND_PERMISSION_USE)) {
      player.sendMessage(MessagesData.NOPERMS);
      return false;
    }

    UUID uuid = player.getUniqueId();

    if (VANISHED_PLAYERS.contains(uuid)) {
      VANISHED_PLAYERS.remove(uuid);
      for (Player online : Bukkit.getOnlinePlayers()) {
        online.showPlayer(CityBuildV2.getPlugin(), player);
      }
      player.sendMessage(MessagesData.VANISH_COMMAND_MESSAGE_DEACTIVATED);
    } else {
      VANISHED_PLAYERS.add(uuid);
      for (Player online : Bukkit.getOnlinePlayers()) {
        if (!online.hasPermission(MessagesData.VANISH_COMMAND_PERMISSION_USE)) {
          online.hidePlayer(CityBuildV2.getPlugin(), player);
        }
      }
      player.sendMessage(MessagesData.VANISH_COMMAND_MESSAGE_ACTIVATED);
    }

    return false;
  }
}
