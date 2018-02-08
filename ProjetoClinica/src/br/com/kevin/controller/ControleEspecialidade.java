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
			return esp;
		}
		return null;
	}

	public void deletar(Especialidade esp) {
		new EspecialidadeDAO().delete(esp);
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
