package br.com.kevin.controller;

import br.com.kevin.model.bean.Medico;
import br.com.kevin.model.dao.MedicoDAO;

public class ControleMedico {

	public void cadastrar(Medico m) {
		new MedicoDAO().insert(m);
	}

}
