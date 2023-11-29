package com.saesig.resource;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminResourceMapper {
    List<ResourceResponseDto> findAll();
}
