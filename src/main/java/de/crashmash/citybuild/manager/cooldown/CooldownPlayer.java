package de.crashmash.citybuild.manager.cooldown;

import java.util.UUID;

public class CooldownPlayer {

    private UUID uuid;
    private long head;
    private long breakBlock;

    public CooldownPlayer(UUID uuid, long head, long breakBlock) {
        this.uuid = uuid;
        this.head = head;
        this.breakBlock = breakBlock;
    }

    public long getHead() {
        return head;
    }

    public void setHead(long head) {
        this.head = head;
    }

    public long getBreakBlock() {
        return breakBlock;
    }

    public void setBreakBlock(long breakBlock) {
        this.breakBlock = breakBlock;
    }
}
