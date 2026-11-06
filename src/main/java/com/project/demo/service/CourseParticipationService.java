package com.project.demo.service;

import com.project.demo.dao.CourseInformationMapper;
import com.project.demo.entity.CourseInformation;
import com.project.demo.entity.CourseParticipation;
import com.project.demo.exception.BusinessException;
import com.project.demo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DuplicateKeyException;

import java.util.*;

/**
 * 课程参与：(CourseParticipation)表服务接口
 *
 */
@Service
public class CourseParticipationService extends BaseService<CourseParticipation> {

    /** 报名的业务逻辑要改课程表的计数，所以直接注入课程 Mapper */
    @Autowired
    private CourseInformationMapper courseInformationMapper;
    /**
     * 报名：校验课程 → 防重复 → 落报名记录 → 原子扣名额。
     *
     * <p>【这一整段必须在同一个事务里】中途任何一步失败，前面已写入的内容都要一起消失。
     * 事务边界放在 Service（业务方法的边界），Controller 上那个 @Transactional 要删掉 ——
     * 一个请求只该有一个事务入口。
     */

    @Transactional(rollbackFor = Exception.class)
    public void enroll(Integer courseId, Integer memberUser, String memberName) {
        if (courseId == null || memberUser == null) {
            throw new BusinessException("课程和会员都必须指定");
        }


        CourseInformation course = courseInformationMapper.selectById(courseId);
        if (course == null) {
            throw new BusinessException("课程不存在：id=" + courseId);
        }


        // 幂等守卫：同一会员 + 同一课程只能报一次。
        // （理想做法是数据库唯一键 uk_member_course(member_user, source_id) 兜底，
        //   但这张表的历史数据 member_user 全是 0，加键前要先清理，留给你决定。）
        Map<String, String> query = new HashMap<>();
        query.put("source_table", "course_information");
        query.put("source_id", String.valueOf(courseId));
        query.put("member_user", String.valueOf(memberUser));
        if (!selectBaseList(select(query, Collections.emptyMap())).isEmpty()) {
            throw new BusinessException("你已报名该课程，请勿重复报名");
        }

        // ① 先落报名记录。课程冗余字段一并带上：报名表是"历史快照"，
        //    课程以后改了时间地点，历史报名记录不该跟着变。
        Map<String, Object> record = new HashMap<>();
        record.put("source_table", "course_information");
        record.put("source_id", courseId);
        record.put("member_user", memberUser);
        record.put("member_name", memberName);
        record.put("course_name", course.getCourse_name());
        record.put("course_time", course.getCourse_time());
        record.put("course_location", course.getCourse_location());
        record.put("name_of_coach", course.getName_of_coach());


        try {
            insert(record);
        }
        catch (DuplicateKeyException e){
            // 唯一键在 insert 那一刻才说话，这是「并发窗口」的唯一防线：
            // 两个请求同时通过上面的 select 检查时，第二个会在这里被数据库拦住。
            // 这里必须继续抛 —— catch 住不抛 = 事务看到「正常结束」= 提交。
            if (e.getMessage() != null && e.getMessage().contains("uk_member_course")) {
                throw new BusinessException("你已经报名该课程,请勿重复报名");
            }
            throw e;// 其他唯一键（比如课程时间冲突）交给全局处理器，别翻译错
        }


        // ② 再原子占名额。0 表示满了 —— 抛异常，①写进去的那条记录随之回滚。
        if (courseInformationMapper.occupySeat(courseId) == 0) {
            throw new BusinessException("名额已满，报名失败");
        }
    }



}
