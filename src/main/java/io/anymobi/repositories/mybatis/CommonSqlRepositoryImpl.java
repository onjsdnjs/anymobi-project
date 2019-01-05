package io.anymobi.repositories.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * <PRE>
 * 1. author	:	(주)애니모비 시스템 개발본부
 * 2. date		:	2019.01
 * </PRE>
 *
 */
@Repository("commonSqlRepository")
public class CommonSqlRepositoryImpl implements CommonSqlRepository {
	
	private SqlSession sqlSession;

    private boolean externalSqlSession;

    @Autowired(required = false)
    public final void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        if (!this.externalSqlSession) {
            this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
        }
    }

    @Autowired(required = false)
    public final void setSqlSessionTemplate(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
        this.externalSqlSession = true;
    }
    
    
	public SqlSession getSqlSession() {

    	return sqlSession;
	}

	public int delete(String qName, Object parameter) throws SQLException {
		return this.sqlSession.delete(qName, parameter);
	}

	public int insert(String qName, Object parameter) throws SQLException {
		return this.sqlSession.insert(qName, parameter);
	}

	public int update(String qName, Object parameter) throws SQLException {
		return this.sqlSession.update(qName, parameter);
	}

	public Object selectOne(String qName, Object parameter) throws SQLException {
		return this.sqlSession.selectOne(qName, parameter);
	}

	@SuppressWarnings("rawtypes")
	public List selectList(String qName, Object parameter) throws SQLException {
		return this.sqlSession.selectList(qName, parameter);
	}
	
	public int delete(String qName) throws SQLException {
		return delete(qName, null);
	}

	public int insert(String qName) throws SQLException {
		return insert(qName, null);
	}

	public int update(String qName) throws SQLException {
		return update(qName, null);
	}

	public Object selectOne(String qName) throws SQLException {
		return selectOne(qName, null);
	}

	@SuppressWarnings("rawtypes")
	public List selectList(String qName) throws SQLException {
		return selectList(qName, null);
	}

}
