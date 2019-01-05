package io.anymobi.repositories.mybatis.mapper.user;


import io.anymobi.domain.dto.UserDTO;

import java.util.List;

public interface UserMapper {

	List<UserDTO> selectUserList(UserDTO user) throws Exception;

	UserDTO selectUser(Long id) throws Exception;
	
	void insertUser(UserDTO user) throws Exception;
	
	void updateTeset(UserDTO user) throws Exception;
	
}
