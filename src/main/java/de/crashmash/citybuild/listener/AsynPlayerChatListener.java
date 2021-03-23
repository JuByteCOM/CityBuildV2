package de.crashmash.citybuild.listener;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.commands.SlowChatCommand;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.manager.cooldown.Cooldown;
import de.crashmash.citybuild.manager.mutep.MuteP;
import de.crashmash.citybuild.utils.ColoredChat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                    Bukkit.getScheduler().runTaskLater(CityBuildV2.getPlugin(),
                            () -> SLOWCHAT_PLAYER.remove(player), MessagesData.SLOWCHAT_COMMAND_SETTINGS_CHAT_COOLDOWN * 20);
                }
            }
        }
        if(MuteP.playerIsMutedP(player)) {
            player.sendMessage(MessagesData.MUTEP_COMMAND_MESSAGE_MUTE_SCREEN.replace("[player]", Objects.requireNonNull(Bukkit.getPlayer(MuteP.getCreator(player))).getName())
                .replace("[reason]", MuteP.getReason(player)));
            event.setCancelled(true);
        }
    }
}
