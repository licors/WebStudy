package spms.dao;

import java.sql.Connection;
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
			} catch (Exception e) {}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {}
		}
	}

}
