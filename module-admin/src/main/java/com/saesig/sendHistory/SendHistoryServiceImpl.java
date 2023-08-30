package com.saesig.sendHistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendHistoryServiceImpl implements SendHistoryService {

    @Autowired
    private SendHistoryMapper sendHistoryMapper;

    @Override
    public List<SendHistoryDto> selectSendHistoryList(SendHistoryDto shd) throws Exception {
        return sendHistoryMapper.selectSendHistoryList(shd);
    }

    @Override
    public SendHistoryDto selectSendHistory(Long id) throws Exception {
        return sendHistoryMapper.selectSendHistory(id);
    }
}
