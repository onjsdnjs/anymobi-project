package io.anymobi.services.mybatis.member;

import io.anymobi.domain.dto.hr.MemberDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Override
    public List<MemberDTO> selectMemberList(MemberDTO user) throws Exception {
        return null;
    }

    @Override
    public MemberDTO selectMember(Long id) throws Exception {
        return null;
    }

    @Override
    public void insertMember(MemberDTO user) throws Exception {

    }

    @Override
    public void updateMember(MemberDTO user) throws Exception {

    }
}
