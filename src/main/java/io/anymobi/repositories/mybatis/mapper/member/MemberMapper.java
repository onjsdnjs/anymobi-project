package io.anymobi.repositories.mybatis.mapper.member;


import io.anymobi.domain.dto.hr.MemberDto;

import java.util.List;

public interface MemberMapper {

	List<MemberDto> selectMemberList(MemberDto Member) throws Exception;

	MemberDto selectMember(Long id) throws Exception;
	
	void insertMember(MemberDto Member) throws Exception;
	
	void updateTeset(MemberDto Member) throws Exception;
	
}
