package org.egov.common.entity.edcr;

import java.util.ArrayList;
import java.util.List;

public class ServiceRoom extends Measurement {

    private static final long serialVersionUID = 90L;

    private String number ;
    private List<RoomHeight> heights = new ArrayList<>();
    private Boolean closed = false;
    private List<Measurement> rooms = new ArrayList<>();

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<RoomHeight> getHeights() {
        return heights;
    }

    public void setHeights(List<RoomHeight> heights) {
        this.heights = heights;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public List<Measurement> getRooms() {
        return rooms;
    }

    public void setRooms(List<Measurement> rooms) {
        this.rooms = rooms;
    }
}
