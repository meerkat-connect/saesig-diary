package com.saesig.saesigManage;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SaesigManageServiceImpl implements SaesigManageService{
    private final SaesigManageMapper saesigManageMapper;

    @Override
    public List<AdoptListDto> selectAdoptList(Map<String, Object> param) throws Exception {
        return saesigManageMapper.selectAdoptList(param);
    }

    @Override
    public AdoptListDto selectAdoptById(Long id) throws Exception {
        return saesigManageMapper.selectAdoptById(id);
    }
}