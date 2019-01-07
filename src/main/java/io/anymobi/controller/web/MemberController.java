package io.anymobi.controller.web;

import io.anymobi.domain.dto.MemberDTO;
import io.anymobi.services.mybatis.members.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <PRE>
 * 1. author	:	(주)애니모비 시스템 개발본부
 * 2. date		:	2019.01
 * </PRE>
 *
 */
@RestController
@Slf4j
public class MemberController {

	@Autowired
	MemberService MemberService;
	
	@PostMapping(value="/rest/member/register")
	public void registerMember(MemberDTO Member) throws Exception {

		MemberService.insertMember(Member);
		
	}
	
	@GetMapping(value="/rest/member/{id}")
	public MemberDTO selectMember(@PathVariable Long id) throws Exception {

		MemberDTO Member = MemberService.selectMember(id);
		return Member;
	}

	@GetMapping(value="/rest/member/list")
	public List<MemberDTO> selectMemberList(MemberDTO Member) throws Exception {
		
		return MemberService.selectMemberList(Member);
	}
}
