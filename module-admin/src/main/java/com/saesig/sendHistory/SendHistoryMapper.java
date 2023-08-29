package com.saesig.sendHistory;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SendHistoryMapper {
    List<SendHistoryDto> selectSendHistoryList(SendHistoryDto shd) throws Exception;

    SendHistoryDto selectSendHistory(Long id) throws Exception;
}
