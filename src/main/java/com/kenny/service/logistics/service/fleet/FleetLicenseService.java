package com.kenny.service.logistics.service.fleet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kenny.service.logistics.mapper.fleet.FleetLicenseMapper;
import com.kenny.service.logistics.model.fleet.FleetLicense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;

@Service
public class FleetLicenseService {
    @Autowired
    private FleetLicenseMapper fleetLicenseMapper;

    public List<FleetLicense> insertByDriver(String json, Integer driver_id) {
        if(json == null)
            json = "";
        json = "[" + json + "]";
        Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
        System.out.println(json);
        List<FleetLicense> licens = gson.fromJson(json, new TypeToken<List<FleetLicense>>() {}.getType());
        for (FleetLicense fleetLicense : licens) {
            fleetLicense.setFk_driver_id(driver_id);
            fleetLicense.setTime(new Date());
            fleetLicense.setFiles(fleetLicense.getFiles().toString());
            fleetLicenseMapper.insert(fleetLicense);
        }
        return licens;
    }

    public List<FleetLicense> insertByCar(String json, Integer car_id) {
        if(json == null)
            json = "";
        json = "[" + json + "]";
        Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
        System.out.println(json);
        List<FleetLicense> licens = gson.fromJson(json, new TypeToken<List<FleetLicense>>() {}.getType());
        for (FleetLicense fleetLicense : licens) {
            fleetLicense.setFk_car_id(car_id);
            fleetLicense.setTime(new Date());
            fleetLicense.setFiles(fleetLicense.getFiles().toString());
            fleetLicenseMapper.insert(fleetLicense);
        }
        return licens;
    }

    public FleetLicense insert(String number, String type, Date pass_time, Date valid_date, Date unvalide_date, String files, String remark, Integer driver_id, Integer car_id) {
        FleetLicense fleetLicense = new FleetLicense();
        fleetLicense.setNumber(number);
        fleetLicense.setType(type);
        fleetLicense.setPass_time(pass_time);
        fleetLicense.setValid_date(valid_date);
        fleetLicense.setUnvalide_date(unvalide_date);
        fleetLicense.setFiles(files);
        fleetLicense.setRemark(remark);
        fleetLicense.setTime(new Date());
        fleetLicense.setFk_car_id(car_id);
        fleetLicenseMapper.insert(fleetLicense);
        return fleetLicense;
    }

    public FleetLicense update(Integer id, String number, String type, Date pass_time, Date valid_date, Date unvalide_date, String files, String remark, Date time) throws ErrorCodeException {
        FleetLicense fleetLicense = fleetLicenseMapper.selectByPrimaryKey(id);
        if (fleetLicense == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        fleetLicense.setNumber(number);
        fleetLicense.setType(type);
        fleetLicense.setPass_time(pass_time);
        fleetLicense.setValid_date(valid_date);
        fleetLicense.setUnvalide_date(unvalide_date);
        fleetLicense.setFiles(files);
        fleetLicense.setRemark(remark);
        fleetLicense.setTime(time);
        fleetLicenseMapper.update(fleetLicense);
        return fleetLicense;
    }

    public FleetLicense selectByPrimaryKey(Integer id) throws ErrorCodeException {
        FleetLicense fleetLicense = fleetLicenseMapper.selectByPrimaryKey(id);
        if (fleetLicense == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        return fleetLicense;
    }

    public PageResponse<FleetLicense> selectPage(Integer offset, Integer pageSize) {
        PageResponse<FleetLicense> response = new PageResponse();
        response.setItem(fleetLicenseMapper.selectPage(offset, pageSize));
        response.setTotal(fleetLicenseMapper.count());
        response.setOffset(offset);
        response.setPageSize(pageSize);
        return response;
    }

    public int deleteByPrimaryKey(Integer id) {
        return fleetLicenseMapper.deleteByPrimaryKey(id);
    }

}
