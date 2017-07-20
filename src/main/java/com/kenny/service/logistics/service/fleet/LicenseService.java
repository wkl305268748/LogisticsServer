package com.kenny.service.logistics.service.fleet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kenny.service.logistics.mapper.fleet.LicenseMapper;
import com.kenny.service.logistics.model.fleet.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;

@Service
public class LicenseService {
    @Autowired
    private LicenseMapper licenseMapper;

    public List<License> insertByDriver(String json, Integer driver_id) {
        if(json == null)
            json = "";
        json = "[" + json + "]";
        Gson gson = new GsonBuilder().serializeNulls().create();
        System.out.println(json);
        List<License> licenses = gson.fromJson(json, new TypeToken<List<License>>() {}.getType());
        for (License license : licenses) {
            license.setFk_driver_id(driver_id);
            licenseMapper.insert(license);
        }
        return licenses;
    }

    public License insert(String number, String type, Date pass_time, Date valid_date, Date unvalide_date, String files, String remark, Integer driver_id, Integer car_id) {
        License license = new License();
        license.setNumber(number);
        license.setType(type);
        license.setPass_time(pass_time);
        license.setValid_date(valid_date);
        license.setUnvalide_date(unvalide_date);
        license.setFiles(files);
        license.setRemark(remark);
        license.setTime(new Date());
        license.setFk_car_id(car_id);
        licenseMapper.insert(license);
        return license;
    }

    public License update(Integer id, String number, String type, Date pass_time, Date valid_date, Date unvalide_date, String files, String remark, Date time) throws ErrorCodeException {
        License license = licenseMapper.selectByPrimaryKey(id);
        if (license == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        license.setNumber(number);
        license.setType(type);
        license.setPass_time(pass_time);
        license.setValid_date(valid_date);
        license.setUnvalide_date(unvalide_date);
        license.setFiles(files);
        license.setRemark(remark);
        license.setTime(time);
        licenseMapper.update(license);
        return license;
    }

    public License selectByPrimaryKey(Integer id) throws ErrorCodeException {
        License license = licenseMapper.selectByPrimaryKey(id);
        if (license == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        return license;
    }

    public PageResponse<License> selectPage(Integer offset, Integer pageSize) {
        PageResponse<License> response = new PageResponse();
        response.setItem(licenseMapper.selectPage(offset, pageSize));
        response.setTotal(licenseMapper.count());
        response.setOffset(offset);
        response.setPageSize(pageSize);
        return response;
    }

    public int deleteByPrimaryKey(Integer id) {
        return licenseMapper.deleteByPrimaryKey(id);
    }

}
