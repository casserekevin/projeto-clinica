package br.com.kevin.controller;

import javax.swing.JOptionPane;

import br.com.kevin.model.bean.Medico;
import br.com.kevin.model.dao.MedicoDAO;

public class ControleMedico {

	public void cadastrar(Medico m) {
		new MedicoDAO().insert(m);
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
