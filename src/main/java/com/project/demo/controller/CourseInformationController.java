package com.project.demo.controller;

import com.alibaba.fastjson.JSON;
import com.project.demo.entity.CourseInformation;
import com.project.demo.service.CourseInformationService;
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
 * 课程信息：(CourseInformation)表控制层
 *
 */
@RestController
@RequestMapping("/course_information")
public class CourseInformationController extends BaseController<CourseInformation, CourseInformationService> {

    /**
     * 课程信息对象
     */
    @Autowired
    public CourseInformationController(CourseInformationService service) {
        setService(service);
    }



    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());

        CourseInformation course = JSON.parseObject(
                JSON.toJSONString(paramMap), CourseInformation.class);

        List<CourseInformation> conflicts = service.checkCourseConflict(course, null);
        if (!conflicts.isEmpty()) {

            return error(30000, "该时间段已经排课，请更换时间或地点");
        }

        this.addMap(paramMap);
        return success(1);
    }

    @PostMapping("/set")
    @Transactional
    public Map<String, Object> set(HttpServletRequest request) throws IOException {
        Map<String, String> query = service.readQuery(request);
        Map<String, String> config = service.readConfig(request);
        Map<String, Object> paramMap = service.readBody(request.getReader());

        Integer excludeId = readCourseId(query, paramMap);
        if (excludeId == null) {
            return error(30000, "缺少 course_information_id，无法定位要修改的课程");
        }
        query.put("course_information_id", String.valueOf(excludeId));

        Map<String, String> idQuery = new HashMap<>();
        idQuery.put("course_information_id", String.valueOf(excludeId));
        List<CourseInformation> existing = service.selectBaseList(
                service.select(idQuery, Collections.emptyMap()));
        if (existing.isEmpty()) {
            return error(30000, "要修改的课程不存在， Course_Information_id" + excludeId);
        }
        CourseInformation course = JSON.parseObject(JSON.toJSONString(paramMap), CourseInformation.class);
        CourseInformation old = existing.get(0);

        if (course.getCourse_time() == null) {
            course.setCourse_time(old.getCourse_time());
        }
        if (course.getCourse_location() == null) {
            course.setCourse_location(old.getCourse_location());
        }

        List<CourseInformation> conflicts = service.checkCourseConflict(course, excludeId);
        if (!conflicts.isEmpty()) {
            return error(30000, "该时间段已经排课，请更换时间或地点");
        }

        service.update(query, config, paramMap);
        return success(1);

    }

    private Integer readCourseId(Map<String, String> query, Map<String, Object> body) {
        Object raw = (query == null) ? null : query.get("course_information_id");
        if (raw == null && body != null) {
            raw = body.get("course_information_id");
        }
        if (raw == null) {
            return null;
        }
        String text = String.valueOf(raw).trim();
        if (text.isEmpty() || "null".equals(text)) {
            return null;
        }
        try {
            Integer id = Integer.valueOf(text);
            return (id > 0) ? id :null;
        }catch (NumberFormatException e) {
            return null;
        }

    }

}
