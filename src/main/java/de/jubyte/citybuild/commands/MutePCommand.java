package de.jubyte.citybuild.commands;

import com.jubyte.developerapi.commands.AbstractCommand;
import de.jubyte.citybuild.CityBuildV2;
import de.jubyte.citybuild.data.ConfigData;
import de.jubyte.citybuild.data.MessagesData;
import de.jubyte.citybuild.manager.mutep.MutepPlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MutePCommand extends AbstractCommand {

    private String reasons = "";

    public MutePCommand() {
        super(ConfigData.CONFIG_COMMAND_MUTEP_NAME, null, "Player can mute other player temporary.", ConfigData.CONFIG_COMMAND_MUTEP_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        } else {
            Player player = (Player) commandSender;
            if(player.hasPermission(MessagesData.MUTEP_COMMAND_PERMISSION_USE)) {
                if (strings.length >= 2) {
                    MutepPlayer mutepPlayer = CityBuildV2.getPLUGIN().getMutePCache().getPlayerByUUID(player.getUniqueId());
                    if(mutepPlayer.hasCooldown(player)) {
                        Player targetPlayer = Bukkit.getPlayer(strings[0]);
                        if(targetPlayer != null) {
                            if(!targetPlayer.equals(player)) {
                                if (!targetPlayer.hasPermission(MessagesData.MUTEP_COMMAND_PERMISSION_BYPASS_MUTE)) {
                                    MutepPlayer mutepTargetPlayer = CityBuildV2.getPLUGIN().getMutePCache().getPlayerByUUID(targetPlayer.getUniqueId());
                                    if(!mutepTargetPlayer.playerIsMutep()) {
                                        StringBuilder reason = new StringBuilder();
                                        for (int i = 1; i < strings.length; i++) {
                                            reason.append(" ").append(strings[i]);
                                        }
                                        reasons = "" + reason;
                                        mutepPlayer.setCooldown();
                                        mutepTargetPlayer.playerMutep(reasons, player);
                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                            players.sendMessage(MessagesData.MUTEP_COMMAND_MESSAGE_MUTE_SUCCESFUL.replace("[player]", player.getDisplayName()).replace("[targetPlayer]", targetPlayer.getDisplayName())
                                                    .replace("[reason]", reasons).replace("[duration]", Integer.toString(MessagesData.MUTEP_COMMAND_SETTINGS_DURATION / 60)));
                                        }
                                        reasons = "";
                                    } else {
                                        player.sendMessage(MessagesData.MUTEP_COMMAND_MESSAGE_PLAYER_IS_MUTED.replace("[targetPlayer]", targetPlayer.getDisplayName()));
                                    }
                                } else {
                                    player.sendMessage(MessagesData.MUTEP_COMMAND_MESSAGE_MUTE_BYPASS.replace("[targetPlayer]", targetPlayer.getDisplayName()));
                                }
                            } else {
                                player.sendMessage(MessagesData.MUTEP_COMMAND_MESSAGE_SELF_MUTE);
                            }
                        } else {
                            player.sendMessage(MessagesData.MUTEP_COMMAND_MESSAGE_TARGET_PLAYER_OFFLINE.replace("[targetPlayer]", strings[0]));
                        }
                    } else {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
                        long time = mutepPlayer.getCooldown();
                        player.sendMessage(MessagesData.MUTEP_COMMAND_MESSAGE_COOLDOWN.replace("[date]", simpleDateFormat.format(time))
                                .replace("[time]", simpleTimeFormat.format(time)));
                    }
                } else {
                    player.sendMessage(MessagesData.MUTEP_COMMAND_MESSAGE_USAGE);
                }
            } else {
                player.sendMessage(MessagesData.NOPERMS);
            }
        }
        return false;
    }

    @Override
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