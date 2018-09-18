package spms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.annotation.Component;
import spms.vo.Project;

@Component("projectDao")
public class MySqlProjectDao implements ProjectDao {
	DataSource ds;

	public void setDataSource(DataSource dataSource) {
		ds = dataSource;
	}

	@Override
	public List<Project> selectList() throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(
					"select PNO, PNAME, STA_DATE, END_DATE, STATE" + " from PROJECTS" + " order by PNO desc");

			ArrayList<Project> projects = new ArrayList<>();

			while (rs.next()) {
				projects.add(new Project().setNo(rs.getInt("PNO")).setTitle(rs.getString("PNAME"))
						.setStartDate(rs.getDate("STA_DATE")).setEndDate(rs.getDate("END_DATE"))
						.setState(rs.getInt("STATE")));
			}
			return projects;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				throw e;
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				throw e;
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				throw e;
			}
		}
	}

	@Override
	public int insert(Project project) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con
					.prepareStatement("insert into PROJECTS(PNAME, CONTENT, STA_DATE, END_DATE, STATE, CRE_DATE, TAGS)"
							+ " values(?, ?, ?, ?, 0, now(), ?)");
			stmt.setString(1, project.getTitle());
			stmt.setString(2, project.getContent());
			stmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
			stmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
			stmt.setString(5, project.getTags());
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				throw e;
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				throw e;
			}
		}
	}

	@Override
	public int delete(int no) throws Exception {
		Connection con = null;
		Statement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			return stmt.executeUpdate("delete from PROJECTS where PNO=" + no);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				throw e;
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				throw e;
			}
		}
	}

	@Override
	public Project selectOne(int no) throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(
					"select PNO, PNAME, CONTENT, STA_DATE, END_DATE, STATE, CRE_DATE, TAGS from PROJECTS where PNO="
							+ no);

			if (rs.next()) {
				return new Project().setNo(rs.getInt("PNO")).setTitle(rs.getString("PNAME"))
						.setContent(rs.getString("CONTENT")).setStartDate(rs.getDate("STA_DATE"))
						.setEndDate(rs.getDate("END_DATE")).setState(rs.getInt("STATE"))
						.setCreatedDate(rs.getDate("CRE_DATE")).setTags(rs.getString("TAGS"));
			} else {
				throw new Exception("해당 번호의 프로젝트를 찾을 수 없습니다.");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				throw e;
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				throw e;
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				throw e;
			}
		}
	}

	@Override
	public int update(Project project) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(
					"update PROJECTS set PNAME=?, CONTENT=?, STA_DATE=?, END_DATE=?, STATE=?, TAGS=?" + " where PNO=?");
			stmt.setString(1, project.getTitle());
			stmt.setString(2, project.getContent());
			stmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
			stmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
			stmt.setInt(5, project.getState());
			stmt.setString(6, project.getTags());
			stmt.setInt(7, project.getNo());
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				throw e;
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				throw e;
			}
		}
	}

}
