package com.saesig.resource;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceMapper {
    List<ResourceResponseDto> findAll();
}
