package org.egov.common.entity.edcr;

import java.util.ArrayList;
import java.util.List;

public class ArchitecturalFeature extends Measurement {
    private static final long serialVersionUID = 95L;

    private String number ;
    private List<RoomHeight> height = new ArrayList<>();
    private Boolean closed = false;
    private List<Measurement> architectures = new ArrayList<>();

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<RoomHeight> getHeights() {
        return height;
    }

    public void setHeights(List<RoomHeight> height) {
        this.height = height;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public List<Measurement> getArchitectures() {
        return architectures;
    }

    public void setArchitectures(List<Measurement> architectures) {
        this.architectures = architectures;
    }
}
