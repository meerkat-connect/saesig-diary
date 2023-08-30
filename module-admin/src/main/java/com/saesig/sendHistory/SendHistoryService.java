package com.saesig.sendHistory;

import java.util.List;

public interface SendHistoryService {
    List<SendHistoryDto> selectSendHistoryList(SendHistoryDto shd) throws Exception;

    SendHistoryDto selectSendHistory(Long id) throws Exception;
}
