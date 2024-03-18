package com.saesig.index;

import com.saesig.managerBoard.ManagerBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DashBoardMapper {
    //새로운식구 목록 조회
    List<Map<String, Object>> selectAdoptions();

    //일상기록 목록 조회
    List<Map<String, Object>> selectDiarys();

    //관리자 게시판 목록 조회
    List<ManagerBoardDto> selectAdminPosts();

    //회원가입 건수
    Map<String, Object> countRegisteredMembers(DashBoardDto dashBoardDto);

    // 발송현황 건수
    List<Map<String, Object>> countMessageDeliverys(DashBoardDto dashBoardDto);

    Map<String,Object> countReports(DashBoardDto dashBoardDto);

    Map<String,Object> countInquiries(DashBoardDto dashBoardDto);

    Map<String,Object> countAdoptions(DashBoardDto dashBoardDto);

    Map<String,Object> countDiarys(DashBoardDto dashBoardDto);

    List<Map<String,Object>> countAdoptionStatus(DashBoardDto dashBoardDto);

    Map<String, Object> selectAdoption(DashBoardDto dashBoardDto);
}
