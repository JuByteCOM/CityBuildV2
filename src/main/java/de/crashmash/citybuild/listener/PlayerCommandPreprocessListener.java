package de.crashmash.citybuild.listener;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.MessagesData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Map;

public class PlayerCommandPreprocessListener implements Listener {

    private final Map<Player, Player> commandSpy_Map = CityBuildV2.getPLUGIN().getCOMMANDSPY_MAP();

    @EventHandler
    public void handlePlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission(MessagesData.COMMANDSPY_COMMAND_PERMISSION_BYPASS)) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (commandSpy_Map.containsKey(players)) {
                    for (Map.Entry<Player, Player> entry : commandSpy_Map.entrySet()) {
                        if (entry.getValue() == null) {
                            players.sendMessage(MessagesData.COMMANDSPY_COMMAND_MESSAGE_COMMAND.replace("[targetPlayer]", player.getName())
                                    .replace("[command]", event.getMessage()));
                        } else if (entry.getValue() == event.getPlayer()) {
                            players.sendMessage(MessagesData.COMMANDSPY_COMMAND_MESSAGE_COMMAND.replace("[targetPlayer]", player.getName())
                                    .replace("[command]", event.getMessage()));
                        }
                    }
                }
            }
        }
    }
}
