package com.jubyte.citybuild.commands;

import com.jubyte.citybuild.data.ConfigData;
import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.developerapi.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author KeinByte
 * @since 24.12.2021
 */

public class SpeedCommand extends AbstractCommand {


    public SpeedCommand() {
        super(ConfigData.CONFIG_COMMAND_SPEED_NAME, null, "Set players speed", ConfigData.CONFIG_COMMAND_SPEED_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)){
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        }else{
            Player player = (Player) commandSender;
            if (player.hasPermission(MessagesData.SPEED_COMMAND_PERMISSION_USE)){
                if (strings.length == 0){
                    player.sendMessage(MessagesData.SPEED_COMMAND_USAGE);
                }else if (strings.length == 1){
                    if (strings[0].matches("[0-9]+")){
                        int SPEED = Integer.parseInt(strings[0]);
                        float REAL_SPEED = SPEED / 10.0F;
                        if (SPEED > 10 || SPEED < 1){
                            player.sendMessage(MessagesData.SPEED_COMMAND_NO_VALID_LEVEL);
                        }else{
                            if (player.isFlying()){
                                player.setFlySpeed(REAL_SPEED);
                                player.sendMessage(MessagesData.SPEED_COMMAND_FLY_SPEED_SET_SELF.replace("[speed]", String.valueOf(SPEED)));
                            }else{
                                player.setWalkSpeed(REAL_SPEED);
                                player.sendMessage(MessagesData.SPEED_COMMAND_WALK_SPEED_SET_SELF.replace("[speed]", String.valueOf(SPEED)));
                            }
                        }
                    }else{
                        player.sendMessage(MessagesData.SPEED_COMMAND_NO_VALID_LEVEL);
                    }
                }else if (strings.length == 2){
                    if (player.hasPermission(MessagesData.SPEED_COMMAND_PERMISSION_USE_OTHER)){
                        if (strings[0].matches("[0-9]+")){
                            Player targetPlayer = Bukkit.getPlayer(strings[1]);
                            if (targetPlayer != null){
                                int SPEED = Integer.parseInt(strings[0]);
                                float REAL_SPEED = SPEED / 10.0F;
                                if (SPEED > 10 || SPEED < 1){
                                    player.sendMessage(MessagesData.SPEED_COMMAND_NO_VALID_LEVEL);
                                }else{
                                    if (player.isFlying()){
                                        targetPlayer.setFlySpeed(REAL_SPEED);
                                        targetPlayer.sendMessage(MessagesData.SPEED_COMMAND_FLY_SPEED_SET_SELF.replace("[speed]", String.valueOf(SPEED)));
                                        player.sendMessage(MessagesData.SPEED_COMMAND_FLY_SPEED_SET_OTHER.replace("[speed]", String.valueOf(SPEED).replace("[targetPlayer]", targetPlayer.getName())));
                                    }else{
                                        targetPlayer.setWalkSpeed(REAL_SPEED);
                                        targetPlayer.sendMessage(MessagesData.SPEED_COMMAND_WALK_SPEED_SET_SELF.replace("[speed]", String.valueOf(SPEED)));
                                        player.sendMessage(MessagesData.SPEED_COMMAND_WALK_SPEED_SET_OTHER.replace("[speed]", String.valueOf(SPEED).replace("[targetPlayer]", targetPlayer.getName())));
                                    }
                                }
                            }else{
                                player.sendMessage(MessagesData.SPEED_COMMAND_PLAYER_OFFLINE.replace("[targetPlayer]", strings[1]));
                            }
                        }else{
                            player.sendMessage(MessagesData.SPEED_COMMAND_NO_VALID_LEVEL);
                        }
                    }else{
                        player.sendMessage(MessagesData.NOPERMS);
                    }
                 }else{
                    player.sendMessage(MessagesData.SPEED_COMMAND_USAGE);
                }
            }else{
                player.sendMessage(MessagesData.NOPERMS);
            }
        }

        return false;
    }
}
