package io.anymobi.services.mybatis.Members;

import io.anymobi.common.provider.MqPublisher;
import io.anymobi.domain.dto.MemberDTO;
import io.anymobi.domain.dto.MessagePacket;
import io.anymobi.repositories.mybatis.mapper.member.MemberMapper;
import io.anymobi.services.mybatis.AbstractBaseService;
import io.anymobi.services.mybatis.users.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
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

	private final MqPublisher mqPublisher;

	public MemberServiceImpl(MqPublisher mqPublisher) {
		this.mqPublisher = mqPublisher;
	}

	@Override
	public List<MemberDTO> selectMemberList(MemberDTO member) throws Exception {
		MemberMapper mapper = getMapper(MemberMapper.class);
		return mapper.selectMemberList(member);
	}

	@Override
	public MemberDTO selectMember(Long id) throws Exception {
		MemberMapper mapper = getMapper(MemberMapper.class);
		return mapper.selectMember(id);
	}

	@Override
	public void insertMember(MemberDTO member) throws Exception {
		MemberMapper mapper = getMapper(MemberMapper.class);
		mapper.insertMember(member);

		MessagePacket messagePacket = MessagePacket.builder()
				.data(member)
				.build();
		mqPublisher.websockMessagePublish(messagePacket);
	}

	@Override
	public void updateMember(MemberDTO member) throws Exception {
		commonSqlRepository.update("Member.updateMember", member);
	}

}
