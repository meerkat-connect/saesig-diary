package com.saesig.api.adopt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdoptServiceImpl implements AdoptService{

    @Autowired
    private AdoptMapper adoptMapper;
    @Override
    @Transactional
    public Long insertAdopt(AdoptDto adoptDto) throws Exception{
        Long insertId = adoptMapper.insertAdopt(adoptDto);
        adoptDto.setId(insertId);
        return adoptMapper.insertAdoptVaccine(adoptDto);
    };

    @Override
    public List<AdoptDto> selectAdoptList(AdoptDto adoptDto) throws Exception{
        return adoptMapper.selectAdoptList(adoptDto);
    };

    @Override
    @Transactional
    public Long updateAdopt(AdoptDto adoptDto) throws Exception{
        adoptMapper.updateAdopt(adoptDto);
        return adoptMapper.updateAdoptVaccine(adoptDto);
    };

    @Override
    public Long deleteAdopt(AdoptDto adoptDto) throws Exception{
        return adoptMapper.deleteAdopt(adoptDto);
    };
}
