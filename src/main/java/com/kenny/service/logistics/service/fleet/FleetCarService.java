package com.kenny.service.logistics.service.fleet;

import com.kenny.service.logistics.mapper.fleet.FleetCarMapper;
import com.kenny.service.logistics.mapper.fleet.FleetLicenseMapper;
import com.kenny.service.logistics.model.fleet.FleetCar;
import com.kenny.service.logistics.model.fleet.FleetCarSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;

@Service
public class FleetCarService {
    @Autowired
    private FleetCarMapper fleetCarMapper;
    @Autowired
    private FleetLicenseMapper fleetLicenseMapper;

    public FleetCar insert(String plate, String type, String resource, String two_plate, String driver_phone, String driver_name, String energy, String length, Float weight, String vin, String brand, String engine, String axle, String wheelbase, String tire, Date factory_time, Date buy_time, Integer buy_price, Date limited_time, Date tow_maintain_time, String insurance_policy, String insurance_company, Date insurance_time, String front_img, String tail_img, String remark, Integer belong_user_id) {
        FleetCar fleetCar = new FleetCar();
        fleetCar.setPlate(plate);
        fleetCar.setType(type);
        fleetCar.setResource(resource);
        fleetCar.setTwo_plate(two_plate);
        fleetCar.setDriver_phone(driver_phone);
        fleetCar.setDriver_name(driver_name);
        fleetCar.setEnergy(energy);
        fleetCar.setLength(length);
        fleetCar.setWeight(weight);
        fleetCar.setVin(vin);
        fleetCar.setBrand(brand);
        fleetCar.setEngine(engine);
        fleetCar.setAxle(axle);
        fleetCar.setWheelbase(wheelbase);
        fleetCar.setTire(tire);
        fleetCar.setFactory_time(factory_time);
        fleetCar.setBuy_time(buy_time);
        fleetCar.setBuy_price(buy_price);
        fleetCar.setLimited_time(limited_time);
        fleetCar.setTow_maintain_time(tow_maintain_time);
        fleetCar.setInsurance_policy(insurance_policy);
        fleetCar.setInsurance_company(insurance_company);
        fleetCar.setInsurance_time(insurance_time);
        fleetCar.setFront_img("[" + front_img + "]");
        fleetCar.setTail_img("[" + tail_img + "]");
        fleetCar.setRemark(remark);
        fleetCar.setTime(new Date());
        fleetCar.setVisible(true);
        fleetCar.setBelong_user_id(belong_user_id);
        fleetCarMapper.insert(fleetCar);
        return fleetCar;
    }

    public FleetCar update(Integer id, String plate, String type, String resource, String two_plate, String driver_phone, String driver_name, String energy, String length, Float weight, String vin, String brand, String engine, String axle, String wheelbase, String tire, Date factory_time, Date buy_time, Integer buy_price, Date limited_time, Date tow_maintain_time, String insurance_policy, String insurance_company, Date insurance_time, String front_img, String tail_img, String remark) throws ErrorCodeException {
        FleetCar fleetCar = fleetCarMapper.selectByPrimaryKey(id);
        if (fleetCar == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        fleetCar.setPlate(plate);
        fleetCar.setType(type);
        fleetCar.setResource(resource);
        fleetCar.setTwo_plate(two_plate);
        fleetCar.setDriver_phone(driver_phone);
        fleetCar.setDriver_name(driver_name);
        fleetCar.setEnergy(energy);
        fleetCar.setLength(length);
        fleetCar.setWeight(weight);
        fleetCar.setVin(vin);
        fleetCar.setBrand(brand);
        fleetCar.setEngine(engine);
        fleetCar.setAxle(axle);
        fleetCar.setWheelbase(wheelbase);
        fleetCar.setTire(tire);
        fleetCar.setFactory_time(factory_time);
        fleetCar.setBuy_time(buy_time);
        fleetCar.setBuy_price(buy_price);
        fleetCar.setLimited_time(limited_time);
        fleetCar.setTow_maintain_time(tow_maintain_time);
        fleetCar.setInsurance_policy(insurance_policy);
        fleetCar.setInsurance_company(insurance_company);
        fleetCar.setInsurance_time(insurance_time);
        fleetCar.setFront_img(front_img);
        fleetCar.setTail_img(tail_img);
        fleetCar.setRemark(remark);
        fleetCarMapper.update(fleetCar);
        return fleetCar;
    }

    public FleetCar selectByPrimaryKey(Integer id) throws ErrorCodeException {
        FleetCar fleetCar = fleetCarMapper.selectByPrimaryKey(id);
        if (fleetCar == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        return fleetCar;
    }

    public PageResponse<FleetCar> selectPageByBelongUser(Integer offset, Integer pageSize, Integer belong_user_id) {
        PageResponse<FleetCar> response = new PageResponse();
        response.setItem(fleetCarMapper.selectPageByBelongUser(offset, pageSize, belong_user_id));
        response.setTotal(fleetCarMapper.countByBelongUser(belong_user_id));
        response.setOffset(offset);
        response.setPageSize(pageSize);
        return response;
    }


    public PageResponse<FleetCarSet> selectPageByBelongUserEx(Integer offset, Integer pageSize, Integer belong_user_id) {
        PageResponse<FleetCarSet> response = new PageResponse();
        List<FleetCarSet> fleetCarSets = new ArrayList<>();
        List<FleetCar> fleetCars = fleetCarMapper.selectPageByBelongUser(offset, pageSize,belong_user_id);

        for (FleetCar fleetCar : fleetCars) {
            FleetCarSet fleetCarSet = new FleetCarSet();
            fleetCarSet.setFleetCar(fleetCar);
            fleetCarSet.setLicens(fleetLicenseMapper.selectByCar(fleetCar.getId()));
            fleetCarSets.add(fleetCarSet);
        }
        response.setItem(fleetCarSets);
        response.setTotal(fleetCarMapper.countByBelongUser(belong_user_id));
        response.setOffset(offset);
        response.setPageSize(pageSize);
        return response;
    }

    public int deleteByPrimaryKey(Integer id) throws ErrorCodeException {
        FleetCar fleetCar = fleetCarMapper.selectByPrimaryKey(id);
        if (fleetCar == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        fleetCar.setVisible(false);
        return fleetCarMapper.update(fleetCar);
    }

}
