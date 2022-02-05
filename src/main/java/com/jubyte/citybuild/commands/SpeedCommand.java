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
            if (player.hasPermission("speed.use")){
                if (strings.length == 0){
                    player.sendMessage("/speed <1-10> <Spieler>");
                }else if (strings.length == 1){
                    if (strings[0].matches("[0-9]+")){
                        int SPEED = Integer.parseInt(strings[0]);
                        float REAL_SPEED = SPEED / 10.0F;
                        if (SPEED > 10 || SPEED < 1){
                            player.sendMessage("§cEs sind nur Werte von 1 bis 10 gültig!");
                        }else{
                            if (player.isFlying()){
                                player.setFlySpeed(REAL_SPEED);
                                player.sendMessage("§aDeine Fluggeschwindigkeit wurde auf §6" + SPEED + " §agesetzt!");
                            }else{
                                player.setWalkSpeed(REAL_SPEED);
                                player.sendMessage("§aDeine Laufgeschwindigkeit wurde auf §6" + SPEED + " §agesetzt!");
                            }
                        }
                    }else{
                        player.sendMessage("KEIN GÜLTIGER WERT");
                    }
                }else if (strings.length == 2){
                    if (player.hasPermission("speed.use.other")){
                        if (strings[0].matches("[0-9]+")){
                            Player targetPlayer = Bukkit.getPlayer(strings[1]);
                            if (targetPlayer != null){
                                int SPEED = Integer.parseInt(strings[0]);
                                float REAL_SPEED = SPEED / 10.0F;
                                if (SPEED > 10 || SPEED < 1){
                                    player.sendMessage("§cEs sind nur Werte von 1 bis 10 gültig!");
                                }else{
                                    if (player.isFlying()){
                                        targetPlayer.setFlySpeed(REAL_SPEED);
                                        targetPlayer.sendMessage("§aDeine Fluggeschwindigkeit wurde auf §6" + SPEED + " §agesetzt!");
                                        targetPlayer.sendMessage("§aDu hast die Fluggeschwindigkeit für §6" + targetPlayer.getName() + "  §aauf §6" + SPEED + " §agesetzt!");
                                    }else{
                                        targetPlayer.setWalkSpeed(REAL_SPEED);
                                        targetPlayer.sendMessage("§aDeine Laufgeschwindigkeit wurde auf §6" + SPEED + " §agesetzt!");
                                        targetPlayer.sendMessage("§aDu hast die Laufgeschwindigkeit für §6" + targetPlayer.getName() + "  §aauf §6" + SPEED + " §agesetzt!");
                                    }
                                }
                            }else{
                                player.sendMessage("§cDieser Spieler ist nicht online!");
                            }
                        }else{
                            player.sendMessage("KEIN GÜLTIGER WERT");
                        }
                    }else{
                        player.sendMessage("KEINE RECHTE");
                    }
                 }else{
                    player.sendMessage("USAGE");
                }
            }else{
                player.sendMessage("KEINE RECHTE");
            }
        }

        return false;
    }
}
