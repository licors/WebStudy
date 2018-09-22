package spms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import spms.annotation.Component;
import spms.vo.Project;

@Component("projectDao")
public class MySqlProjectDao implements ProjectDao {
	SqlSessionFactory sqlSessionFactory;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public List<Project> selectList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.ProjectDao.selectList");
		} finally {
			sqlSession.close();
		}
//			rs = stmt.executeQuery(
//					"select PNO, PNAME, STA_DATE, END_DATE, STATE" + " from PROJECTS" + " order by PNO desc");
	}

	@Override
	public int insert(Project project) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.ProjectDao.insert", project);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
//					.prepareStatement("insert into PROJECTS(PNAME, CONTENT, STA_DATE, END_DATE, STATE, CRE_DATE, TAGS)"
//							+ " values(?, ?, ?, ?, 0, now(), ?)");
			}

	@Override
	public int delete(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.delete("spms.dao.ProjectDao.delete",no);
			sqlSession.commit();
			return count;
		}finally {
			sqlSession.close();
		}
//			return stmt.executeUpdate("delete from PROJECTS where PNO=" + no);
			}

	@Override
	public Project selectOne(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.ProjectDao.selectOne", no);
		} finally {
			sqlSession.close();
		}
//					"select PNO, PNAME, CONTENT, STA_DATE, END_DATE, STATE, CRE_DATE, TAGS from PROJECTS where PNO="
//							+ no);
	}

	@Override
	public int update(Project project) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.update("spms.dao.ProjectDao.update", project);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
//					"update PROJECTS set PNAME=?, CONTENT=?, STA_DATE=?, END_DATE=?, STATE=?, TAGS=?" + " where PNO=?");
		
	}
}
