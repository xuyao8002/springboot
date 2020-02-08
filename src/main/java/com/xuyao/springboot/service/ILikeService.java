package com.xuyao.springboot.service;


public interface ILikeService {

    void like(Long from, Long to);

    void unlike(Long from, Long to);

}
