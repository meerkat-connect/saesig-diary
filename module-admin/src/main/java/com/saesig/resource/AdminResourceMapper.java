package com.saesig.resource;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminResourceMapper {
    List<ResourceResponseDto> findAll(@Param("category") String category);
}
