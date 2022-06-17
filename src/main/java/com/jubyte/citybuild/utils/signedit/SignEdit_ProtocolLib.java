package com.jubyte.citybuild.utils.signedit;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Justin_SGD
 * @since 02.08.2021
 */
public class SignEdit_ProtocolLib implements SignEdit {

  public void editSign(Player player, Sign sign) {
    String[] lines = new String[4];
    for (int i = 0; i < sign.getLines().length; i++) {
      sign.getLine(i);
      lines[i] = sign.getLine(i).replaceAll("§", "&");
    }

    sign.setEditable(true);
    sign.update(true);

    ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
    PacketContainer packetContainer = new PacketContainer(PacketType.Play.Server.OPEN_SIGN_EDITOR);
    packetContainer
            .getBlockPositionModifier()
            .write(0, new BlockPosition(sign.getX(), sign.getY(), sign.getZ()));
    try {
      protocolManager.sendServerPacket(player, packetContainer);
    } catch (InvocationTargetException e) {
      Bukkit.getLogger().warning("Error!");
    }
  }
}