package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.data.ConfigData;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.developerapi.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GameModeCommand extends AbstractCommand {

    public GameModeCommand() {
        super(ConfigData.CONFIG_COMMAND_GAMEMODE_NAME, null, "For switching gamemodes of players", ConfigData.CONFIG_COMMAND_GAMEMODE_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)){
            if (strings.length == 2){
                if (commandSender.hasPermission(MessagesData.GAMEMODE_COMMAND_PERMISSION_USE_OTHER)){

                    Player target = Bukkit.getPlayer(strings[1]);

                    if (target != null){
                        if (strings[0].equalsIgnoreCase("0")){
                            target.setGameMode(GameMode.SURVIVAL);
                            target.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_SURVIVAL_SELF);
                            commandSender.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_SURVIVAL_OTHER.replace("[targetPlayer]", target.getName()));
                        }else if (strings[0].equalsIgnoreCase("1")){
                            target.setGameMode(GameMode.CREATIVE);
                            target.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_CREATIVE_SELF);
                            commandSender.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_CREATIVE_OTHER.replace("[targetPlayer]", target.getName()));
                        }else if (strings[0].equalsIgnoreCase("2")){
                            target.setGameMode(GameMode.ADVENTURE);
                            target.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_ADVENTURE_SELF);
                            commandSender.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_ADVENTURE_OTHER.replace("[targetPlayer]", target.getName()));
                        }else if (strings[0].equalsIgnoreCase("3")){
                            target.setGameMode(GameMode.SPECTATOR);
                            target.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_SPECTATOR_SELF);
                            commandSender.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_SPECTATOR_OTHER.replace("[targetPlayer]", target.getName()));

                        }else{
                            commandSender.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_USAGE);
                        }
                    }else{
                        commandSender.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_PLAYER_NOT_FOUND);
                    }

                }else{
                    commandSender.sendMessage(MessagesData.NOPERMS);
                }
            }else{
                commandSender.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_USAGE);
            }
        }else{
            Player player = (Player) commandSender;
            if (player.hasPermission(MessagesData.GAMEMODE_COMMAND_PERMISSION_USE_SELF)){
                if (strings.length == 1){

                    if (strings[0].equalsIgnoreCase("0")){
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_SURVIVAL_SELF);
                    }else if (strings[0].equalsIgnoreCase("1")){
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_CREATIVE_SELF);
                    }else if (strings[0].equalsIgnoreCase("2")){
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_ADVENTURE_SELF);
                    }else if (strings[0].equalsIgnoreCase("3")){
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_SPECTATOR_SELF);
                    }else{
                        player.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_USAGE);
                    }

                }else if (strings.length == 2){
                    if (player.hasPermission(MessagesData.GAMEMODE_COMMAND_PERMISSION_USE_OTHER)){

                        Player target = Bukkit.getPlayer(strings[1]);

                        if (target != null){
                            if (strings[0].equalsIgnoreCase("0")){
                                target.setGameMode(GameMode.SURVIVAL);
                                target.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_SURVIVAL_SELF);
                                player.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_SURVIVAL_OTHER.replace("[targetPlayer]", target.getName()));
                            }else if (strings[0].equalsIgnoreCase("1")){
                                target.setGameMode(GameMode.CREATIVE);
                                target.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_CREATIVE_SELF);
                                player.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_CREATIVE_OTHER.replace("[targetPlayer]", target.getName()));
                            }else if (strings[0].equalsIgnoreCase("2")){
                                target.setGameMode(GameMode.ADVENTURE);
                                target.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_ADVENTURE_SELF);
                                player.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_ADVENTURE_OTHER.replace("[targetPlayer]", target.getName()));
                            }else if (strings[0].equalsIgnoreCase("3")){
                                target.setGameMode(GameMode.SPECTATOR);
                                target.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_SPECTATOR_SELF);
                                player.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_SPECTATOR_OTHER.replace("[targetPlayer]", target.getName()));

                            }else{
                                player.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_USAGE);
                            }
                        }else{
                            player.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_PLAYER_NOT_FOUND);
                        }

                    }else{
                        player.sendMessage(MessagesData.NOPERMS);
                    }
                }else{
                    player.sendMessage(MessagesData.GAMEMODE_COMMAND_MESSAGE_USAGE);
                }
            }else{
                player.sendMessage(MessagesData.NOPERMS);
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 2) {
            final List<String> players = new ArrayList<>();
            for(Player all : Bukkit.getOnlinePlayers()) {
                players.add(all.getName());
            }
            return players;
        }
        return null;
    }

}
