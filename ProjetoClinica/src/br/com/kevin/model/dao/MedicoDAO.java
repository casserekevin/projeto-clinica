package br.com.kevin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.kevin.connection.ConnectionFactory;
import br.com.kevin.model.bean.Medico;

public class MedicoDAO {

	private Connection con = null;

	public MedicoDAO() {
		con = ConnectionFactory.getConnection();
	}

	public void insert(Medico medico) {

		String sql = "INSERT INTO medico (nome, crm, Especialidade_id) VALUES (?, ?, ?)";

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, medico.getNome());
			stmt.setInt(2, medico.getCrm());
			stmt.setInt(3, medico.getEspecialidade().getId());
			stmt.execute();

		} catch (SQLException ex) {
			System.err.println("Insert: MedicoDAO - Erro: " + ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}
