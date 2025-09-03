package org.egov.edcr.entity.blackbox;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.egov.common.entity.edcr.ServiceFloor;
import org.kabeja.dxf.DXFLWPolyline;

import java.util.ArrayList;
import java.util.List;

public class ServiceFloorDetail extends ServiceFloor {
    private static final long serialVersionUID = 70L;
    @JsonIgnore
    private transient List<DXFLWPolyline> builtUpAreaPolyLine = new ArrayList<>();

    public List<DXFLWPolyline> getBuiltUpAreaPolyLine() {
        return builtUpAreaPolyLine;
    }

    public void setBuiltUpAreaPolyLine(List<DXFLWPolyline> builtUpAreaPolyLine) {
        this.builtUpAreaPolyLine = builtUpAreaPolyLine;
    }
}
