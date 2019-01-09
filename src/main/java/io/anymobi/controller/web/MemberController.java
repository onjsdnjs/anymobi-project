package io.anymobi.controller.web;

import io.anymobi.domain.dto.hr.MemberDTO;
import io.anymobi.services.mybatis.member.MemberService;
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
	MemberService memberService;
	
	@PostMapping(value="/rest/member/register")
	public void registerMember(MemberDTO member) throws Exception {

		memberService.insertMember(member);
		
	}
	
	@GetMapping(value="/rest/member/{id}")
	public MemberDTO selectMember(@PathVariable Long id) throws Exception {

		MemberDTO Member = memberService.selectMember(id);
		return Member;
	}

	@GetMapping(value="/rest/member/list")
	public List<MemberDTO> selectMemberList(MemberDTO member) throws Exception {
		
		return memberService.selectMemberList(member);
	}
}
