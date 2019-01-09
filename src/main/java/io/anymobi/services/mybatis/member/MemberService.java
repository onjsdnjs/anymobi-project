package io.anymobi.services.mybatis.member;

import io.anymobi.domain.dto.hr.MemberDTO;

import java.util.List;


/**
 * <PRE>
 * 1. author	:	(주)애니모비 시스템 개발본부
 * 2. date		:	2019.01
 * </PRE>
 *
 */
public interface MemberService {
	
	List<MemberDTO> selectMemberList(MemberDTO user) throws Exception;

	MemberDTO selectMember(Long id) throws Exception;
	
	void insertMember(MemberDTO user) throws Exception;
	
	void updateMember(MemberDTO user) throws Exception;
}
