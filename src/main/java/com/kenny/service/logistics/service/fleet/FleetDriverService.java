package com.kenny.service.logistics.service.fleet;

import com.kenny.service.logistics.mapper.fleet.FleetDriverLicenseMapper;
import com.kenny.service.logistics.mapper.fleet.FleetDriverMapper;
import com.kenny.service.logistics.mapper.fleet.FleetLicenseMapper;
import com.kenny.service.logistics.model.fleet.FleetDriver;
import com.kenny.service.logistics.model.fleet.FleetDriverLicense;
import com.kenny.service.logistics.model.fleet.FleetDriverSet;
import com.kenny.service.logistics.model.fleet.FleetLicense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;

@Service
public class FleetDriverService {
    @Autowired
    private FleetDriverMapper fleetDriverMapper;
    @Autowired
    private FleetDriverLicenseMapper fleetDriverLicenseMapper;
    @Autowired
    private FleetLicenseMapper fleetLicenseMapper;

    public FleetDriver insert(String name,String sex,String phone,Integer fk_user_id,Boolean is_sms,String idcard,String email,String hometown,String remark,Integer belong_user_id,String bank_number,String bank_addr){
        FleetDriver fleetDriver = new FleetDriver();
        fleetDriver.setName(name);
        fleetDriver.setSex(sex);
        fleetDriver.setPhone(phone);
        fleetDriver.setFk_user_id(fk_user_id);
        fleetDriver.setIs_sms(is_sms);
        fleetDriver.setIdcard(idcard);
        fleetDriver.setEmail(email);
        fleetDriver.setHometown(hometown);
        fleetDriver.setRemark(remark);
        fleetDriver.setBelong_user_id(belong_user_id);
        fleetDriver.setBank_number(bank_number);
        fleetDriver.setBank_addr(bank_addr);
        fleetDriver.setVisible(true);
        fleetDriver.setTime(new Date());
        fleetDriverMapper.insert(fleetDriver);
        return fleetDriver;
    }

    public FleetDriver update(Integer id,String name,String sex,String phone,Boolean is_sms,String idcard,String email,String hometown,String remark,String bank_number,String bank_addr) throws ErrorCodeException{
        FleetDriver fleetDriver = fleetDriverMapper.selectByPrimaryKey(id);
        if(fleetDriver == null){
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        fleetDriver.setName(name);
        fleetDriver.setSex(sex);
        fleetDriver.setPhone(phone);
        fleetDriver.setIs_sms(is_sms);
        fleetDriver.setIdcard(idcard);
        fleetDriver.setEmail(email);
        fleetDriver.setHometown(hometown);
        fleetDriver.setRemark(remark);
        fleetDriver.setBank_number(bank_number);
        fleetDriver.setBank_addr(bank_addr);
        fleetDriverMapper.update(fleetDriver);
        return fleetDriver;
    }

    public FleetDriver selectByPrimaryKey(Integer id) throws ErrorCodeException {
        FleetDriver fleetDriver = fleetDriverMapper.selectByPrimaryKey(id);
        if (fleetDriver == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        return fleetDriver;
    }

    public FleetDriverSet selectByPrimaryKeyEx(Integer id) throws ErrorCodeException {
        FleetDriverSet fleetDriverSet = new FleetDriverSet();
        FleetDriver fleetDriver = fleetDriverMapper.selectByPrimaryKey(id);
        if (fleetDriver == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        FleetDriverLicense fleetDriverLicense = fleetDriverLicenseMapper.selectByDriverId(fleetDriver.getId());
        List<FleetLicense> licens = fleetLicenseMapper.selectByDriver(fleetDriver.getId());
        fleetDriverSet.setFleetDriver(fleetDriver);
        fleetDriverSet.setFleetDriverLicense(fleetDriverLicense);
        fleetDriverSet.setLicens(licens);

        return fleetDriverSet;
    }

    public PageResponse<FleetDriver> selectPageByBelongUser(Integer offset, Integer pageSize, Integer belong_user_id) {
        PageResponse<FleetDriver> response = new PageResponse();
        response.setItem(fleetDriverMapper.selectPageBelongUser(offset, pageSize,belong_user_id));
        response.setTotal(fleetDriverMapper.countByBelongUser(belong_user_id));
        response.setOffset(offset);
        response.setPageSize(pageSize);
        return response;
    }


    public PageResponse<FleetDriverSet> selectPageByBelongUserEx(Integer offset, Integer pageSize, Integer belong_user_id) {
        PageResponse<FleetDriverSet> response = new PageResponse();
        List<FleetDriverSet> fleetDriverSets = new ArrayList<>();
        List<FleetDriver> fleetDrivers = fleetDriverMapper.selectPageBelongUser(offset, pageSize, belong_user_id);
        for (FleetDriver fleetDriver : fleetDrivers) {
            FleetDriverSet fleetDriverSet = new FleetDriverSet();
            FleetDriverLicense fleetDriverLicense = fleetDriverLicenseMapper.selectByDriverId(fleetDriver.getId());
            List<FleetLicense> licens = fleetLicenseMapper.selectByDriver(fleetDriver.getId());
            fleetDriverSet.setFleetDriver(fleetDriver);
            fleetDriverSet.setFleetDriverLicense(fleetDriverLicense);
            fleetDriverSet.setLicens(licens);
            fleetDriverSets.add(fleetDriverSet);
        }
        response.setItem(fleetDriverSets);
        response.setTotal(fleetDriverMapper.countByBelongUser(belong_user_id));
        response.setOffset(offset);
        response.setPageSize(pageSize);
        return response;
    }

    /**
     * 删除车辆
     *
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(Integer id) {
        FleetDriver fleetDriver = fleetDriverMapper.selectByPrimaryKey(id);
        if (fleetDriver == null)
            return 0;
        fleetDriver.setVisible(false);
        return fleetDriverMapper.update(fleetDriver);
    }
}
