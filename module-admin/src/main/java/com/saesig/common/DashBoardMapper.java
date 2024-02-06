package com.saesig.common;

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
    List<Map<String, Object>> selectAdminPosts();

    //회원가입 건수
    List<Map<String, Object>> countRegisteredMembers();

    // 발송현황 건수
    List<Map<String, Object>> countMessageDeliverys();

    Integer countReports();

    Integer countInquiries();

    Integer countAdoptions();

    Integer countDiarys();
}
