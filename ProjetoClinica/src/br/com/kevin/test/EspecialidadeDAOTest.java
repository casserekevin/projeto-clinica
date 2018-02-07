package br.com.kevin.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import br.com.kevin.model.bean.Especialidade;
import br.com.kevin.model.dao.EspecialidadeDAO;

public class EspecialidadeDAOTest {

	@Test
	public void insert() {
		Especialidade esp = new Especialidade("Cirurgia");
		EspecialidadeDAO dao = new EspecialidadeDAO();

		if (dao.insert(esp) != null) {
			System.out.println("Salvo com sucesso!");
		} else {
			fail("Erro ao salvar");
		}

	}

	@Test
	public void searchAll() {
		EspecialidadeDAO dao = new EspecialidadeDAO();

		for (Especialidade e : dao.searchAll()) {
			System.out.println("Nome: " + e.getNome());
		}
	}

	@Test
	public void update() {
		Especialidade esp = new Especialidade(1, "Cardiologia");
		EspecialidadeDAO dao = new EspecialidadeDAO();

		if (dao.update(esp)) {
			System.out.println("Atualizado com sucesso!");
		} else {
			fail("Erro ao atualizar");
		}
	}

	@Test
	public void delete() {
		Especialidade esp = new Especialidade(1);
		EspecialidadeDAO dao = new EspecialidadeDAO();

		if (dao.delete(esp)) {
			System.out.println("Deletado com sucesso!");
		} else {
			fail("Erro ao deletar");
		}
	}
}
