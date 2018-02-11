package br.com.kevin.controller;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.kevin.model.bean.Especialidade;
import br.com.kevin.model.dao.EspecialidadeDAO;

public class ControleEspecialidade {

	public Especialidade cadastrar(Especialidade esp) {
		if (esp.getNome().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Nome inválido", "Erro", JOptionPane.ERROR_MESSAGE);
		} else if (isExistingInTable(esp.getNome())) {
			JOptionPane.showMessageDialog(null, "Evento ja existente", "Erro", JOptionPane.ERROR_MESSAGE);
		} else {
			esp = new EspecialidadeDAO().insert(esp);
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			return esp;
		}
		return null;
	}

	public boolean deletar(Especialidade esp) {
		if (new EspecialidadeDAO().delete(esp)) {
			JOptionPane.showMessageDialog(null, "Excluído com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Especialidade Usada", "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

	}

	private boolean isExistingInTable(String s) {
		boolean value = false;
		List<Especialidade> especialidades = new EspecialidadeDAO().searchAll();
		for (int i = 0; i < especialidades.size(); i++) {
			if (especialidades.get(i).getNome().equalsIgnoreCase(s) == true) {
				value = true;
			}
		}
		return value;

	}

}
