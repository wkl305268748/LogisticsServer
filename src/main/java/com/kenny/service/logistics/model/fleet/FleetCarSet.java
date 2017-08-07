package com.kenny.service.logistics.model.fleet;

import java.util.List;

/**
 * Created by Kenny on 2017/7/23.
 */
public class FleetCarSet {
    FleetCar fleetCar;
    List<FleetLicense> licens;

    public FleetCar getFleetCar() {
        return fleetCar;
    }

    public void setFleetCar(FleetCar fleetCar) {
        this.fleetCar = fleetCar;
    }

    public List<FleetLicense> getLicens() {
        return licens;
    }

    public void setLicens(List<FleetLicense> licens) {
        this.licens = licens;
    }
}
