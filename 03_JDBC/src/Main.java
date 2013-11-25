import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

public class Main {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String host = "127.0.0.1";
		String dbName = "app";
		int port = 3306;
		String mySqlUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
		try {
			Connection connection = DriverManager.getConnection(mySqlUrl,
					"root", "");
			Statement statement = connection.createStatement();

			ResultSet selectSchueler = statement
					.executeQuery("SELECT * FROM schueler");
			System.out.println("Schüler:");
			printSchueler(selectSchueler);

			System.out.println("Insert:");
			statement = connection.createStatement();
			statement
					.executeUpdate("Insert into schueler values ('S5nach', 'S5vor', '1995-10-20', 'wien 5', '2chif');");
			ResultSet selectSchueler2 = statement
					.executeQuery("SELECT * FROM schueler where s_id = 5");
			printSchueler(selectSchueler2);

			System.out.println("Update:");
			statement
					.executeUpdate("UPDATE `app`.`schueler` SET `s_Ort`='NeuerOrt5' WHERE `s_id`='5'");
			selectSchueler2 = statement
					.executeQuery("SELECT * FROM schueler where s_id = 5");
			printSchueler(selectSchueler2);
			
			System.out.println("Update with preparedStatement:");
			String template =
					"UPDATE `app`.`schueler` SET `s_gebdat`=? WHERE `s_id`=?";
			PreparedStatement inserter =
					connection.prepareStatement(template);
			
			inserter.setString(1, "1990-10-10");
			inserter.setInt(2, 2);			
			inserter.executeUpdate();
			
			selectSchueler2 = statement
					.executeQuery("SELECT * FROM schueler where s_id = 5");
			printSchueler(selectSchueler2);

			System.out.println("Delete:");
			statement.executeUpdate("DELETE FROM `app`.`schueler` WHERE `s_id`='2'");
			selectSchueler = statement.executeQuery("SELECT * FROM schueler");
			printSchueler(selectSchueler);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void printSchueler(ResultSet set) {
		try {
			while (set.next()) {
				System.out.println("Name: " + set.getString("s_vorname")+ ' '+ set.getString("s_nachname")+ " Gebdat: "
						+ set.getDate("s_gebdat", GregorianCalendar.getInstance()).toString() + " Ort: " + set.getString("s_ort")
						+ " Klasse: " + set.getString("s_k_klasse"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
