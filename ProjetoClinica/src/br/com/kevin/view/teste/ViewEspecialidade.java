package br.com.kevin.view.teste;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import br.com.kevin.model.EspecialidadeTableModel;
import br.com.kevin.model.bean.Especialidade;

@SuppressWarnings("serial")
public class ViewEspecialidade extends JDialog {

	private JLabel lbl_espec;
	private JTextField txtf_espec;
	private JScrollPane scrollPane;
	private JTable tbl_espec;
	private EspecialidadeTableModel tableModel = new EspecialidadeTableModel();
	private JButton btn_salvar;
	private JButton btn_excluir;
	private JButton btn_alterar;

	public static void main(String[] args) {

		ViewEspecialidade dialog = new ViewEspecialidade();
		dialog.setVisible(true);
	}

	public ViewEspecialidade() {
		initLeF();
		initComponents();
	}

	private void initLeF() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ViewEspecialidade.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(ViewEspecialidade.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(ViewEspecialidade.class.getName()).log(Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			Logger.getLogger(ViewEspecialidade.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void initComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		setModal(true);

		getContentPane().setLayout(null);

		lbl_espec = new JLabel("Especialidade");
		lbl_espec.setBounds(59, 30, 83, 14);
		getContentPane().add(lbl_espec);

		txtf_espec = new JTextField();
		txtf_espec.setBounds(59, 55, 462, 30);
		getContentPane().add(txtf_espec);
		txtf_espec.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 96, 462, 191);
		getContentPane().add(scrollPane);

		tbl_espec = new JTable();
		tbl_espec.setModel(tableModel);
		scrollPane.setViewportView(tbl_espec);

		btn_salvar = new JButton("Salvar");
		btn_salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_salvarActionPerformed(e);
			}

		});
		btn_salvar.setBounds(432, 298, 89, 23);
		getContentPane().add(btn_salvar);

		btn_excluir = new JButton("Excluir");
		btn_excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_excluirActionPerformed(e);
			}

		});
		btn_excluir.setBounds(333, 298, 89, 23);
		getContentPane().add(btn_excluir);

		btn_alterar = new JButton("Alterar");
		btn_alterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_alterarActionPerformed(e);
			}

		});
		btn_alterar.setBounds(235, 298, 89, 23);
		getContentPane().add(btn_alterar);
	}

	private void btn_salvarActionPerformed(ActionEvent e) {
		Especialidade espec = new Especialidade(1, txtf_espec.getText());

		tableModel.addRow(espec);
	}

	private void btn_excluirActionPerformed(ActionEvent e) {

		if (tbl_espec.getSelectedRow() != -1) {
			tableModel.removeRow(tbl_espec.getSelectedRow());
		}
	}

	private void btn_alterarActionPerformed(ActionEvent e) {

		if (tbl_espec.getSelectedRow() != -1) {
			tableModel.setValueAt(txtf_espec.getText(), tbl_espec.getSelectedRow(), 0);
		}
	}
}
