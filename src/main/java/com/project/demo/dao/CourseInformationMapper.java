package com.project.demo.dao;

import com.project.demo.dao.base.BaseMapper;
import com.project.demo.entity.CourseInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 课程信息：(CourseInformation)Mapper接口
 *
 */
@Mapper
public interface CourseInformationMapper extends BaseMapper<CourseInformation>{

    /**
     * 原子占用一个名额：只有"还没满"时才 +1。
     *
     * <p>【这条 SQL 是整节课的核心】它把「读 → 判断 → 改」压成了一条语句：
     * - MySQL 执行 UPDATE 时会对命中的行加排他锁（X 锁），
     *   并发的第二个事务会<b>阻塞等待</b>，拿到锁后重新读到的已经是 +1 之后的值；
     * - 所以 `enrolled_count < course_capacity` 的判断永远基于"锁内最新值"，
     *   不存在 check-then-act 的窗口。
     *
     * <p>返回值是<b>受影响行数</b>：1 = 抢到名额，0 = 已经满了（条件不成立）。
     * 用返回值判断成败，比"先 SELECT 看一眼再 UPDATE"安全一个数量级。
     */

    @Update("UPDATE `course_information` SET enrolled_count = enrolled_count + 1 " +
            "WHERE course_information_id = #{id} AND " +
            "enrolled_count < course_capacity")
    int occupySeat(@Param("id") Integer id);
}
