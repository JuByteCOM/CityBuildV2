package de.jubyte.citybuild.commands;

import com.plotsquared.core.PlotAPI;
import de.jubyte.citybuild.CityBuildV2;
import de.jubyte.citybuild.data.ConfigData;
import de.jubyte.citybuild.data.MessagesData;
import de.jubyte.citybuild.manager.cooldown.CooldownPlayer;
import de.crashmash.developerapi.commands.AbstractCommand;
import de.jubyte.citybuild.utils.PlotUtilsV6;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BreakblockCommand extends AbstractCommand {

    private final List<Player> breackBlockPlayers = new ArrayList<>();

    public BreakblockCommand() {
        super(ConfigData.CONFIG_COMMAND_BREAKBLOCK_NAME, null, "Remove indestructible blocks.", ConfigData.CONFIG_COMMAND_BREAKBLOCK_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        } else {
            Player player = (Player) commandSender;
            if(Bukkit.getServer().getPluginManager().getPlugin("PlotSquared") != null) {
                PlotAPI plotAPI = new PlotAPI();
                if (player.hasPermission(MessagesData.BREAKBLOCK_COMMAND_PERMISSION_USE)) {
                    CooldownPlayer cooldownPlayer = CityBuildV2.getPLUGIN().getCooldownCache().getPlayerByUUID(player.getUniqueId());
                    if (strings.length == 0) {
                        if (cooldownPlayer.hasBreakblockCooldown(player)) {
                            if (!breackBlockPlayers.contains(player)) {
                                breackBlockPlayers.add(player);
                            }
                            player.sendMessage(MessagesData.BREAKBLOCK_COMMAND_MESSAGE_CONFIRM_COMMAND);
                        } else {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
                            long time = cooldownPlayer.getBreakBlock();
                            player.sendMessage(MessagesData.BREAKBLOCK_COMMAND_MESSAGE_COODLOWN.replace("[date]", simpleDateFormat.format(time))
                                    .replace("[time]", simpleTimeFormat.format(time)));
                        }
                    } else if (strings.length == 1) {
                        if (strings[0].equalsIgnoreCase("confirm")) {
                            if (breackBlockPlayers.contains(player)) {
                                Block block = player.getTargetBlock(null, 5);
                                boolean breakAllowed = true;
                                if (MessagesData.BREAKBLOCK_COMMAND_SETTINGS_AVIABLE_WORLDS.stream().filter(world -> player.getWorld().getName().equalsIgnoreCase(world)).collect(Collectors.toSet()).isEmpty()) {
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
                                    if (Bukkit.getServer().getPluginManager().getPlugin("PlotSquared") == null) {
                                        breackBlockPlayers.remove(player);
                                        player.sendMessage(MessagesData.BREAKBLOCK_COMMAND_MESSAGE_CONFIRM_BLOCK_REMOVED);
                                        cooldownPlayer.setBreakBlock();
                                        if (MessagesData.BREAKBLOCK_COMMAND_SETTINGS_DROP_BLOCK) {
                                            ItemStack itemStack = new ItemStack(block.getType());
                                            player.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                                        }
                                        block.setType(Material.AIR);
                                    } else {
                                        if (Arrays.stream(plotAPI.getPlotSquared().getPlotAreaManager().getAllPlotAreas()).filter(y -> block.getWorld().getName().equalsIgnoreCase(String.valueOf(y))).collect(Collectors.toSet()).isEmpty()) {
                                            breackBlockPlayers.remove(player);
                                            player.sendMessage(MessagesData.BREAKBLOCK_COMMAND_MESSAGE_CONFIRM_BLOCK_REMOVED);
                                            cooldownPlayer.setBreakBlock();
                                            if (MessagesData.BREAKBLOCK_COMMAND_SETTINGS_DROP_BLOCK) {
                                                ItemStack itemStack = new ItemStack(block.getType());
                                                player.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                                            }
                                            block.setType(Material.AIR);
                                        } else {
                                            if (PlotUtilsV6.getPlot(player.getTargetBlock(null, 5).getLocation()) != null) {
                                                if (PlotUtilsV6.getPlot(block.getLocation()).isOwner(player.getUniqueId())) {
                                                    breackBlockPlayers.remove(player);
                                                    player.sendMessage(MessagesData.BREAKBLOCK_COMMAND_MESSAGE_CONFIRM_BLOCK_REMOVED);
                                                    cooldownPlayer.setBreakBlock();
                                                    if (MessagesData.BREAKBLOCK_COMMAND_SETTINGS_DROP_BLOCK) {
                                                        ItemStack itemStack = new ItemStack(block.getType());
                                                        player.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                                                    }
                                                    block.setType(Material.AIR);
                                                } else {
                                                    player.sendMessage(MessagesData.BREAKBLOCK_COMMAND_MESSAGE_ISNT_PLOT_OWNER);
                                                }
                                            } else {
                                                player.sendMessage(MessagesData.BREAKBLOCK_COMMAND_MESSAGE_BLOCK_ISNT_ON_PLOT);
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
            } else {
                if(player.hasPermission("citybuild.breakblock.admin")) {
                    player.sendMessage("§cPlotSquaredV5 is missing.");
                } else {
                    player.sendMessage(MessagesData.SCHILD_COMMAND_MESSAGE_NOPLOTSQUARED);
                }
            }
        }
        return false;
    }
}
