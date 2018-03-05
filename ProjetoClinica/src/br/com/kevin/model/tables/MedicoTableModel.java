package br.com.kevin.model.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.kevin.model.bean.Medico;

@SuppressWarnings("serial")
public class MedicoTableModel extends AbstractTableModel {

	private List<Medico> medicos = new ArrayList<>();
	private String[] titulos = {"Nome", "CRM", "Especialidade"};

	public MedicoTableModel() {
		this.medicos = new ArrayList<>();
	}

	public MedicoTableModel(List<Medico> medicos) {
		this.medicos = medicos;
		fireTableDataChanged();
	}

	public void clearAll() {
		this.medicos.clear();
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return this.titulos.length;
	}

	@Override
	public String getColumnName(int numColuna) {
		return this.titulos[numColuna];
	}

	public List<Medico> getMedicos() {
		return this.medicos;
	}

	@Override
	public int getRowCount() {
		return this.medicos.size();
	}

	public Medico getValueAt(int numLinha) {
		return this.medicos.get(numLinha);
	}

	@Override
	public Object getValueAt(int numLinha, int numColuna) {

		switch (numColuna) {
			case 0:
				return this.medicos.get(numLinha).getNome();

			case 1:
				return this.medicos.get(numLinha).getCrm();

			case 2:
				return this.medicos.get(numLinha).getEspecialidade();
		}
		return null;
	}

	public Medico removeRow(int numLinha) {
		Medico esp = getValueAt(numLinha);
		this.medicos.remove(numLinha);
		fireTableRowsDeleted(numLinha, numLinha);
		return esp;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
		fireTableDataChanged();
	}

	@Override
	public void setValueAt(Object value, int numLinha, int numColuna) {

		switch (numColuna) {
			case 0:
				this.medicos.get(numLinha).setNome(((Medico) value).getNome());

			case 1:
				this.medicos.get(numLinha).setCrm(((Medico) value).getCrm());

			case 2:
				this.medicos.get(numLinha).getEspecialidade().setNome(((Medico) value).getEspecialidade().getNome());
		}
		fireTableCellUpdated(numLinha, numColuna);

	}

}
