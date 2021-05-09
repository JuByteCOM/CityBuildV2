package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.manager.cooldown.Cooldown;
import de.crashmash.developerapi.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.List;

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
                if (strings.length == 0){
                    if (player.hasPermission(MessagesData.HEAD_COMMAND_PERMISSION_BYPASS)){
                        player.sendMessage(MessagesData.GIFTRANK_COMMAND_MESSAGE_HAS_BYPASS);
                    }else{
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
                        long time = Cooldown.getGiftRankCooldown(player) + MessagesData.HEAD_COMMAND_SETTINGS_COOLDOWN;
                        player.sendMessage(MessagesData.GIFTRANK_COMMAND_MESSAGE_COOLDOWN.replace("[date]", simpleDateFormat.format(time))
                                .replace("[time]", simpleTimeFormat.format(time)));
                    }
                }else if (strings.length == 1){
                    Player target = Bukkit.getPlayer(strings[0]);
                    if (target != null){
                        if (Cooldown.canUseGiftRank(player)){
                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), MessagesData.GIFTRANK_COMMAND_SETTINGS_DISPATCH_RANK);
                            player.sendMessage(MessagesData.GIFTRANK_COMMAND_MESSAGE_ADDED_RANK);
                            Cooldown.setGiftRankCooldown(player);
                            target.kickPlayer(MessagesData.GIFTRANK__COMMAND_MESSAGE_PLAYER_KICKSCREEN.replace("[targetPlayer]",target.getName()));
                        }else{
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
                            long time = Cooldown.getGiftRankCooldown(player) + MessagesData.HEAD_COMMAND_SETTINGS_COOLDOWN;
                            player.sendMessage(MessagesData.GIFTRANK_COMMAND_MESSAGE_COOLDOWN.replace("[date]", simpleDateFormat.format(time))
                                    .replace("[time]", simpleTimeFormat.format(time)));
                        }
                    }else{
                        player.sendMessage(MessagesData.GIFTRANK_COMMAND_MESSAGE_TARGETPLAYER_OFFLINE);
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
