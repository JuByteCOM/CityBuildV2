package de.crashmash.citybuild.manager.startkick;

import java.util.UUID;

public class StartKickPlayer {

    private UUID uuid;
    private String reason;
    private long duration;
    private long cooldown;

    public StartKickPlayer(UUID uuid, String reason, long duration, long cooldown) {
        this.uuid = uuid;
        this.reason = reason;
        this.duration = duration;
        this.cooldown = cooldown;
    }

    public String getReason() {
        return reason;
    }

    public long getDuration() {
        return duration;
    }

    public long getCooldown() {
        return cooldown;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
    }
}
