package jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*public class Conexao {
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/gerenciamento?useSSL=false&useTimezone=true&serverTimezone=UTC", "root", "dementes");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}*/
public class Conexao {
	private static Connection[] conexoes = new Connection[10];
	private static boolean conectou = false;
	private static int pos = 0;

	public Conexao() {
	}

	public static Connection getConnection() {
		if (pos == 10) {
			pos = 0;
		}
		if (!conectou) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				for (int i = 0; i < 10; i++) {
					conexoes[i] = DriverManager.getConnection(
							"jdbc:mysql://localhost/gerenciamento?useSSL=false&useTimezone=true&serverTimezone=UTC",
							"root", "dementes");
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			conectou = true;
		}
		return conexoes[pos++];
	}
}