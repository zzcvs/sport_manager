package com.project.demo.controller;

import com.project.demo.entity.DeviceInformation;
import com.project.demo.service.DeviceInformationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;


/**
 * 设备信息：(DeviceInformation)表控制层
 *
 */
@RestController
@RequestMapping("/device_information")
public class DeviceInformationController extends BaseController<DeviceInformation, DeviceInformationService> {

    /**
     * 设备信息对象
     */
    @Autowired
    public DeviceInformationController(DeviceInformationService service) {
        setService(service);
    }



    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapequipment_number = new HashMap<>();
        mapequipment_number.put("equipment_number",String.valueOf(paramMap.get("equipment_number")));
        List listequipment_number = service.selectBaseList(service.select(mapequipment_number, new HashMap<>()));
        if (listequipment_number.size()>0){
            return error(30000, "字段设备编号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }


}
