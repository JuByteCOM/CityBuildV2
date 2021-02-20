package de.crashmash.citybuild.listener;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.commands.SlowChatCommand;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.utils.ColoredChat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class AsynPlayerChatListener implements Listener {

    public static final List<Player> SLOWCHAT_PLAYER = new ArrayList<>();

    @EventHandler
    public void handleAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        event.setMessage(ColoredChat.format(event.getMessage(), player));

        if(SlowChatCommand.SLOWCHAT_STATUS) {
            if(SLOWCHAT_PLAYER.contains(player)) {
                player.sendMessage(MessagesData.SLOWCHAT_COMMAND_MESSAGE_CHATTET_TO_FAST);
                event.setCancelled(true);
            }
        }
        if(SlowChatCommand.SLOWCHAT_STATUS) {
            if(!player.hasPermission(MessagesData.SLOWCHAT_COMMAND_PERMISSION_BYPASS)) {
                System.out.println("Keine Rechte");
                if(!SLOWCHAT_PLAYER.contains(player)) {
                    SLOWCHAT_PLAYER.add(player);
                    Bukkit.getScheduler().runTaskLater(CityBuildV2.getPlugin(), new Runnable() {

                        @Override
                        public void run() {
                            SLOWCHAT_PLAYER.remove(player);
                        }
                    }, MessagesData.SLOWCHAT_COMMAND_SETTINGS_CHAT_COOLDOWN * 20);
                }
            }
        }
    }
}
