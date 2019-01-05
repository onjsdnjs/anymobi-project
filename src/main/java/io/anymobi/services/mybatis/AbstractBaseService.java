package io.anymobi.services.mybatis;

import io.anymobi.common.CommonLogger;
import io.anymobi.repositories.mybatis.CommonSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <PRE>
 * 1. author	:	(주)애니모비 시스템 개발본부
 * 2. date		:	2019.01
 * </PRE>
 *
 */
public abstract class AbstractBaseService<T> extends CommonLogger<T> {

	@Autowired
	protected CommonSqlRepository commonSqlRepository;
	
	protected <Mapper> Mapper getMapper(Class<Mapper> clz) {
		Mapper mapper = (Mapper) commonSqlRepository.getSqlSession().getMapper(clz);
		return mapper;
	}
}
