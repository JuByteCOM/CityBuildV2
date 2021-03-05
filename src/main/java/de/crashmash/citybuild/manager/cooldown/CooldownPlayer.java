package de.crashmash.citybuild.manager.cooldown;

import java.util.UUID;

public class CooldownPlayer {

    private UUID uuid;
    private long head;
    private long breakBlock;
    private long muteP;

    public CooldownPlayer(UUID uuid, long head, long breakBlock, long muteP) {
        this.uuid = uuid;
        this.head = head;
        this.breakBlock = breakBlock;
        this.muteP = muteP;
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

    public long getMuteP() {
        return muteP;
    }

    public void setMuteP(long muteP) {
        this.muteP = muteP;
    }
}
