package de.crashmash.citybuild.manager.status;

import de.crashmash.citybuild.CityBuildV2;

import java.util.UUID;

public class StatusPlayer {

    private UUID uuid;
    private String status;

    public StatusPlayer(UUID uuid, String status) {
        this.uuid = uuid;
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        CityBuildV2.getPLUGIN().getStorage().getStatusCollection().update()
                .set("Status", status)
                .where("UUID", uuid).executeAsync();
    }

    public boolean hasStatus() {
        return getStatus() != null;
    }
}
