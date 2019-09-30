package com.yqh.conversion;

/**
 * @author yangq
 * Create time in 2019-09-30 15:49
 */
public interface BaseConversion<M, D> {

    M toModel(D d);

    D toDto(M m);
}
