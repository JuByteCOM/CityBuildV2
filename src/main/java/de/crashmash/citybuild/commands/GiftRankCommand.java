package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.manager.cooldown.CooldownPlayer;
import de.crashmash.developerapi.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;

public class GiftRankCommand extends AbstractCommand {

    public GiftRankCommand() {
        super("giftrank", "usage", "description");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)){
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        }else{
            Player player = (Player) commandSender;
            if (player.hasPermission(MessagesData.GIFTRANK_COMMAND_PERMISSION_USE)){
                if (strings.length == 1){
                    Player targetPlayer = Bukkit.getPlayer(strings[0]);
                    if (targetPlayer != null){
                        CooldownPlayer cooldownPlayer = CityBuildV2.getPLUGIN().getCooldownCache().getPlayerByUUID(player.getUniqueId());
                        if (cooldownPlayer.hasGiftRankCooldown(player)){
                            System.out.println(MessagesData.GIFTRANK_COMMAND_SETTINGS_DISPATCHCOMMAND);
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), MessagesData.GIFTRANK_COMMAND_SETTINGS_DISPATCHCOMMAND.replace("[targetPlayer]", targetPlayer.getName()));
                            player.sendMessage(MessagesData.GIFTRANK_COMMAND_MESSAGE_ADDED_RANK.replace("[targetPlayer]", targetPlayer.getName()));
                            cooldownPlayer.setGiftRank();
                            targetPlayer.kickPlayer(MessagesData.GIFTRANK_COMMAND_MESSAGE_KICK_SCREEN.replace("[targetPlayer]", targetPlayer.getName()));
                        }else{
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
                            long time = cooldownPlayer.getGiftRank();
                            player.sendMessage(MessagesData.GIFTRANK_COMMAND_MESSAGE_COOLDOWN.replace("[date]", simpleDateFormat.format(time))
                                    .replace("[time]", simpleTimeFormat.format(time)));
                        }
                    }else{
                        player.sendMessage(MessagesData.GIFTRANK_COMMAND_MESSAGE_TARGETPLAYER_OFFLINE.replace("[targetPlayer]", targetPlayer.getName()));
                    }
                }else{
                    player.sendMessage(MessagesData.GIFTRANK_COMMAND_MESSAGE_USAGE);
                }
            }else{
                player.sendMessage(MessagesData.NOPERMS);
            }
        }

        return false;
    }
}
