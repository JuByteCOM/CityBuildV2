package de.crashmash.citybuild.manager.mutep;

import java.util.UUID;

public class MutepPlayer {

    private UUID uuid;
    private String reason;
    private long duration;
    private long cooldown;
    private UUID creator;

    public MutepPlayer(UUID uuid, String reason, long duration, long cooldown, UUID creator) {
        this.uuid = uuid;
        this.reason = reason;
        this.duration = duration;
        this.cooldown = cooldown;
        this.creator = creator;
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

    public UUID getCreator() {
        return creator;
    }

    public void setCreator(UUID creator) {
        this.creator = creator;
    }
}