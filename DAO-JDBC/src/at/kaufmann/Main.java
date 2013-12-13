package at.kaufmann;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;

import at.kaufmann.model.Tables;
import at.kaufmann.model.tables.EEmpl;


public class Main {

	public static void main(String[] args) {

		String userName = "root";
		String password = "";
		String url = "jdbc:mysql://localhost:3306/firmendb";
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, userName, password);
			DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
			Result<Record> result = create.select().from(Tables.E_EMPL).fetch();
			System.out.println("E_EMPL:");
			for (Record r : result) {
				Long id = r.getValue(EEmpl.E_EMPL.E_EMPNO);
				String name = r.getValue(EEmpl.E_EMPL.E_ENAME);
				System.out.println("ID: " + id + "  " + "Name: " + name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}

}
