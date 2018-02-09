package br.com.kevin.view;

import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.kevin.controller.ControleEspecialidade;
import br.com.kevin.model.bean.Especialidade;
import br.com.kevin.model.dao.EspecialidadeDAO;
import br.com.kevin.model.tables.EspecialidadeTableModel;

@SuppressWarnings("serial")
public class CadEspecialidadeView extends JDialog {
	private JLabel lbl_cadastroEspec;
	private JScrollPane scrollPane;
	private JTable tbl_espec;
	private JTextField txtf_espec;
	private JButton btn_cadastrar;
	private JButton btn_excluir;
	private JButton btn_fechar;

	private ControleEspecialidade controle = new ControleEspecialidade();

	private Especialidade[] vetor;

	public static void main(String[] args) {
		CadEspecialidadeView dialog = new CadEspecialidadeView();
		dialog.setVisible(true);
	}

	public CadEspecialidadeView() {
		initLeF();
		initComponents();
	}

	public CadEspecialidadeView(Especialidade[] array) {
		vetor = array;
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
			Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void initComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 465, 450);
		setLocationRelativeTo(null);
		setModal(true);

		getContentPane().setLayout(null);

		lbl_cadastroEspec = new JLabel("Cadastro de Especialidade");
		lbl_cadastroEspec.setBounds(60, 25, 330, 25);
		getContentPane().add(lbl_cadastroEspec);
		lbl_cadastroEspec.setFont(new Font("Tahoma", Font.BOLD, 25));

		scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 75, 350, 200);
		getContentPane().add(scrollPane);
		if (vetor == null) {
			tbl_espec = new JTable(new EspecialidadeTableModel(new EspecialidadeDAO().searchAll()));
		} else {
			tbl_espec = new JTable(new EspecialidadeTableModel(vetor));
		}
		DefaultTableCellRenderer Renderer = new DefaultTableCellRenderer();
		Renderer.setHorizontalAlignment(SwingConstants.LEFT);
		tbl_espec.setDefaultRenderer(Integer.class, Renderer);
		tbl_espec.getColumnModel().getColumn(0).setPreferredWidth(115);
		scrollPane.setViewportView(tbl_espec);

		txtf_espec = new JTextField();
		txtf_espec.setBounds(50, 286, 350, 30);
		getContentPane().add(txtf_espec);
		txtf_espec.setColumns(10);

		btn_cadastrar = new JButton("Cadastrar");
		btn_cadastrar.setBounds(310, 327, 90, 30);
		getContentPane().add(btn_cadastrar);
		btn_cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_cadastrarActionPerformed(e);

			}
		});
		getRootPane().setDefaultButton(btn_cadastrar);

		btn_excluir = new JButton("Excluir");
		btn_excluir.setBounds(210, 327, 90, 30);
		getContentPane().add(btn_excluir);
		btn_excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_excluirActionPerformed(e);
			}
		});

		btn_fechar = new JButton("Fechar");
		btn_fechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_fecharActionPerformed(e);
			}
		});
		btn_fechar.setBounds(50, 327, 90, 30);
		getContentPane().add(btn_fechar);
	}

	private void btn_fecharActionPerformed(ActionEvent e) {
		dispose();
	}

	private void btn_cadastrarActionPerformed(ActionEvent e) {
		Especialidade esp = new Especialidade(txtf_espec.getText());
		txtf_espec.setText("");
		esp = controle.cadastrar(esp);
		if (esp != null) {
			EspecialidadeTableModel model = (EspecialidadeTableModel) tbl_espec.getModel();
			model.addRow(esp);

		}
	}

	private void btn_excluirActionPerformed(ActionEvent e) {
		if (tbl_espec.getSelectedRow() != -1) {

			EspecialidadeTableModel model = (EspecialidadeTableModel) tbl_espec.getModel();
			Especialidade esp = model.getValueAt(tbl_espec.getSelectedRow());
			if (controle.deletar(esp)) {
				model.removeRow(tbl_espec.getSelectedRow());
			}
		}
	}

	public Especialidade[] getArrayTable() {
		EspecialidadeTableModel model = (EspecialidadeTableModel) tbl_espec.getModel();
		return model.getEspecialidadesArray();
	}
}
