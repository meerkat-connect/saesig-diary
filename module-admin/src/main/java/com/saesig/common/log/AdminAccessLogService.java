package com.saesig.common.log;

import com.saesig.domain.log.AdminAccessLog;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminAccessLogService {
    private final AdminAccessLogRepository adminAccessLogRepository;

    public void insertLog(AdminAccessLog adminAccessLog){
        adminAccessLogRepository.save(adminAccessLog);
    }

    public List<AdminAccessLogResponseDto> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        return adminAccessLogRepository
                .findAll()
                .stream()
                .map(adminAccessLog -> modelMapper.map(adminAccessLog, AdminAccessLogResponseDto.class))
                .collect(Collectors.toList());
    }
}
