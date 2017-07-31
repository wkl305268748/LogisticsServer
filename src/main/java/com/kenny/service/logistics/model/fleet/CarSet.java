package com.kenny.service.logistics.model.fleet;

import java.util.List;

/**
 * Created by Kenny on 2017/7/23.
 */
public class CarSet {
    Car car;
    List<License> licenses;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<License> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<License> licenses) {
        this.licenses = licenses;
    }
}
