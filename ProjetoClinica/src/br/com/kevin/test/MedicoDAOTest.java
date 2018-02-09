package br.com.kevin.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import br.com.kevin.model.bean.Especialidade;
import br.com.kevin.model.bean.Medico;
import br.com.kevin.model.dao.MedicoDAO;

public class MedicoDAOTest {

	@Test
	public void insert() {
		Especialidade esp = new Especialidade(15);
		Medico m = new Medico("João", 2228, esp);
		MedicoDAO dao = new MedicoDAO();

		if (dao.insert(m)) {
			System.out.println("Salvo com sucesso!");
		} else {
			fail("Erro ao salvar");
		}

	}

}
