package de.jubyte.citybuild.utils;

import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutOpenSignEditor;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.world.level.block.entity.TileEntitySign;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class SignEdit_1_17_R1 implements SignEdit {

    public void editSign(Player player, Sign sign) {
        String[] lines = new String[4];
        for (int i = 0; i < sign.getLines().length; i++) {
            sign.getLine(i);
            lines[i] = sign.getLine(i).replaceAll("§", "&");
        }
        TileEntitySign tes = (TileEntitySign)((CraftWorld)sign.getWorld()).getHandle().getTileEntity(new BlockPosition(sign.getX(), sign.getY(), sign.getZ()));
        assert tes != null;
        tes.f = true;
        tes.a((EntityPlayer) ((CraftHumanEntity)player).getHandle());

        player.sendSignChange(sign.getLocation(), lines);
        PacketPlayOutOpenSignEditor packet2 = new PacketPlayOutOpenSignEditor(tes.getPosition());
        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
        PlayerConnection connection = entityPlayer.b;
        connection.sendPacket(packet2);
    }
}