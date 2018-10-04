package jdbcTest;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		Connection conexao = new Conexao().getConnection();
		System.out.println("Conexão aberta!");
		conexao.close();
	}

}
