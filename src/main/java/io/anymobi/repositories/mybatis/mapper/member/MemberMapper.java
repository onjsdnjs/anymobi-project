package io.anymobi.repositories.mybatis.mapper.member;


import io.anymobi.domain.dto.MemberDTO;

import java.util.List;

public interface MemberMapper {

	List<MemberDTO> selectMemberList(MemberDTO Member) throws Exception;

	MemberDTO selectMember(Long id) throws Exception;
	
	void insertMember(MemberDTO Member) throws Exception;
	
	void updateTeset(MemberDTO Member) throws Exception;
	
}
