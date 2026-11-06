package com.project.demo.service;

import com.project.demo.entity.CourseInformation;
import com.project.demo.entity.CourseParticipation;
import com.project.demo.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.*;    /**
     * 课程信息：(CourseInformation)表服务接口
     *
     * <p>原来是空壳 —— 仅仅继承 BaseService<CourseInformation>。
     * 这一回我们加两个真正的业务方法，让这个类开始承担「课程管理」里
     * 的一致性校验和报名席位释放的职责。
     *
     * <p>两个方法的共同特点：
     * <ul>
     *   <li>都使用 BaseService 已经具备的能力（insert / selectBaseList / delete）；
     *     <strong>不重复造轮子</strong>，把通用读写留给父类，只在子类写领域逻辑。
     *   <li>都自己决定参数是什么（不是照搬 Controller 传来的 Map），
     *     <strong>让业务方法的契约尽量面向实体/基础类型</strong>，方便以后写单元测试
     *     或者在别的 Service 里复用。
     *   <li>逻辑失败的时候抛 RuntimeException，成功时返回结果。
     *     <strong>不返回 null 来表达「失败」</strong>，null 在 BaseService.selectBaseList
     *     里是合法值（没有记录），在业务方法里用它表达语义会混。
     * </ul>
     */
@Service
public class CourseInformationService extends BaseService<CourseInformation> {

    /**
     * 新增／修改课程的时候，检查同一时间段有没有已存在的课程。
     *
     * <p>业务规则（你可以按自己的需求再细化）：
     * <ul>
     *  <li>「上课时间」字段 {@link CourseInformation#//course_time} 是字符串，
     *       本项目里存储的是「日期+时间段」这种形式（例如 "2024-03-15 10:00~11:30"）。
     *       目前我们不做字符串拆解，改为「精确相等的 course_time 不允许重复」；
     *       如果你想支持「同一天的任意时间段冲突」，可以在这个方法里先
     *       做字符串解析（截取日期部分）再比较。
     *   <li>冲突检查范围：本方法不过滤 course_information_id，
     *     <strong>修改课程本身的时候也会把自己纳入比较范围</strong>，
     *     所以调用方在改课的时候需要把当前课的 id 排除掉。
     *   <li>冲突 = 同一时间 <strong>且</strong> 同一地点（AND，不是 OR）：
     *     时间相同但地点不同不算冲突，地点相同但时间不同也不算。
     *     地点为空时降级为只按时间判
     * </ul>
     *
     * @param target  要插入或更新的课程实体（用于取 course_time）
     * @param excludeId  需要排除的课程 id（防止修改自己的时候触发自身冲突）
     *                   传 null 表示插入新课（不需要排除）
     * @return  与目标 course_time 相同的已存在课程列表；
     *         为空则说明没有冲突
     */
//    public List<CourseInformation> checkCourseConflict(CourseInformation target,
//                                                        Integer excludeId) {
//        if (target == null) {
//            throw new IllegalArgumentException("参数 target 不能为空");
//        }
//        String targetTime = target.getCourse_time();
//        if (targetTime == null || targetTime.trim().isEmpty()) {
//            // 时间为空不算冲突 —— 允许保存没有时间的草稿课，
//            // 但保存正式课的时候调用方最好先校验时间。
//            return Collections.emptyList();
//        }
//
//        // 【为什么用 Map/String 而不用 Controller 的配置？】
//        //   BaseService.select() 是用 StringBuffer 拼 SQL 的，
//        //   它的筛选条件来自 query Map；这里我们构造一个 query Map
//        //   把 "course_time" 变成等值条件传进去。
//        Map<String, String> query = new HashMap<>();
//        query.put("course_time", targetTime.trim());
//
//        // 查出所有 course_time == targetTime 的课程
//        List<CourseInformation> sameTimeList = selectBaseList(
//                select(query, Collections.emptyMap()));
//
//        // 【为什么在内存里排除掉 excludeId，而不在 SQL 里写 NOT EQUALS？】
//        //   SQL 层面写一个 "and course_information_id != ?" 是做得到的，
//        //   但 BaseService.select() 目前不支持「除某个 id 之外」，
//        //   而且我们只有一条查询，排除一条记录在内存里做 O(n) 代价最小。
//        //   日后如果表很大（成千上万条课），可以在 query 里直接加
//        //   "and course_information_id != #{id}" 到 toWhereSql 里，但现在先简单。
//        if (excludeId != null) {
//            sameTimeList.removeIf(course ->
//                    course.getCourse_information_id() != null
//                            && course.getCourse_information_id().equals(excludeId));
//        }
//
//        return sameTimeList;
//    }
    public List<CourseInformation> checkCourseConflict(CourseInformation target,
                                                      Integer excludeId){
        if (target == null) {
            throw new IllegalArgumentException("参数 target 不能为空");
        }
        String targetTime = target.getCourse_time() == null
                ? null : target.getCourse_time().trim();
        String targetLocation = target.getCourse_location() == null
                ? null : target.getCourse_location().trim();
        if (targetTime == null || targetTime.isEmpty()) {
            return Collections.emptyList();
        }
        Map<String, String> query = new HashMap<>();
        query.put("course_time", targetTime);
        // 【设计决策：地点为空就降级为只按时间判冲突】
        //   不填地点 ≠ 同一地点，但也 ≠ 不冲突。
        //   这里选「降级」：拿不到地点就退回时间单条件，
        //   这样缺数据不会把课程永远卡在冲突上，调用方也不会误以为已校验过地点。
        if (targetLocation != null && !targetLocation.isEmpty()) {
            query.put("course_location", targetLocation);
        }
        List<CourseInformation> conflicts = selectBaseList(
                select(query, Collections.emptyMap()));

        if (excludeId != null) {
            conflicts.removeIf(course ->
                    course.getCourse_information_id() != null
                            && course.getCourse_information_id().equals(excludeId));
        }
        return conflicts;
    }

    /**
     * 撤销一笔报名记录（{@link CourseParticipation}），
     * 并释放其占用的名额。
     *
     * <p>这里的「释放名额」语义取决于你的业务模型，我们目前采用最简单的做法：
     * <ul>
     *   <li>CourseParticipation 里存了 {@link CourseParticipation#/source_table}
     *       和 {@link CourseParticipation#/source_id} —— 表示这条报名记录
     *       是从哪张表／哪条记录来的；
     *   <li>「释放名额」在本方法里表现为：
     *      <ol>
     *        <li>删掉这条 CourseParticipation 记录本身
     *        <li>视业务需要，调用方在此基础上再去减掉课程表里的
     *            已报名人数 / 更新剩余席位 —— 这一步不在本方法里做，
     *            因为课程表里的「名额字段」命名和语义我尚未确认，
     *            所以本方法只负责删掉报名记录，保证报名記録不再存在。
     *      </ol>
     * </ul>
     *
     * <p>【为什么删报名记录和更新课程名额不写在同一个方法里？】
     *   事务边界和业务原子性是两个层次的问题。删掉报名记录本身是原子操作，
     *   「课程名额字段减 1」则取决于课程表的设计：有的系统在课程表里有
     *   {@code rest_seats} 字段直接减，有的系统改的是大表里的统计字段，有的
     *   系统根本不存名额字段而是用报名记录数来算。把两件事写在一起会把
     *   不同课程模型的差异硬编码进这个通用 Service 里，把将来接其他实体
     *   （会员卡、健身建议等也都有 source_table/source_id）的时候绑死。
     *
     * @param participationId 要撤销的报名记录 id
     * @return 删掉一条则返回 1（与方法文档一致：查不到记录则抛异常）
     */
    @org.springframework.transaction.annotation.Transactional
    public int cancelParticipationAndFreeSeat(int participationId) {
        // 【为什么用 BaseService.delete() 而不写一条原生 delete SQL？】
        //   BaseService.delete(Map query, Map config) 已经封装了
        //   "delete from `course_participation` where course_participation_id = ?"
        //   的逻辑，这里直接用它，意味着如果以后 BaseService.delete 的实现
        //   有所改进（比如软删、记录日志、审计字段），本方法自动受益。
        //   BaseService.delete() 内部调用 BaseMapper.delete(wrapper)，
        //   而 BaseMapper 继承自 MyBatis-Plus BaseMapper<E>，后者delete()
        //   返回影响行数 int —— 但本项目的 BaseService.delete() 声明为 void，
        //   所以删完拿不到影响行数，因此我们改为「删前先查是否存在」再删。
        // 【删前先查 - 不存在则抛异常】
        //   BaseService.delete() 返回 void，删完拿不到影响行数。
        //   因此改为「删前先查是否存在」，查到空则抛异常；查到有记录
        //   再调用 delete() 删掉（事务内操作）。
        //   为什么不用 findOne()？
        //   <ul>
        //     <li>findOne() 的实现里是 selectBaseOne(select(...))，
        //         一路经 BaseMapper.selectBaseOne → JSON 转换 → 实体，
        //         中间有好几步；对于「删一条」这种操作来说太重。
        //     <li>这里用 select() + selectBaseList() 也是查一次，
        //         但用的是同一个 BaseService 的通用 select 能力，
        //         和 checkCourseConflict 用的查询路径一致，易懂。
        //   </ul>
        Map<String,String> queryForDelete = new HashMap<>();
        queryForDelete.put("course_participation_id", String.valueOf(participationId));

        List<CourseParticipation> existing = selectBaseList(
                select(queryForDelete, Collections.emptyMap()));
        if (existing.isEmpty()) {
            throw new IllegalArgumentException(
                    "要撤销的报名记录不存在，participationId=" + participationId);
        }

        // 存在再删。删之前如果抛异常，事务回滚，不需要手动回滚。
        delete(queryForDelete, Collections.emptyMap());

        // 【释放名额的语义延伸】（不在本方法内实现）
        //   如果你的课程表（course_information）里有表示「已报名人数」或
        //   「剩余席位」的字段，可以在删除报名记录成功后调用一个单独的方法
        //   去更新它。本方法目前只负责删掉报名记录，释放名额的后半步
        //   留给调用方根据实际表结构决定。
        //   提醒：如果课程表里有这样的字段，将来可以在这个位置加一行
        //   updateBaseSql(...) 来原子地递减它，然后整个事务就会把
        //   「删报名记录 + 减名额」绑在一起 —— 届时就不再返回 int
        //   而是返回 void，由上游捕获异常来判断成败。

        return 1;
    }
}
