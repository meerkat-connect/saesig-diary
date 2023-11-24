package com.saesig.diaryManage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryManageServiceImpl implements DiaryManageService {
    private final DiaryManageMapper diaryManageMapper;
    @Override
    public List<DiaryManageDto> selectDiaryList(DiaryManageDto param) throws Exception{
        return diaryManageMapper.selectDiaryList(param);
    };

    @Override
    public DiaryManageDto selectDiaryCntData(DiaryManageDto param) throws Exception{
        return diaryManageMapper.selectDiaryCntData(param);
    };

    @Override
    public DiaryManageDto selectDiaryById(DiaryManageDto param) throws Exception{
        DiaryManageDto result = diaryManageMapper.selectDiaryById(param);
        result.setTagDtoList(diaryManageMapper.selectDiaryTagListById(param));
        return result;
    };

    @Override
    public List<DiaryTagDto> selectDiaryTagListById(DiaryManageDto param) throws Exception{
        return diaryManageMapper.selectDiaryTagListById(param);
    };

    @Override
    public Long updateDiaryInfo(DiaryManageDto param) throws Exception{
        if (param.getDiaryStatus().getKey().equals("STOP")){
            param.setIsDeleted("Y");
        }else{
            param.setIsDeleted("N");
        }
        return diaryManageMapper.updateDiaryInfo(param);
    };
}
