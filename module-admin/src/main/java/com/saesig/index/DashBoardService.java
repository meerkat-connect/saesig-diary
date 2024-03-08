package com.saesig.index;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class DashBoardService {
    private final DashBoardMapper dashBoardMapper;

    // 발송 현황
    public Map<String,Object> getSendStatus() {
        //일간, 주간, 월간, 종합

        return null;
    }
}
