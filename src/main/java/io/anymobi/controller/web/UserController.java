package io.anymobi.controller.web;

import io.anymobi.common.CommonLogger;
import io.anymobi.domain.dto.UserDTO;
import io.anymobi.services.mybatis.users.UserService;
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
public class UserController extends CommonLogger<UserController> {

	@Autowired
    UserService userService;
	
	@PostMapping(value="/rest/member/register")
	public void registerMember(UserDTO user) throws Exception {

		userService.insertUser(user);
		
	}
	
	@GetMapping(value="/rest/member/{id}")
	public UserDTO selectMember(@PathVariable Long id) throws Exception {

		UserDTO user = userService.selectUser(id);
		return user;
	}

	@GetMapping(value="/rest/member/list")
	public List<UserDTO> selectMemberList(UserDTO user) throws Exception {
		
		return userService.selectUserList(user);
	}
}
