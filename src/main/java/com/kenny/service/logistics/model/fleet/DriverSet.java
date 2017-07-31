package com.kenny.service.logistics.model.fleet;

import java.util.List;

/**
 * Created by Kenny on 2017/7/23.
 */
public class DriverSet {
    Driver driver;
    DriverLicense driverLicense;
    List<License> licenses;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public DriverLicense getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(DriverLicense driverLicense) {
        this.driverLicense = driverLicense;
    }

    public List<License> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<License> licenses) {
        this.licenses = licenses;
    }
}
