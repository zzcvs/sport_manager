package com.project.demo.sevice;


import com.project.demo.entity.CourseInformation;
import com.project.demo.service.CourseInformationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:mysql://127.0.0.1:3306/project_db"
                + "?serverTimezone=GMT%2B8&useSSL=false&characterEncoding=utf-8"
                + "&allowPublicKeyRetrieval=true"
})
@DisplayName("冲突检测：时间 + 地点 双条件")
class CourseConflictTest {

    @Autowired
    CourseInformationService service;

    @Test
    @DisplayName("同时间+同地点才算冲突；只同时间不算")
    void locationNarrowsTheConflict() {
        // 取库里真实一行当样本，不用我猜数据内容
        List<CourseInformation> all = service.selectBaseList(
                service.select(new HashMap<>(), new HashMap<>()));
        assertFalse(all.isEmpty(), "course_information 里得先有数据");
        CourseInformation sample = all.get(0);

        // 1) 同时间 + 同地点（不排除自己）→ 至少命中自己这一行
        List<CourseInformation> sameTimeSamePlace =
                service.checkCourseConflict(sample, null);
        assertFalse(sameTimeSamePlace.isEmpty(), "同时间同地点应至少命中自己");

        // 2) 同时间 + 一个库里不存在的地点 → 0 条
        //    升级前（只判时间）这里会 ≥1 条，这条断言就是「AND 真的生效」的证据
        CourseInformation wrongPlace = new CourseInformation();
        wrongPlace.setCourse_time(sample.getCourse_time());
        wrongPlace.setCourse_location("不存在的地点_9527");
        assertTrue(service.checkCourseConflict(wrongPlace, null).isEmpty(),
                "只同时间、不同地点，不应算冲突");

        // 3) 排除自己后：时间地点唯一时应为 0
        List<CourseInformation> afterExclude =
                service.checkCourseConflict(sample, sample.getCourse_information_id());
        assertEquals(0, afterExclude.size(), "排除自己后应无冲突");

        // 4) 地点为空 → 降级为只按时间判 → 结果与「同时间同地点」一致
        CourseInformation noPlace = new CourseInformation();
        noPlace.setCourse_time(sample.getCourse_time());
        assertEquals(sameTimeSamePlace.size(),
                service.checkCourseConflict(noPlace, null).size(),
                "地点为空时应降级为只判时间");
    }
}
