package com.project.demo.controller;

import com.project.demo.entity.CourseParticipation;
import com.project.demo.service.CourseParticipationService;
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
 * 课程参与：(CourseParticipation)表控制层
 *
 */
@RestController
@RequestMapping("/course_participation")
public class CourseParticipationController extends BaseController<CourseParticipation, CourseParticipationService> {

    /**
     * 课程参与对象
     */
    @Autowired
    public CourseParticipationController(CourseParticipationService service) {
        setService(service);
    }


    @PostMapping("/add")
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String, Object> paramMap = service.readBody(request.getReader());

        Integer courseId = readInt(paramMap.get("source_id"));
        // 身份只认会话：拦截器已经解析过，这里直接拿
        Integer memberUser = (Integer) request.getAttribute("userId");

        Object name = paramMap.get("member_name");

        service.enroll(courseId, memberUser, name == null ? null : String.valueOf(name));

        return success(1);
    }

    private Integer readInt(Object raw) {
        if (raw == null) {
            return null;
        }
        String text = String.valueOf(raw).trim();
        if (text.isEmpty() || "null".equals(text)) {
            return null;
        }
        try {
            int value = Integer.parseInt(text);
            return (value > 0) ? value : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
