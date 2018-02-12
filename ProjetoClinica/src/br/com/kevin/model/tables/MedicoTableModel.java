package br.com.kevin.model.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.kevin.model.bean.Medico;

@SuppressWarnings("serial")
public class MedicoTableModel extends AbstractTableModel {

	private List<Medico> medicos = new ArrayList<>();
	private String[] titulos = { "Nome", "CRM", "Especialidade" };

	public MedicoTableModel() {
		medicos = new ArrayList<>();
	}

	public MedicoTableModel(List<Medico> medicos) {
		this.medicos = medicos;
		fireTableDataChanged();
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int numColuna) {
		return titulos[numColuna];
	}

	@Override
	public int getRowCount() {
		return medicos.size();
	}

	@Override
	public int getColumnCount() {
		return titulos.length;
	}

	@Override
	public Object getValueAt(int numLinha, int numColuna) {

		switch (numColuna) {
		case 0:
			return medicos.get(numLinha).getNome();

		case 1:
			return medicos.get(numLinha).getCrm();

		case 2:
			return medicos.get(numLinha).getEspecialidade();
		}
		return null;
	}

	public Medico getValueAt(int numLinha) {
		return medicos.get(numLinha);
	}

	@Override
	public void setValueAt(Object value, int numLinha, int numColuna) {

		switch (numColuna) {
		case 0:
			medicos.get(numLinha).setNome((String) value);

		case 1:
			medicos.get(numLinha).setCrm((Integer) value);

		case 2:
			medicos.get(numLinha).getEspecialidade().setNome((String) value);
		}
		fireTableCellUpdated(numLinha, numColuna);

	}

	public void clearAll() {
		medicos.clear();
		fireTableDataChanged();
	}

}
