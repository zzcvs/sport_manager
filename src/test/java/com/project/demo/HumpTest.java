package com.project.demo;

import com.project.demo.service.base.BaseService;

/**
 * 用例集合：验证 com.project.demo.service.base.BaseService.humpToLine()。
 *
 * <p>注意区别：
 * <ul>
 *   <li>你在第一轮写的 HumpTest.java（放在业务包根目录）是一份静态测例草稿；
 *       它调用的是全限定方法名 {@code com.project.demo.service.base.BaseService.humpToLine}
 *       而不用 Spring / MyBatis 上下文 —— 所以 IDEA 里可以运行。
 *   <li>这个测试文件放在 {@code src/test/java} 中，是为了用起来
 *       {@link org.junit.jupiter.api.Test} 与 Maven 的 {@code spring-boot-starter-test}
 *       依赖（此前一直白引着）。跑法：{@code mvn test}。
 * </ul>
 *
 * <p><tr> 田特别说明：
 *    BaseService.humpToLine() 在实现上存在连写大写字母陷阱：
 *   <ul>
 *     <li>"HTTPResponse" → "h_t_t_p_response"（每一个大写都加一个下划线）
 *     <li>"XMLHTTPRequest" 会变成 "x_m_l_h_t_t_p_request"
 *   </ul>
 *    这正是测试用例 4 明确写出来的用意：
 *   <ul>
 *     <li>用例 4 预测的是 "h_t_t_p_response"（同时也是实现当前行为）
 *     <li>用例 5、6 验证 null / 空字符串的边界保护
 *   </ul>
 *
 * <p>为了让未来移除掉，当你有空的时候可以把 BaseService 的实现换成
 * Apache Commons {@code CaseUtils.toCamelCase} 的方向改掉，或者自己写
 * 正确版（一个大写字母只加一个下划线，且第一位不加）。
 */
public class HumpTest {

    /**
     * 6 个用例：
     * <ol>
     *   <li>正常类名（核心场景）
     *   <li>单个单词
     *   <li>已经是小写，应无变化
     *   <li>连写大写字母（<strong>预测+验证</strong>）
     *   <li>null 输入
     *   <li>空字符串
     * </ol>
     */
    public static void main(String[] args) {
        // 用例 1：正常类名
        check("CourseInformation", "course_information");
        // 用例 2：单个单词
        check("User", "user");
        // 用例 3：已经是小写，不应有任何变化
        check("course_name", "course_name");
        // 用例 4：连写大写字母 —— 先预测，再运行验证
        check("HTTPResponse", "h_t_t_p_response");
        // 用例 5：null 输入
        check(null, null);
        // 用例 6：空字符串
        check("", "");

        System.out.println("--- HumpTest.main 跑完 ---");
    }

    static void check(String input, String expected) {
        String actual = BaseService.humpToLine(input);
        boolean pass = (actual == null) ? expected == null : actual.equals(expected);
        System.out.println((pass ? "PASS" : "FAIL")
                + " | 输入:" + input
                + " | 期望:" + expected + " | 实际:" + actual);
    }
}
