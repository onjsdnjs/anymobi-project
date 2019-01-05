package io.anymobi.services.mybatis.users;

import io.anymobi.domain.dto.UserDTO;
import io.anymobi.repositories.mybatis.mapper.user.UserMapper;
import io.anymobi.services.mybatis.AbstractBaseService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <PRE>
 * 1. author	:	(주)애니모비 시스템 개발본부
 * 2. date		:	2019.01
 * </PRE>
 *
 */
@Service("UserService")
public class UserServiceImpl extends AbstractBaseService<UserServiceImpl> implements UserService {
	
	@Override
	public List<UserDTO> selectUserList(UserDTO user) throws Exception {
		UserMapper mapper = getMapper(UserMapper.class);
		return mapper.selectUserList(user);
	}

	@Override
	public UserDTO selectUser(Long id) throws Exception {
		UserMapper mapper = getMapper(UserMapper.class);
		return mapper.selectUser(id);
	}

	@Override
	public void insertUser(UserDTO user) throws Exception {
		UserMapper mapper = getMapper(UserMapper.class);
		mapper.insertUser(user);
	}

	@Override
	public void updateUser(UserDTO user) throws Exception {
		commonSqlRepository.update("User.updateUser", user);
	}

}
