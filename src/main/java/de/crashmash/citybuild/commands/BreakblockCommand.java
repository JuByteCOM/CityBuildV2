package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.manager.cooldown.Cooldown;
import de.crashmash.citybuild.manager.startkick.StartKick;
import de.crashmash.citybuild.utils.PlotUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BreakblockCommand implements CommandExecutor {

    private final List<Player> breackBlockPlayers = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        } else {
            Player player = (Player) commandSender;
            if(player.hasPermission(MessagesData.BREAKBLOCK_COMMAND_PERMISSION_USE)) {
                if(strings.length == 0) {
                    if(Cooldown.canUseBreakBlock(player)) {
                        if (!breackBlockPlayers.contains(player)) {
                            breackBlockPlayers.add(player);
                        }
                        player.sendMessage(MessagesData.BREAKBLOCK_COMMAND_MESSAGE_CONFIRM_COMMAND);
                    } else {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
                        long time = StartKick.getDuration(player) + MessagesData.STARTKICK_COMMAND_SETTING_DURATION*1000L;
                        player.sendMessage(MessagesData.BREAKBLOCK_COMMAND_MESSAGE_COODLOWN.replace("[date]", simpleDateFormat.format(time))
                                .replace("[time]", simpleTimeFormat.format(time)));
                    }
                } else if(strings.length == 1) {
                    if(strings[0].equalsIgnoreCase("confirm")) {
                        if(breackBlockPlayers.contains(player)) {
                            Block block = player.getTargetBlock(null, 5);
                            boolean breakAllowed = true;
                            if (MessagesData.BREAKBLOCK_COMMAND_SETTINGS_AVIABLE_WORLDS.stream().filter(world -> !player.getWorld().equals(world)).collect(Collectors.toSet()).isEmpty()) {
                                breakAllowed = false;
                                player.sendMessage(MessagesData.BREAKBLOCK_COMMAND_MESSAGE_DISABLED_WORLDS);
                            }
                            if (breakAllowed) {
                                if (!MessagesData.BREAKBLOCK_COMMAND_SETTINGS_DISABLES_BLOCK_HEIGHTS.stream().filter(y -> block.getLocation().getBlockY() == y).collect(Collectors.toSet()).isEmpty()) {
                                    breakAllowed = false;
                                    player.sendMessage(MessagesData.BREAKBLOCK_COMMAND_MESSAGE_DISABLED_BLOCK_HEIGHT.replace("[blockHeight]", String.valueOf(block.getLocation().getBlockY())));
                                }
                            }
                            if (breakAllowed) {
                                if (MessagesData.BREAKBLOCK_COMMAND_SETTINGS_BLOCKED_BLOCKS.stream().filter(material -> block.getType() !=
                                        Material.matchMaterial(material)).collect(Collectors.toSet()).isEmpty()) {
                                    breakAllowed = false;
                                    player.sendMessage(MessagesData.BREAKBLOCK_COMMAND_MESSAGE_BLOCKED_BLOCK);
                                }
                            }
                            if (breakAllowed) {
                                if(MessagesData.BREAKBLOCK_COMMAND_SETTINGS_DROP_BLOCK) {
                                    ItemStack itemStack = new ItemStack(block.getType());
                                    player.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                                }
                                if(Bukkit.getServer().getPluginManager().getPlugin("PlotSquared") == null) {
                                    breackBlockPlayers.remove(player);
                                    block.setType(Material.AIR);
                                    player.sendMessage(MessagesData.BREAKBLOCK_COMMAND_MESSAGE_CONFIRM_BLOCK_REMOVED);
                                    Cooldown.setBreakBlockCooldown(player);
                                } else {
                                    if (!PlotUtils.isInPlot(block.getLocation())) {
                                        breackBlockPlayers.remove(player);
                                        block.setType(Material.AIR);
                                        player.sendMessage(MessagesData.BREAKBLOCK_COMMAND_MESSAGE_CONFIRM_BLOCK_REMOVED);
                                        Cooldown.setBreakBlockCooldown(player);
                                    } else {
                                        if (PlotUtils.getPlot(block.getLocation()) != null) {
                                            if (PlotUtils.getPlot(block.getLocation()).isOwner(player.getUniqueId())) {
                                                breackBlockPlayers.remove(player);
                                                block.setType(Material.AIR);
                                                player.sendMessage(MessagesData.BREAKBLOCK_COMMAND_MESSAGE_CONFIRM_BLOCK_REMOVED);
                                                Cooldown.setBreakBlockCooldown(player);
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            player.sendMessage(MessagesData.BREAKBLOCK_COMMAND_MESSAGE_USAGE);
                        }
                    }
                } else {
                    player.sendMessage(MessagesData.BREAKBLOCK_COMMAND_MESSAGE_USAGE);
                }
            } else {
                player.sendMessage(MessagesData.NOPERMS);
            }
        }
        return false;
    }
}