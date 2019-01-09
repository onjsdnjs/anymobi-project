package io.anymobi.services.mybatis.member;

import io.anymobi.domain.dto.hr.MemberDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Override
    public List<MemberDto> selectMemberList(MemberDto user) throws Exception {
        return null;
    }

    @Override
    public MemberDto selectMember(Long id) throws Exception {
        return null;
    }

    @Override
    public void insertMember(MemberDto user) throws Exception {

    }

    @Override
    public void updateMember(MemberDto user) throws Exception {

    }
}
