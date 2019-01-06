package io.anymobi.services.mybatis.Members;

import io.anymobi.domain.dto.MemberDTO;
import io.anymobi.repositories.mybatis.mapper.member.MemberMapper;
import io.anymobi.services.mybatis.AbstractBaseService;
import io.anymobi.services.mybatis.users.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <PRE>
 * 1. author	:	(주)애니모비 시스템 개발본부
 * 2. date		:	2019.01
 * </PRE>
 *
 */
@Service("MemberService")
public class MemberServiceImpl extends AbstractBaseService<MemberServiceImpl> implements MemberService {

	
	@Override
	public List<MemberDTO> selectMemberList(MemberDTO Member) throws Exception {
		MemberMapper mapper = getMapper(MemberMapper.class);
		return mapper.selectMemberList(Member);
	}

	@Override
	public MemberDTO selectMember(Long id) throws Exception {
		MemberMapper mapper = getMapper(MemberMapper.class);
		return mapper.selectMember(id);
	}

	@Override
	public void insertMember(MemberDTO Member) throws Exception {
		MemberMapper mapper = getMapper(MemberMapper.class);
		mapper.insertMember(Member);
	}

	@Override
	public void updateMember(MemberDTO Member) throws Exception {
		commonSqlRepository.update("Member.updateMember", Member);
	}

}
