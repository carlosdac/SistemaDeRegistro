package jdbcTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import prova1.Animal;
import prova1.Bovino;
import prova1.Caprino;
import prova1.Suino;

public class AnimalDAO {
	private Connection conexao;

	public AnimalDAO() {
		new Conexao();
		conexao = Conexao.getConnection();
	}

	public void inserir(Animal a) {
		String sql = "insert into animal "
				+ "(idanimal,nome,diaNascimento,mesNascimento,anoNascimento,idFazendaAssociada,vacinado,abatido,vendido,ativo,valorCompra,valorVenda,diaVacinacao,mesVacinacao,anoVacinacao,morto,tipo,genero)"
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, a.getNumero());
			stmt.setString(2, a.getNome());
			stmt.setInt(3, a.getDataNascimento().getDia());
			stmt.setInt(4, a.getDataNascimento().getMes());
			stmt.setInt(5, a.getDataNascimento().getAno());
			stmt.setInt(6, a.getFazendaAssociada());
			stmt.setBoolean(7, a.isVacinado());
			stmt.setBoolean(8, a.isAbatido());
			stmt.setBoolean(9, a.isVendido());
			stmt.setBoolean(10, a.isAtivo());
			stmt.setDouble(11, a.getValorCompra());
			stmt.setDouble(12, a.getValorVenda());
			stmt.setInt(13, 0);
			stmt.setInt(14, 0);
			stmt.setInt(15, 0);
			stmt.setBoolean(16, a.isMorto());
			stmt.setInt(17, a.getTipo());
			stmt.setInt(18, a.getGenero());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Animal buscar(int id, int idFazenda) {
		new Conexao();
		Connection con = Conexao.getConnection();
		String sql = "select * from animal where idanimal = ? and idFazendaAssociada = ?";
		Animal a = null;
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setInt(2, idFazenda);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) { // usar coluna tipo pra definir o tipo de animal.
				if (rs.getInt("tipo") == 1) {
					a = new Bovino(rs.getInt("idanimal"), rs.getString("nome"), rs.getInt("diaNascimento"),
							rs.getInt("mesNascimento"), rs.getInt("anoNascimento"), rs.getInt("genero"),
							rs.getDouble("valorCompra"), rs.getDouble("valorVenda"));
				} else if (rs.getInt("tipo") == 2) {
					a = new Suino(rs.getInt("idanimal"), rs.getString("nome"), rs.getInt("diaNascimento"),
							rs.getInt("mesNascimento"), rs.getInt("anoNascimento"), rs.getInt("genero"),
							rs.getDouble("valorCompra"), rs.getDouble("valorVenda"));
				} else if (rs.getInt("tipo") == 3) {
					a = new Caprino(rs.getInt("idanimal"), rs.getString("nome"), rs.getInt("diaNascimento"),
							rs.getInt("mesNascimento"), rs.getInt("anoNascimento"), rs.getInt("genero"),
							rs.getDouble("valorCompra"), rs.getDouble("valorVenda"));
				}
				a.setFazendaAssociada(rs.getInt("idFazendaAssociada"));
				a.setVacinado(rs.getBoolean("vacinado"));
				a.setAbatido(rs.getBoolean("abatido"));
				a.setVendido(rs.getBoolean("vendido"));
				a.setAtivo(rs.getBoolean("ativo"));
				a.setDataVacinacao(rs.getInt("diaVacinacao"), rs.getInt("mesVacinacao"), rs.getInt("anoVacinacao"));
				a.setAbatido(rs.getBoolean("abatido"));
				return a;
			}
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public ArrayList<Animal> listaDeAnimais(int idFazenda) {
		new Conexao();
		Connection con = Conexao.getConnection();
		String sql = "select * from animal where idFazendaAssociada = ?";
		ArrayList<Animal> animais = new ArrayList<Animal>();
		Animal a = null;
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idFazenda);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { // usar coluna tipo pra definir o tipo de animal.
				if (rs.getInt("tipo") == 1) {
					a = new Bovino(rs.getInt("idanimal"), rs.getString("nome"), rs.getInt("diaNascimento"),
							rs.getInt("mesNascimento"), rs.getInt("anoNascimento"), rs.getInt("genero"),
							rs.getDouble("valorCompra"), rs.getDouble("valorVenda"));
				} else if (rs.getInt("tipo") == 2) {
					a = new Suino(rs.getInt("idanimal"), rs.getString("nome"), rs.getInt("diaNascimento"),
							rs.getInt("mesNascimento"), rs.getInt("anoNascimento"), rs.getInt("genero"),
							rs.getDouble("valorCompra"), rs.getDouble("valorVenda"));
				} else if (rs.getInt("tipo") == 3) {
					a = new Caprino(rs.getInt("idanimal"), rs.getString("nome"), rs.getInt("diaNascimento"),
							rs.getInt("mesNascimento"), rs.getInt("anoNascimento"), rs.getInt("genero"),
							rs.getDouble("valorCompra"), rs.getDouble("valorVenda"));
				}
				a.setFazendaAssociada(rs.getInt("idFazendaAssociada"));
				a.setVacinado(rs.getBoolean("vacinado"));
				a.setAbatido(rs.getBoolean("abatido"));
				a.setVendido(rs.getBoolean("vendido"));
				a.setAtivo(rs.getBoolean("ativo"));
				a.setDataVacinacao(rs.getInt("diaVacinacao"), rs.getInt("mesVacinacao"), rs.getInt("anoVacinacao"));
				a.setAbatido(rs.getBoolean("abatido"));
				a.setMorto(rs.getBoolean("morto"));
				animais.add(a);
			}
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return animais;
	}

	public void atualizarBooleans(String campo, boolean valor, int numAnimal, int idFazenda) {
		try {
			String sql = "update animal set " + campo + " = ? where idanimal = ? and idFazendaAssociada = ?";
			new Conexao();
			Connection con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setBoolean(1, valor);
			stmt.setInt(2, numAnimal);
			stmt.setInt(3, idFazenda);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void atualizarInteiros(String campo, int numero, int numAnimal) {
		try {
			String sql = "update animal set " + campo + " = ? where idanimal = ?";
			new Conexao();
			Connection con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, numero);
			stmt.setInt(2, numAnimal);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void limpar() {
		try {
			new Conexao();
			Connection con = Conexao.getConnection();
			Statement stmt = con.createStatement();
			String comando = "delete from animal";
			stmt.executeUpdate(comando);
			stmt.close();
			// conexao.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
