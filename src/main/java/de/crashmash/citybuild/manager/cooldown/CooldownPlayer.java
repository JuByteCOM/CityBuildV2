package de.crashmash.citybuild.manager.cooldown;

import java.util.UUID;

public class CooldownPlayer {

    private UUID uuid;
    private long head;
    private long breakBlock;
    private long giftRank;

    public CooldownPlayer(UUID uuid, long head, long breakBlock, long giftRank) {
        this.uuid = uuid;
        this.head = head;
        this.breakBlock = breakBlock;
        this.giftRank = giftRank;
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

    public long getGiftRank() {
        return giftRank;
    }

    public void setGiftRank(long giftRank) {
        this.giftRank = giftRank;
    }

}
