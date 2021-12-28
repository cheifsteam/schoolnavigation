package com.hqd.schoolnavigation.util.copyUtils;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@FunctionalInterface
public interface BeanCopyUtilCallBack <S, T> {

    /**
     * 定义默认回调方法
     * @param t
     * @param s
     */
    void callBack(S t, T s);
}