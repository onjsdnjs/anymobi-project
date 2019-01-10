package io.anymobi.services.mybatis.member;

import io.anymobi.domain.dto.hr.MemberDto;

import java.sql.SQLException;
import java.util.List;


/**
 * <PRE>
 * 1. author	:	(주)애니모비 시스템 개발본부
 * 2. date		:	2019.01
 * </PRE>
 *
 */
public interface MemberService {
	
	List<MemberDto> selectMemberList(MemberDto user) ;

	MemberDto selectMember(int id) ;
	
	void insertMember(MemberDto user) ;
	
	void updateMember(MemberDto memberDto);
}
