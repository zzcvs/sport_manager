package com.project.demo;

import com.project.demo.service.CourseInformationService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SqlPrinter {
    public static void main(String[] args) {
        CourseInformationService service = new CourseInformationService();
        Map<String, String> query = new HashMap<>();
        query.put("course_time", "课程时间1");
        query.put("course_location", "课程地点2");
        System.out.println(service.select(query, Collections.emptyMap()));
    }
}
