package br.com.kevin.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EtchedBorder;

import br.com.kevin.controller.ControleMedico;
import br.com.kevin.model.bean.Especialidade;
import br.com.kevin.model.bean.Medico;
import br.com.kevin.model.dao.EspecialidadeDAO;
import br.com.kevin.model.tables.MedicoTableModel;

@SuppressWarnings("serial")
public class CadMedicoView extends JDialog {

	private JPanel panel;
	private JButton btn_novo;
	private JButton btn_editar;
	private JButton btn_excluir;
	private JButton btn_cadastrar;
	private JButton btn_cancelar;
	private JLabel lbl_nome;
	private JTextField txtf_nome;
	private JLabel lbl_especialidade;
	private JComboBox<Especialidade> cb_especialidade;
	private JLabel lbl_crm;
	private JTextField txtf_crm;
	private JTextField txtf_pesquisar;
	private JButton btn_pesquisar;
	private JLabel lbl_cadMedico;
	private JButton btn_3p;
	private JScrollPane scrollPane;
	private JTable tbl_medico;
	private JCheckBox ckb_porNome;
	private JCheckBox ckb_porEspecialidade;
	private JCheckBox ckb_porCrm;

	private ControleMedico controle = new ControleMedico();

	private Especialidade[] vetor;

	public static void main(String[] args) {
		CadMedicoView dialog = new CadMedicoView();
		dialog.setVisible(true);
	}

	public CadMedicoView() {
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 836, 500);
		setLocationRelativeTo(null);
		setModal(true);

		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 39, 800, 411);
		panel.setLayout(null);
		getContentPane().add(panel);

		btn_novo = new JButton("Novo");
		btn_novo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_novoActionPerformed(e);
			}

		});
		btn_novo.setBounds(10, 26, 89, 23);
		panel.add(btn_novo);

		btn_editar = new JButton("Editar");
		btn_editar.setBounds(10, 60, 89, 23);
		btn_editar.setEnabled(false);
		panel.add(btn_editar);

		btn_excluir = new JButton("Excluir");
		btn_excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_excluirActionPerformed(e);
			}

		});
		btn_excluir.setBounds(10, 94, 89, 23);
		btn_excluir.setEnabled(false);
		panel.add(btn_excluir);

		btn_cadastrar = new JButton("Cadastrar");
		btn_cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_cadastrarActionPerformed(e);
			}

		});
		btn_cadastrar.setBounds(552, 377, 89, 23);
		btn_cadastrar.setEnabled(false);
		panel.add(btn_cadastrar);

		btn_cancelar = new JButton("Cancelar");
		btn_cancelar.setBounds(453, 377, 89, 23);
		btn_cancelar.setEnabled(false);
		panel.add(btn_cancelar);

		lbl_nome = new JLabel("Nome:");
		lbl_nome.setBounds(125, 34, 40, 14);
		panel.add(lbl_nome);

		txtf_nome = new JTextField();
		txtf_nome.setBounds(161, 26, 363, 30);
		txtf_nome.setEnabled(false);
		txtf_nome.setColumns(10);
		panel.add(txtf_nome);

		lbl_especialidade = new JLabel("Especialidade:");
		lbl_especialidade.setBounds(534, 34, 86, 14);
		panel.add(lbl_especialidade);

		cb_especialidade = new JComboBox<>();
		vetor = new EspecialidadeDAO().searchAllArray();
		cb_especialidade.setModel(new DefaultComboBoxModel(vetor));
		cb_especialidade.setBounds(616, 31, 130, 20);
		cb_especialidade.setEnabled(false);
		panel.add(cb_especialidade);

		lbl_crm = new JLabel("CRM:");
		lbl_crm.setBounds(132, 68, 40, 14);
		panel.add(lbl_crm);

		txtf_crm = new JTextField();
		txtf_crm.setBounds(161, 60, 74, 30);
		txtf_crm.setEnabled(false);
		txtf_crm.setColumns(10);
		panel.add(txtf_crm);

		txtf_pesquisar = new JTextField();
		txtf_pesquisar.setColumns(10);
		txtf_pesquisar.setBounds(161, 144, 381, 30);
		txtf_pesquisar.setEnabled(false);
		panel.add(txtf_pesquisar);

		btn_pesquisar = new JButton("Pesquisar");
		btn_pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_pesquisarActionPerformed(e);
			}

		});
		btn_pesquisar.setBounds(552, 148, 89, 23);
		btn_pesquisar.setEnabled(false);
		panel.add(btn_pesquisar);

		btn_3p = new JButton("...");
		btn_3p.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_3pActionPerformed(e);
			}
		});
		btn_3p.setBounds(756, 31, 30, 20);
		panel.add(btn_3p);

		ckb_porNome = new JCheckBox("Por Nome");
		ckb_porNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ckb_porNomeActionPerformed(e);
			}

		});
		ckb_porNome.setBounds(647, 185, 97, 23);
		panel.add(ckb_porNome);

		ckb_porEspecialidade = new JCheckBox("Por Especialidade");
		ckb_porEspecialidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ckb_porEspecialidadeActionPerformed(e);
			}

		});
		ckb_porEspecialidade.setBounds(647, 237, 139, 23);
		panel.add(ckb_porEspecialidade);

		ckb_porCrm = new JCheckBox("Por CRM");
		ckb_porCrm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ckb_porCrmActionPerformed(e);
			}

		});
		ckb_porCrm.setBounds(647, 211, 97, 23);
		panel.add(ckb_porCrm);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(161, 185, 480, 181);
		panel.add(scrollPane);

		tbl_medico = new JTable(new MedicoTableModel());
		tbl_medico.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tbl_medicoKeyReleased(e);
			}
		});
		tbl_medico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tbl_medicoMouseClicked(e);
			}

		});

		scrollPane.setViewportView(tbl_medico);

		lbl_cadMedico = new JLabel("Cadastro de M\u00E9dico");
		lbl_cadMedico.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_cadMedico.setBounds(338, 11, 155, 22);
		getContentPane().add(lbl_cadMedico);
	}

	private void btn_novoActionPerformed(ActionEvent e) {
		btn_cadastrar.setEnabled(true);
		txtf_nome.setEnabled(true);
		cb_especialidade.setEnabled(true);
		txtf_crm.setEnabled(true);
		txtf_pesquisar.setEnabled(false);
		btn_pesquisar.setEnabled(false);
		ckb_porNome.setSelected(false);
		ckb_porCrm.setSelected(false);
		ckb_porEspecialidade.setSelected(false);
		ckb_porNome.setEnabled(true);
		ckb_porCrm.setEnabled(true);
		ckb_porEspecialidade.setEnabled(true);
		txtf_nome.setText("");
		txtf_crm.setText("");
		if(cb_especialidade.getModel().getSize() != 0) {
			cb_especialidade.setSelectedIndex(0);			
		}
		MedicoTableModel model = (MedicoTableModel) tbl_medico.getModel();
		model.clearAll();
		btn_editar.setEnabled(false);
		btn_excluir.setEnabled(false);
	}

	private void btn_cadastrarActionPerformed(ActionEvent e) {
		if (controle.validaValores(txtf_nome.getText(), (Especialidade) cb_especialidade.getSelectedItem(), txtf_crm.getText())) {
			Medico m = new Medico(controle.getNome(), controle.getCrm(), controle.getEspecialidade());
			controle.cadastrar(m);
			txtf_crm.setText("");
			txtf_nome.setText("");
			cb_especialidade.setSelectedIndex(0);			
			txtf_nome.setEnabled(false);
			txtf_crm.setEnabled(false);
			cb_especialidade.setEnabled(false);
			btn_cadastrar.setEnabled(false);
		}
	}

	private void btn_excluirActionPerformed(ActionEvent e) {
		if (tbl_medico.getSelectedRow() != -1) {
			MedicoTableModel model = (MedicoTableModel) tbl_medico.getModel();
			Medico m = model.removeRow(tbl_medico.getSelectedRow());
			controle.delete(m);
			txtf_nome.setText("");
			txtf_crm.setText("");
			cb_especialidade.setSelectedIndex(0);
			txtf_nome.setEnabled(false);
			cb_especialidade.setEnabled(false);
			txtf_crm.setEnabled(false);
			btn_editar.setEnabled(false);
			btn_excluir.setEnabled(false);
		}
	}

	private void tbl_medicoMouseClicked(MouseEvent e) {

		MedicoTableModel model = (MedicoTableModel) tbl_medico.getModel();
		Medico m = model.getValueAt(tbl_medico.getSelectedRow());

		txtf_nome.setText(m.getNome());
		txtf_crm.setText(String.valueOf(m.getCrm()));
		cb_especialidade.setEditable(true);
		cb_especialidade.setSelectedItem(m.getEspecialidade());
		cb_especialidade.setEditable(false);
		txtf_nome.setEnabled(true);
		txtf_crm.setEnabled(true);
		cb_especialidade.setEnabled(true);
		btn_editar.setEnabled(true);
		btn_excluir.setEnabled(true);

	}

	private void tbl_medicoKeyReleased(KeyEvent e) {

		if ((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) && btn_cadastrar.isEnabled() == false) {
			MedicoTableModel model = (MedicoTableModel) tbl_medico.getModel();
			Medico m = model.getValueAt(tbl_medico.getSelectedRow());

			txtf_nome.setText(m.getNome());
			txtf_crm.setText(String.valueOf(m.getCrm()));
			cb_especialidade.setEditable(true);
			cb_especialidade.setSelectedItem(m.getEspecialidade());
			cb_especialidade.setEditable(false);
			txtf_nome.setEnabled(true);
			txtf_crm.setEnabled(true);
			cb_especialidade.setEnabled(true);
			btn_editar.setEnabled(true);
			btn_excluir.setEnabled(true);

		}
	}

	private void btn_pesquisarActionPerformed(ActionEvent e) {
		if (ckb_porNome.isSelected()) {
			MedicoTableModel model = (MedicoTableModel) tbl_medico.getModel();
			model.setMedicos(controle.searchByName(txtf_pesquisar.getText()));
		} else if (ckb_porCrm.isSelected()) {
			List<Medico> medicos = controle.searchByCrm(txtf_pesquisar.getText());
			if (medicos != null) {
				MedicoTableModel model = (MedicoTableModel) tbl_medico.getModel();
				model.setMedicos(medicos);
			}
		} else if (ckb_porEspecialidade.isSelected()) {
			MedicoTableModel model = (MedicoTableModel) tbl_medico.getModel();
			model.setMedicos(controle.searchByEsp(txtf_pesquisar.getText()));
		}
		txtf_pesquisar.setText("");
		txtf_nome.setText("");
		txtf_crm.setText("");
		if(cb_especialidade.getModel().getSize() != 0) {
			cb_especialidade.setSelectedIndex(0);			
		}
		txtf_nome.setEnabled(false);
		txtf_crm.setEnabled(false);
		cb_especialidade.setEnabled(false);
		btn_editar.setEnabled(false);
		btn_excluir.setEnabled(false);
		btn_cadastrar.setEnabled(false);
	}

	private void btn_3pActionPerformed(ActionEvent evt) {
		CadEspecialidadeView janela = new CadEspecialidadeView(vetor);
		janela.setVisible(true);

		vetor = janela.getArrayTable();
		cb_especialidade.setModel(new DefaultComboBoxModel<>(janela.getArrayTable()));
		if (tbl_medico.getSelectedRow() != -1) {
			MedicoTableModel model = (MedicoTableModel) tbl_medico.getModel();
			Medico m = model.getValueAt(tbl_medico.getSelectedRow());
			cb_especialidade.setEditable(true);
			cb_especialidade.setSelectedItem(m.getEspecialidade());
			cb_especialidade.setEditable(false);
		}
	}

	private void ckb_porNomeActionPerformed(ActionEvent e) {
		if (ckb_porNome.isSelected()) {
			ckb_porEspecialidade.setEnabled(false);
			ckb_porCrm.setEnabled(false);
			txtf_pesquisar.setEnabled(true);
			btn_pesquisar.setEnabled(true);
		} else {
			ckb_porEspecialidade.setEnabled(true);
			ckb_porCrm.setEnabled(true);
			txtf_pesquisar.setEnabled(false);
			btn_pesquisar.setEnabled(false);
		}
	}

	private void ckb_porEspecialidadeActionPerformed(ActionEvent e) {
		if (ckb_porEspecialidade.isSelected()) {
			ckb_porNome.setEnabled(false);
			ckb_porCrm.setEnabled(false);
			txtf_pesquisar.setEnabled(true);
			btn_pesquisar.setEnabled(true);
		} else {
			ckb_porNome.setEnabled(true);
			ckb_porCrm.setEnabled(true);
			txtf_pesquisar.setEnabled(false);
			btn_pesquisar.setEnabled(false);
		}
	}

	private void ckb_porCrmActionPerformed(ActionEvent e) {
		if (ckb_porCrm.isSelected()) {
			ckb_porNome.setEnabled(false);
			ckb_porEspecialidade.setEnabled(false);
			txtf_pesquisar.setEnabled(true);
			btn_pesquisar.setEnabled(true);
		} else {
			ckb_porNome.setEnabled(true);
			ckb_porEspecialidade.setEnabled(true);
			txtf_pesquisar.setEnabled(false);
			btn_pesquisar.setEnabled(false);
		}

	}
}
