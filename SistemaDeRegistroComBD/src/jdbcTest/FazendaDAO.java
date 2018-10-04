package jdbcTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import prova1.Fazenda;

public class FazendaDAO {
	private Connection conexao;

	public FazendaDAO() {
		new Conexao();
		conexao = Conexao.getConnection();
	}

	public void inserir(Fazenda f) {
		String sql = "insert into fazenda " + "(idfazenda,nome)" + " values(?,?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, f.getId());
			stmt.setString(2, f.getNome());

			stmt.execute();
			stmt.close();
			// conexao.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Fazenda buscar(int id) {
		Fazenda f = null;
		new Conexao();
		Connection con = Conexao.getConnection();
		String sql = "select * from fazenda where idfazenda = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				f = new Fazenda(rs.getInt("idfazenda"), rs.getString("nome"));
				return f;
			}
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public void limpar() {
		try {
			new Conexao();
			Connection con = Conexao.getConnection();
			Statement stmt = con.createStatement();
			String comando = "delete from fazenda";
			stmt.executeUpdate(comando);
			stmt.close();
			// conexao.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
