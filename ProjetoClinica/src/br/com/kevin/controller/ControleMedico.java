package br.com.kevin.controller;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.kevin.model.bean.Especialidade;
import br.com.kevin.model.bean.Medico;
import br.com.kevin.model.dao.MedicoDAO;

public class ControleMedico {

	private String nome;
	private Especialidade esp;
	private int crm;
	
	public String getNome() {
		return nome;
	}
	
	public Especialidade getEspecialidade() {
		return esp;
	}
	
	public int getCrm() {
		return crm;
	}
	
	public void cadastrar(Medico m) {
			new MedicoDAO().insert(m);
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);			
		
	}
	public boolean validaValores(String nome, Especialidade esp, String crm) {
		boolean retorno = true;
		if(validaNome(nome) == false) {
			JOptionPane.showMessageDialog(null, "Nome inválido", "Erro", JOptionPane.ERROR_MESSAGE);
			retorno = false;
		}
		else if(validaEspecialidade(esp) == false) {
			JOptionPane.showMessageDialog(null, "Especialidade inválida", "Erro", JOptionPane.ERROR_MESSAGE);
			retorno = false;
		}
		else if(validaCRM(crm) == false){
			JOptionPane.showMessageDialog(null, "CRM inválido", "Erro", JOptionPane.ERROR_MESSAGE);
			retorno = false;
		}
		return retorno;
	}

	public List<Medico> searchByName(String s) {
		List<Medico> medicos = null;
		if (s.equalsIgnoreCase("")) {
			medicos = new MedicoDAO().searchAllByNameOrdered();
		} else {
			medicos = new MedicoDAO().searchByNameOrdered(s);
		}
		return medicos;
	}

	public List<Medico> searchByCrm(String s) {
		List<Medico> medicos = null;
		if (s.equalsIgnoreCase("")) {
			medicos = new MedicoDAO().searchAllByCrmOrdered();
		} else if (validaCRM(s) == false) {
		} else {
			medicos = new MedicoDAO().searchByCrmOrdered(s);
		}
		return medicos;
	}

	public List<Medico> searchByEsp(String s) {
		List<Medico> medicos = null;
		if (s.equalsIgnoreCase("")) {
			medicos = new MedicoDAO().searchAllByEspOrdered();
		} else {
			medicos = new MedicoDAO().searchByEspOrdered(s);
		}
		return medicos;
	}

	public boolean delete(Medico m) {
		boolean retorno = false;

		if (new MedicoDAO().delete(m)) {
			retorno = true;
			JOptionPane.showMessageDialog(null, "Deletado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		}
		return retorno;
	}
	
	private boolean validaNome(String nome) {
		if(nome.equalsIgnoreCase("")) {
			return false;
		}
		else {
			this.nome = nome;
			return true;
		}
	}
	
	private boolean validaEspecialidade(Especialidade esp) {
		if(esp == null) {
			return false;
		}
		else {
			this.esp = esp;
			return true;
		}
	}
	
	private boolean validaCRM(String crm) {
		if(crm.equalsIgnoreCase("")) {
			return false;
		}
		try {
			this.crm = Integer.parseInt(crm);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

}
