package br.com.kevin.model.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.kevin.model.bean.Especialidade;

@SuppressWarnings("serial")
public class EspecialidadeTableModel extends AbstractTableModel {

	private List<Especialidade> especialidades = new ArrayList<>();
	private String[] titulos = { "Especialidades" };

	public EspecialidadeTableModel() {
	}

	public EspecialidadeTableModel(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
		fireTableDataChanged();
	}

	public EspecialidadeTableModel(Especialidade[] esp) {
		for (int i = 0; i < esp.length; i++) {
			especialidades.add(esp[i]);
		}
		fireTableDataChanged();
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	public Especialidade[] getEspecialidadesArray() {
		return especialidades.toArray(new Especialidade[0]);
	}

	@Override
	public String getColumnName(int numColuna) {
		return titulos[numColuna];
	}

	@Override
	public int getRowCount() {
		return especialidades.size();
	}

	@Override
	public int getColumnCount() {
		return titulos.length;
	}

	@Override
	public Object getValueAt(int numLinha, int numColuna) {

		switch (numColuna) {
		case 0:
			return especialidades.get(numLinha);
		}

		return null;
	}

	public Especialidade getValueAt(int numLinha) {
		return especialidades.get(numLinha);
	}

	public void setValueAt(Object aValue, int numLinha, int numColuna) {

		switch (numColuna) {
		case 0:
			especialidades.get(numLinha).setNome((String) aValue);
		}
		fireTableCellUpdated(numLinha, numColuna);
	}

	public void addRow(Especialidade e) {
		especialidades.add(e);
		fireTableDataChanged();
	}

	public Especialidade removeRow(int numLinha) {
		Especialidade esp = getValueAt(numLinha);
		especialidades.remove(numLinha);
		fireTableRowsDeleted(numLinha, numLinha);
		return esp;
	}

	public void clearAll() {
		especialidades.clear();
		fireTableDataChanged();
	}

}
