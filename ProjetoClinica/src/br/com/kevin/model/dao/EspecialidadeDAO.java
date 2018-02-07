package br.com.kevin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.kevin.connection.ConnectionFactory;
import br.com.kevin.model.bean.Especialidade;

public class EspecialidadeDAO {

	private Connection con = null;
	private int tamanho = 0;

	public EspecialidadeDAO() {
		con = ConnectionFactory.getConnection();
		count();
	}

	public Especialidade insert(Especialidade esp) {// CREATE

		String sql = "INSERT INTO especialidade (nome) VALUES (?)";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, esp.getNome());
			stmt.execute();
			rs = stmt.getGeneratedKeys();

			while (rs.next()) {
				esp.setId(rs.getInt(1));
			}

		} catch (SQLException ex) {
			System.err.println("Insert: EpecialidadeDAO - Erro: " + ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return esp;
	}

	public List<Especialidade> searchAll() {// READ

		String sql = "SELECT * FROM especialidade";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Especialidade> especialidades = new ArrayList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Especialidade esp = new Especialidade(rs.getInt(1), rs.getString(2));
				especialidades.add(esp);

			}
		} catch (SQLException ex) {
			System.err.println("searchAll: EspecialidadeDAO - Erro: " + ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return especialidades;
	}

	public Especialidade[] searchAllArray() {

		String sql = "SELECT * FROM especialidade";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		Especialidade[] esp = new Especialidade[tamanho];

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				esp[i++] = new Especialidade(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException ex) {
			System.err.println("searchAllArray: EspecialidadeDAO - Erro: " + ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return esp;
	}

	public boolean update(Especialidade esp) {// UPDATE

		String sql = "UPDATE especialidade SET nome = ? WHERE id = ?";

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, esp.getNome());
			stmt.setInt(2, esp.getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException ex) {
			System.err.println("Update: EspecialidadeDAO - Erro: " + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}

	public boolean delete(Especialidade esp) {// DELETE

		String sql = "DELETE FROM especialidade WHERE id = ?";

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, esp.getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException ex) {
			System.err.println("Delete: EspecialidadeDAO - Erro: " + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}

	public void count() {
		String sql = "SELECT COUNT(nome) AS qtd FROM especialidade";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				tamanho = rs.getInt(1);
			}
		} catch (SQLException ex) {
			System.err.println("Count: EspecialidadeDAO - Erro: " + ex);
		}
	}

}
