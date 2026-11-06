package com.project.demo.exception;

/**
 * 业务规则不满足时抛的异常（名额满、重复报名、课程不存在……）。
 *
 * <p>【为什么必须 extends RuntimeException】Spring 的 @Transactional 默认只在
 * <b>RuntimeException 和 Error</b> 上回滚；抛 checked Exception（比如 Exception、
 * IOException）默认<b>照常提交</b>。这是新手最常踩的事务坑 —— 报错了，数据却进去了。
 * 要么像这里一样继承 RuntimeException，要么在 @Transactional 上写 rollbackFor = Exception.class。
 */

public class BusinessException extends RuntimeException{
    private final Integer code;

    public BusinessException(String message) {
        this(30000, message);
    }
    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public  Integer getCode() {
        return code;
    }
}
