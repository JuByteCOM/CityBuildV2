package de.jubyte.citybuild.commands;

import de.jubyte.citybuild.CityBuildV2;
import de.jubyte.citybuild.data.ConfigData;
import de.jubyte.citybuild.data.MessagesData;
import de.jubyte.citybuild.manager.cooldown.CooldownPlayer;
import de.crashmash.developerapi.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GiftRankCommand extends AbstractCommand {

    public GiftRankCommand() {
        super(ConfigData.CONFIG_COMMAND_GIFTRANK_NAME, null, "With this command, you can gift a rank.", ConfigData.CONFIG_COMMAND_GIFTRANK_ALIASES);
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
                            if (targetPlayer.hasPermission(MessagesData.GIFTRANK_COMMAND_PERMISSION_HIGHER_RANK)){
                                player.sendMessage(MessagesData.GIFTRANK_COMMAND_MESSAGE_HIGHER_RANK);
                                return true;
                            }
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), MessagesData.GIFTRANK_COMMAND_SETTINGS_DISPATCHCOMMAND.replace("[targetPlayer]", targetPlayer.getName()));
                            player.sendMessage(MessagesData.GIFTRANK_COMMAND_MESSAGE_ADDED_RANK.replace("[targetPlayer]", targetPlayer.getName()));
                            cooldownPlayer.setGiftRank();
                            if(MessagesData.GIFTRANK_COMMAND_SETTING_KICK)
                            targetPlayer.kickPlayer(MessagesData.GIFTRANK_COMMAND_MESSAGE_KICK_SCREEN.replace("[targetPlayer]", player.getName()));
                        }else{
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
                            long time = cooldownPlayer.getGiftRank();
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

    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            final List<String> players = new ArrayList<>();
            for(Player all : Bukkit.getOnlinePlayers()) {
                players.add(all.getName());
            }
            return players;
        }
        return null;
    }
}
