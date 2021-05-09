package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.ConfigData;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.manager.cooldown.CooldownPlayer;
import de.crashmash.developerapi.commands.AbstractCommand;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.SimpleDateFormat;

public class HeadCommand extends AbstractCommand {

    public HeadCommand() {
        super(ConfigData.CONFIG_COMMAND_HEAD_NAME, null, "Give yourself decorative heads from other players.", ConfigData.CONFIG_COMMAND_HEAD_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        } else {
            Player player = (Player) commandSender;
            if(player.hasPermission(MessagesData.HEAD_COMMAND_PERMISSION_USE)) {
                if(strings.length == 1) {
                    CooldownPlayer cooldownPlayer = CityBuildV2.getPLUGIN().getCooldownCache().getPlayerByUUID(player.getUniqueId());
                    if(cooldownPlayer.hasHeadCooldown(player)) {
                        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD, 1);
                        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
                        skullMeta.setOwner(strings[0]);
                        skullMeta.setDisplayName(MessagesData.HEAD_COMMAND_ITEM_DISPLAYNAME.replace("[skullName]", strings[0]));
                        itemStack.setItemMeta(skullMeta);
                        player.getInventory().addItem(itemStack);
                        player.sendMessage(MessagesData.HEAD_COMMAND_MESSAGE_ADDED_HEAD.replace("[skullName]", strings[0]));
                        cooldownPlayer.setHead();
                    } else {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
                        long time = cooldownPlayer.getHead();
                        player.sendMessage(MessagesData.HEAD_COMMAND_MESSAGE_COOLDOWN.replace("[date]", simpleDateFormat.format(time))
                                .replace("[time]", simpleTimeFormat.format(time)));
                    }
                } else {
                    player.sendMessage(MessagesData.HEAD_COMMAND_MESSAGE_USAGE);
                }
            } else {
                player.sendMessage(MessagesData.NOPERMS);
            }
        }
        return false;
    }
}
