package io.anymobi.services.mybatis.users;

import io.anymobi.domain.dto.UserDTO;

import java.util.List;


/**
 * <PRE>
 * 1. author	:	(주)애니모비 시스템 개발본부
 * 2. date		:	2019.01
 * </PRE>
 *
 */
public interface UserService {
	
	List<UserDTO> selectUserList(UserDTO user) throws Exception;

	UserDTO selectUser(Long id) throws Exception;
	
	void insertUser(UserDTO user) throws Exception;
	
	void updateUser(UserDTO user) throws Exception;
}
