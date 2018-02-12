package br.com.kevin.controller;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.kevin.model.bean.Medico;
import br.com.kevin.model.dao.MedicoDAO;

public class ControleMedico {

	public void cadastrar(Medico m) {
		new MedicoDAO().insert(m);
		JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
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
		} else if (parseToInt(s) == null) {
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

	public Integer parseToInt(String s) {
		Integer value = null;
		try {
			value = Integer.parseInt(s);

			return value;
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "CRM inválido", "Erro", JOptionPane.ERROR_MESSAGE);
			return value;
		}
	}

}
