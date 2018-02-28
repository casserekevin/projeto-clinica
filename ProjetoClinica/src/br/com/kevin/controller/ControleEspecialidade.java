package br.com.kevin.controller;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.kevin.model.bean.Especialidade;
import br.com.kevin.model.dao.EspecialidadeDAO;

public class ControleEspecialidade {
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void cadastrar(Especialidade esp) {
		esp = new EspecialidadeDAO().insert(esp);
		JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public boolean validarValores(String nome) {
		boolean retorno = true;
		if (validarNome(nome) == false) {
			retorno = false;
		}
		return retorno;
		
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
	
	private boolean validarNome(String nome) {
		if(nome.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Nome inválido", "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(isExistingInTable(nome)) {
			JOptionPane.showMessageDialog(null, "Especialidade ja existente", "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			this.nome = nome;
			return true;
		}
	}

}
