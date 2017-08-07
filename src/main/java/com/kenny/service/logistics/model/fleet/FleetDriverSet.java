package com.kenny.service.logistics.model.fleet;

import java.util.List;

/**
 * Created by Kenny on 2017/7/23.
 */
public class FleetDriverSet {
    FleetDriver fleetDriver;
    FleetDriverLicense fleetDriverLicense;
    List<FleetLicense> licens;

    public FleetDriver getFleetDriver() {
        return fleetDriver;
    }

    public void setFleetDriver(FleetDriver fleetDriver) {
        this.fleetDriver = fleetDriver;
    }

    public FleetDriverLicense getFleetDriverLicense() {
        return fleetDriverLicense;
    }

    public void setFleetDriverLicense(FleetDriverLicense fleetDriverLicense) {
        this.fleetDriverLicense = fleetDriverLicense;
    }

    public List<FleetLicense> getLicens() {
        return licens;
    }

    public void setLicens(List<FleetLicense> licens) {
        this.licens = licens;
    }
}
