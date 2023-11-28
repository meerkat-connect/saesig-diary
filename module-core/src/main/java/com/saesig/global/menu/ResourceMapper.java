package com.saesig.global.menu;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceMapper {
    List<ResourceItem> findAll();
}
