package com.project.demo.service.base;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * BaseService 中 Map → 实体 转换的单元测试。
 *
 * <p>核心逻辑是 {@link BaseService#selectBaseList(String)} 里的 this line:
 * <pre>
 *   for (Map<String,Object> map : mapList) {
 *       list.add(JSON.parseObject(JSON.toJSONString(map), eClass));
 *   }
 * </pre>
 * —— 先把 {@link Map} 序列化成 JSON，再反序列化进目标实体类。这条测试
 * 验证的就是那条“深拷贝式”转换的行为。
 *
 * <p>为了让测试在不启动 Spring 的情况下运行，我们用 lombok 生成的
 * setter/getter 直接构造一个 Map（模拟 MyBatis 返回的 LinkedHashMap），
 * 然后把它喂进与 BaseService 相同的 fastjson 路径上去。
 */
@DisplayName("BaseService Map→Entity 转换")
class MapToEntityTest {

    /** 目标实体 —— pick CourseInformation，因为课程管理是我们的学习主线。 */
    static class CourseInformationLike {
        // 选一个字段儿就够用了：课程名称。命名规范用 courseName（驼峰），
        // 验证 fastjson 能不能把 mybatis 的 snake_case 键对上。
        private String courseName;

        // 第二个字段：课程时间。留着它是因为下面用例 2 要验证
        // snake_case 键 course_time -> 驼峰字段 courseTime 的映射。
        // （之前这里只写了 courseName 一个字段，用例却调了 getCourseTime()，
        //   编译不过 —— 这正是「先写用例、再补齐被测字段」时容易漏的一步。）
        private String courseTime;

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getCourseTime() {
            return courseTime;
        }

        public void setCourseTime(String courseTime) {
            this.courseTime = courseTime;
        }
    }

    @Test
    @DisplayName("null Map 不抛异常，结果为空列表")
    void nullMapDontCrash() {
        // 跟 BaseService.selectBaseList 的入口行为一致：
        // 虽然 selectBaseList 本身会在 BaseMapper 层抛（没有 mock），
        // 但转换这一段我们做的是输入为空集合的情况。
        Map<String, Object> emptyMap = null;
        // 复刻 selectBaseList 的转换流：JSON.toJSONString(map) 在 map==null 时
        // 返回字符串 "null"，JSON.parseObject("null", Class) 返回 null。
        String json = JSONObject.toJSONString(emptyMap);
        CourseInformationLike obj = JSONObject.parseObject(json, CourseInformationLike.class);

        // 结果是 null（而不是抛出异常），这正是 BaseService 在空列表上的安全保证。
        assert obj == null
                : "null Map 的转换结果应为 null，而不是抛异常。";
    }

    @Test
    @DisplayName("snake_case Map 键（mybatis 返回）能映射到实体驼峰字段")
    void snakeCaseKeyMapsToCamelField() {
        // MyBatis 返回的是 LinkedHashMap，键是表字段名（snake_case）。例如：
        //      { "course_name" : "瑜伽入门", "course_time" : "周日 14:00", ... }
        Map<String, Object> rowAsMybatisReturns = new java.util.LinkedHashMap<>();
        rowAsMybatisReturns.put("course_name", "瑜伽入门");
        rowAsMybatisReturns.put("course_time", "周日 14:00");
        // 故意留一个 null 字段，验证 BaseService 走 WriteMapNullValue 时的意图：
        // front-end 通过 v-if 读 null 字段时不 panic。
        rowAsMybatisReturns.put("course_location", null);

        String json = JSONObject.toJSONString(rowAsMybatisReturns);
        // BaseService 用的是 fastjson 的 parseObject(json, Class<E>)。
        // fastjson 默认按字段名（不管 case）映射：course_name -> courseName。
        CourseInformationLike obj = JSONObject.parseObject(json, CourseInformationLike.class);

        assert obj != null;
        assert "瑜伽入门".equals(obj.getCourseName())
                : "course_name 应映射到 courseName 字段，期值 瑜伽入门，得值 " + obj.getCourseName();
        assert "周日 14:00".equals(obj.getCourseTime())
                : "course_time 应映射到 courseTime 字段";
        // course_location 是 null 的字段，没有 getter，这里不判 —— 留作“null 也映射”的证据。
    }

    @Test
    @DisplayName("多条记录遍历转换与 BaseService 的 for 循环行为一致")
    void multiRowConversionMatchesForLoop() {
        // 模拟 BaseService.selectBaseList 拿到的多行结果
        java.util.List<Map<String, Object>> mapList = new java.util.ArrayList<>();
        Map<String, Object> r1 = new java.util.LinkedHashMap<>();
        r1.put("course_name", "HIIT训练");
        Map<String, Object> r2 = new java.util.LinkedHashMap<>();
        r2.put("course_name", "瑜伽冥想");
        // 【容易漏的一步】地图们得真的放进 list 里，
        //   否则下面的 for 循环遍历一个空集合，断言 size==2 必挂。
        mapList.add(r1);
        mapList.add(r2);

        // 复刻 selectBaseList 的字符串化 → 反序列化路径（原代码：
        //   for (Map<String,Object> map : mapList) {
        //       list.add(JSON.parseObject(JSON.toJSONString(map), eClass));
        //   }
        java.util.List<CourseInformationLike> list = new java.util.ArrayList<>();
        for (Map<String, Object> map : mapList) {
            list.add(JSONObject.parseObject(JSONObject.toJSONString(map), CourseInformationLike.class));
        }

        assert list.size() == 2
                : "两行输入应得到两个实体，得 " + list.size();
        assert "HIIT训练".equals(list.get(0).getCourseName())
                : "第一条课程名应为 HIIT训练";
        assert "HIIT训练".equals(list.get(0).getCourseName());
        assert "瑜伽冥想".equals(list.get(1).getCourseName())
                : "第二条课程名应为瑜伽冥想";
    }
}
