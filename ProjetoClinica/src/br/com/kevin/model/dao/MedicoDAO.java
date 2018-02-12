package br.com.kevin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.com.kevin.connection.ConnectionFactory;
import br.com.kevin.model.bean.Especialidade;
import br.com.kevin.model.bean.Medico;

public class MedicoDAO {

	private Connection con = null;

	public MedicoDAO() {
		con = ConnectionFactory.getConnection();
	}

	public boolean insert(Medico medico) {

		String sql = "INSERT INTO medico (nome, crm, Especialidade_id) VALUES (?, ?, ?)";

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, medico.getNome());
			stmt.setInt(2, medico.getCrm());
			stmt.setInt(3, medico.getEspecialidade().getId());
			stmt.execute();

			return true;
		} catch (SQLException ex) {
			System.err.println("Insert: MedicoDAO - Erro: " + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}

	public List<Medico> searchByNameOrdered(String nome) {

		String sql = "select m.id, m.nome, m.crm, e.id, e.nome as especialidade from medico as m join especialidade as e on m.Especialidade_id = e.id where m.nome like '%"
				+ nome + "%' order by m.nome";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Medico> medicos = new ArrayList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Especialidade esp = new Especialidade(rs.getInt(4), rs.getString(5));
				Medico m = new Medico(rs.getInt(1), rs.getString(2), rs.getInt(3), esp);
				medicos.add(m);
			}
		} catch (SQLException ex) {
			System.err.println("searchByNameOrdered: MedicoDAO - Erro: " + ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return medicos;
	}

	public List<Medico> searchAllByNameOrdered() {

		String sql = "select m.id, m.nome, m.crm, e.id, e.nome as especialidade from medico as m join especialidade as e on m.Especialidade_id = e.id order by m.nome";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Medico> medicos = new ArrayList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Especialidade esp = new Especialidade(rs.getInt(4), rs.getString(5));
				Medico m = new Medico(rs.getInt(1), rs.getString(2), rs.getInt(3), esp);
				medicos.add(m);
			}
		} catch (SQLException ex) {
			System.err.println("searchAllByNameOrdered: MedicoDAO - Erro: " + ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return medicos;
	}

	public List<Medico> searchByCrmOrdered(String s) {

		String sql = "select m.id, m.nome, m.crm, e.id, e.nome as especialidade from medico as m join especialidade as e on m.Especialidade_id = e.id where m.crm like '%"
				+ s + "%' order by m.crm";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Medico> medicos = new ArrayList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Especialidade esp = new Especialidade(rs.getInt(4), rs.getString(5));
				Medico m = new Medico(rs.getInt(1), rs.getString(2), rs.getInt(3), esp);
				medicos.add(m);
			}
		} catch (SQLException ex) {
			System.err.println("searchAllByCrmOrdered: MedicoDAO - Erro: " + ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return medicos;
	}

	public List<Medico> searchAllByCrmOrdered() {

		String sql = "select m.id, m.nome, m.crm, e.id, e.nome as especialidade from medico as m join especialidade as e on m.Especialidade_id = e.id order by m.crm";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Medico> medicos = new ArrayList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Especialidade esp = new Especialidade(rs.getInt(4), rs.getString(5));
				Medico m = new Medico(rs.getInt(1), rs.getString(2), rs.getInt(3), esp);
				medicos.add(m);
			}
		} catch (SQLException ex) {
			System.err.println("searchAllByCrmOrdered: MedicoDAO - Erro: " + ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return medicos;
	}

	public List<Medico> searchByEspOrdered(String s) {

		String sql = "select m.id, m.nome, m.crm, e.id, e.nome as especialidade from medico as m join especialidade as e on m.Especialidade_id = e.id where e.nome like '%"
				+ s + "%' order by e.nome";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Medico> medicos = new ArrayList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Especialidade esp = new Especialidade(rs.getInt(4), rs.getString(5));
				Medico m = new Medico(rs.getInt(1), rs.getString(2), rs.getInt(3), esp);
				medicos.add(m);
			}
		} catch (SQLException ex) {
			System.err.println("searchAllByEspOrdered: MedicoDAO - Erro: " + ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return medicos;
	}

	public List<Medico> searchAllByEspOrdered() {

		String sql = "select m.id, m.nome, m.crm, e.id, e.nome as especialidade from medico as m join especialidade as e on m.Especialidade_id = e.id order by e.nome";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Medico> medicos = new ArrayList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Especialidade esp = new Especialidade(rs.getInt(4), rs.getString(5));
				Medico m = new Medico(rs.getInt(1), rs.getString(2), rs.getInt(3), esp);
				medicos.add(m);
			}
		} catch (SQLException ex) {
			System.err.println("searchAllByEspOrdered: MedicoDAO - Erro: " + ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return medicos;
	}

	public boolean delete(Medico m) {// DELETE

		String sql = "DELETE FROM medico WHERE id = ?";

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, m.getId());
			stmt.executeUpdate();
			return true;
		} catch (MySQLIntegrityConstraintViolationException ex) {
			return false;
		} catch (SQLException ex) {
			System.err.println("Delete: EspecialidadeDAO - Erro: " + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}
