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
import javax.swing.JOptionPane;
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

	private JButton btn_3p;

	private JButton btn_cadastrar;
	private JButton btn_cancelar;
	private JButton btn_editar;
	private JButton btn_excluir;
	private JButton btn_novo;
	private JButton btn_pesquisar;
	private JComboBox<Especialidade> cb_especialidade;
	private JCheckBox ckb_porCrm;
	private JCheckBox ckb_porEspecialidade;
	private JCheckBox ckb_porNome;
	private ControleMedico controle = new ControleMedico();
	private JLabel lbl_cadMedico;
	private JLabel lbl_crm;
	private JLabel lbl_especialidade;
	private JLabel lbl_nome;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable tbl_medico;
	private JTextField txtf_crm;
	private JTextField txtf_nome;

	private JTextField txtf_pesquisar;

	private Especialidade[] vetor;

	public CadMedicoView() {
		initLeF();
		initComponents();
	}

	public static void main(String[] args) {
		CadMedicoView dialog = new CadMedicoView();
		dialog.setVisible(true);
	}

	private void btn_3pActionPerformed(ActionEvent evt) {
		CadEspecialidadeView janela = new CadEspecialidadeView(this.vetor);
		janela.setVisible(true);

		this.vetor = janela.getArrayTable();
		this.cb_especialidade.setModel(new DefaultComboBoxModel<>(janela.getArrayTable()));
		if (this.tbl_medico.getSelectedRow() != -1) {
			MedicoTableModel model = (MedicoTableModel) this.tbl_medico.getModel();
			Medico m = model.getValueAt(this.tbl_medico.getSelectedRow());
			this.cb_especialidade.setEditable(true);
			this.cb_especialidade.setSelectedItem(m.getEspecialidade());
			this.cb_especialidade.setEditable(false);
		}
	}

	private void btn_cadastrarActionPerformed(ActionEvent e) {
		if (this.controle.validaValores(this.txtf_nome.getText(), (Especialidade) this.cb_especialidade.getSelectedItem(),
				this.txtf_crm.getText())) {
			Medico m = new Medico(this.controle.getNome(), this.controle.getCrm(), this.controle.getEspecialidade());
			this.controle.cadastrar(m);
			this.txtf_crm.setText("");
			this.txtf_nome.setText("");
			this.cb_especialidade.setSelectedIndex(0);
			this.txtf_nome.setEnabled(false);
			this.txtf_crm.setEnabled(false);
			this.cb_especialidade.setEnabled(false);
			this.btn_cadastrar.setEnabled(false);
			this.btn_cancelar.setEnabled(false);
		}
	}

	private void btn_cancelarActionPerformed(ActionEvent e) {
		if (JOptionPane.showConfirmDialog(this, "Deseja continuar?", "Pergunta", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE) == 0) {
			this.txtf_nome.setText("");
			this.txtf_crm.setText("");
			this.txtf_nome.setEnabled(false);
			this.txtf_crm.setEnabled(false);
			if (this.cb_especialidade.getModel().getSize() != 0) {
				this.cb_especialidade.setSelectedIndex(0);
			}
			this.cb_especialidade.setEnabled(false);
			this.btn_cadastrar.setEnabled(false);
			this.btn_cancelar.setEnabled(false);
			this.ckb_porNome.setSelected(false);
			this.ckb_porCrm.setSelected(false);
			this.ckb_porEspecialidade.setSelected(false);
			this.ckb_porNome.setEnabled(true);
			this.ckb_porCrm.setEnabled(true);
			this.ckb_porEspecialidade.setEnabled(true);
			this.txtf_pesquisar.setText("");
			this.txtf_pesquisar.setEnabled(false);
			this.btn_pesquisar.setEnabled(false);
			MedicoTableModel model = (MedicoTableModel) this.tbl_medico.getModel();
			model.clearAll();
			this.btn_editar.setEnabled(false);
			this.btn_excluir.setEnabled(false);
		}
		else {
			dispose();
		}
	}

	private void btn_editarActionPerformed(ActionEvent e) {
		if (this.tbl_medico.getSelectedRow() != -1) {
			if (this.controle.validaValores(this.txtf_nome.getText(), (Especialidade) this.cb_especialidade.getSelectedItem(),
					this.txtf_crm.getText())) {
				MedicoTableModel model = (MedicoTableModel) this.tbl_medico.getModel();
				Medico m = model.getValueAt(this.tbl_medico.getSelectedRow());
				m.setNome(this.controle.getNome());
				m.setEspecialidade(this.controle.getEspecialidade());
				m.setCrm(this.controle.getCrm());
				this.controle.update(m);
				model.setValueAt(m, this.tbl_medico.getSelectedRow(), 0);
				model.setValueAt(m, this.tbl_medico.getSelectedRow(), 1);
				model.setValueAt(m, this.tbl_medico.getSelectedRow(), 2);
			}
		}

	}

	private void btn_excluirActionPerformed(ActionEvent e) {
		if (this.tbl_medico.getSelectedRow() != -1) {
			MedicoTableModel model = (MedicoTableModel) this.tbl_medico.getModel();
			Medico m = model.removeRow(this.tbl_medico.getSelectedRow());
			this.controle.delete(m);
			this.txtf_nome.setText("");
			this.txtf_crm.setText("");
			this.cb_especialidade.setSelectedIndex(0);
			this.txtf_nome.setEnabled(false);
			this.cb_especialidade.setEnabled(false);
			this.txtf_crm.setEnabled(false);
			this.btn_editar.setEnabled(false);
			this.btn_excluir.setEnabled(false);
		}
	}

	private void btn_novoActionPerformed(ActionEvent e) {
		this.btn_cadastrar.setEnabled(true);
		this.btn_cancelar.setEnabled(true);
		this.txtf_nome.setEnabled(true);
		this.cb_especialidade.setEnabled(true);
		this.txtf_crm.setEnabled(true);
		this.txtf_pesquisar.setEnabled(false);
		this.btn_pesquisar.setEnabled(false);
		this.ckb_porNome.setSelected(false);
		this.ckb_porCrm.setSelected(false);
		this.ckb_porEspecialidade.setSelected(false);
		this.ckb_porNome.setEnabled(true);
		this.ckb_porCrm.setEnabled(true);
		this.ckb_porEspecialidade.setEnabled(true);
		this.txtf_nome.setText("");
		this.txtf_crm.setText("");
		if (this.cb_especialidade.getModel().getSize() != 0) {
			this.cb_especialidade.setSelectedIndex(0);
		}
		MedicoTableModel model = (MedicoTableModel) this.tbl_medico.getModel();
		model.clearAll();
		this.btn_editar.setEnabled(false);
		this.btn_excluir.setEnabled(false);
	}

	private void btn_pesquisarActionPerformed(ActionEvent e) {
		if (this.ckb_porNome.isSelected()) {
			MedicoTableModel model = (MedicoTableModel) this.tbl_medico.getModel();
			model.setMedicos(this.controle.searchByName(this.txtf_pesquisar.getText()));
		}
		else if (this.ckb_porCrm.isSelected()) {
			List<Medico> medicos = this.controle.searchByCrm(this.txtf_pesquisar.getText());
			if (medicos != null) {
				MedicoTableModel model = (MedicoTableModel) this.tbl_medico.getModel();
				model.setMedicos(medicos);
			}
		}
		else if (this.ckb_porEspecialidade.isSelected()) {
			MedicoTableModel model = (MedicoTableModel) this.tbl_medico.getModel();
			model.setMedicos(this.controle.searchByEsp(this.txtf_pesquisar.getText()));
		}
		this.txtf_pesquisar.setText("");
		this.txtf_nome.setText("");
		this.txtf_crm.setText("");
		if (this.cb_especialidade.getModel().getSize() != 0) {
			this.cb_especialidade.setSelectedIndex(0);
		}
		this.txtf_nome.setEnabled(false);
		this.txtf_crm.setEnabled(false);
		this.cb_especialidade.setEnabled(false);
		this.btn_editar.setEnabled(false);
		this.btn_excluir.setEnabled(false);
		this.btn_cadastrar.setEnabled(false);
	}

	private void ckb_porCrmActionPerformed(ActionEvent e) {
		if (this.ckb_porCrm.isSelected()) {
			this.ckb_porNome.setEnabled(false);
			this.ckb_porEspecialidade.setEnabled(false);
			this.txtf_pesquisar.setEnabled(true);
			this.btn_pesquisar.setEnabled(true);
			this.btn_cancelar.setEnabled(true);
		}
		else {
			this.ckb_porNome.setEnabled(true);
			this.ckb_porEspecialidade.setEnabled(true);
			this.txtf_pesquisar.setEnabled(false);
			this.btn_pesquisar.setEnabled(false);
			MedicoTableModel model = (MedicoTableModel) this.tbl_medico.getModel();
			if (model.getRowCount() == 0) {
				this.btn_cancelar.setEnabled(false);
			}
		}

	}

	private void ckb_porEspecialidadeActionPerformed(ActionEvent e) {
		if (this.ckb_porEspecialidade.isSelected()) {
			this.ckb_porNome.setEnabled(false);
			this.ckb_porCrm.setEnabled(false);
			this.txtf_pesquisar.setEnabled(true);
			this.btn_pesquisar.setEnabled(true);
			this.btn_cancelar.setEnabled(true);
		}
		else {
			this.ckb_porNome.setEnabled(true);
			this.ckb_porCrm.setEnabled(true);
			this.txtf_pesquisar.setEnabled(false);
			this.btn_pesquisar.setEnabled(false);
			MedicoTableModel model = (MedicoTableModel) this.tbl_medico.getModel();
			if (model.getRowCount() == 0) {
				this.btn_cancelar.setEnabled(false);
			}
		}
	}

	private void ckb_porNomeActionPerformed(ActionEvent e) {
		if (this.ckb_porNome.isSelected()) {
			this.ckb_porEspecialidade.setEnabled(false);
			this.ckb_porCrm.setEnabled(false);
			this.txtf_pesquisar.setEnabled(true);
			this.btn_pesquisar.setEnabled(true);
			this.btn_cancelar.setEnabled(true);
		}
		else {
			this.ckb_porEspecialidade.setEnabled(true);
			this.ckb_porCrm.setEnabled(true);
			this.txtf_pesquisar.setEnabled(false);
			this.btn_pesquisar.setEnabled(false);
			MedicoTableModel model = (MedicoTableModel) this.tbl_medico.getModel();
			if (model.getRowCount() == 0) {
				this.btn_cancelar.setEnabled(false);
			}
		}
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private void initComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 836, 500);
		setLocationRelativeTo(null);
		setModal(true);

		getContentPane().setLayout(null);

		this.panel = new JPanel();
		this.panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panel.setBounds(10, 39, 800, 411);
		this.panel.setLayout(null);
		getContentPane().add(this.panel);

		this.btn_novo = new JButton("Novo");
		this.btn_novo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btn_novoActionPerformed(e);
			}

		});
		this.btn_novo.setBounds(10, 26, 89, 23);
		this.panel.add(this.btn_novo);

		this.btn_editar = new JButton("Editar");
		this.btn_editar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btn_editarActionPerformed(e);
			}

		});
		this.btn_editar.setBounds(10, 60, 89, 23);
		this.btn_editar.setEnabled(false);
		this.panel.add(this.btn_editar);

		this.btn_excluir = new JButton("Excluir");
		this.btn_excluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btn_excluirActionPerformed(e);
			}

		});
		this.btn_excluir.setBounds(10, 94, 89, 23);
		this.btn_excluir.setEnabled(false);
		this.panel.add(this.btn_excluir);

		this.btn_cadastrar = new JButton("Cadastrar");
		this.btn_cadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btn_cadastrarActionPerformed(e);
			}

		});
		this.btn_cadastrar.setBounds(552, 377, 89, 23);
		this.btn_cadastrar.setEnabled(false);
		this.panel.add(this.btn_cadastrar);

		this.btn_cancelar = new JButton("Cancelar");
		this.btn_cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btn_cancelarActionPerformed(e);
			}

		});
		this.btn_cancelar.setBounds(453, 377, 89, 23);
		this.btn_cancelar.setEnabled(false);
		this.panel.add(this.btn_cancelar);

		this.lbl_nome = new JLabel("Nome:");
		this.lbl_nome.setBounds(125, 34, 40, 14);
		this.panel.add(this.lbl_nome);

		this.txtf_nome = new JTextField();
		this.txtf_nome.setBounds(161, 26, 363, 30);
		this.txtf_nome.setEnabled(false);
		this.txtf_nome.setColumns(10);
		this.panel.add(this.txtf_nome);

		this.lbl_especialidade = new JLabel("Especialidade:");
		this.lbl_especialidade.setBounds(534, 34, 86, 14);
		this.panel.add(this.lbl_especialidade);

		this.cb_especialidade = new JComboBox<>();
		this.vetor = new EspecialidadeDAO().searchAllArray();
		this.cb_especialidade.setModel(new DefaultComboBoxModel(this.vetor));
		this.cb_especialidade.setBounds(616, 31, 130, 20);
		this.cb_especialidade.setEnabled(false);
		this.panel.add(this.cb_especialidade);

		this.lbl_crm = new JLabel("CRM:");
		this.lbl_crm.setBounds(132, 68, 40, 14);
		this.panel.add(this.lbl_crm);

		this.txtf_crm = new JTextField();
		this.txtf_crm.setBounds(161, 60, 74, 30);
		this.txtf_crm.setEnabled(false);
		this.txtf_crm.setColumns(10);
		this.panel.add(this.txtf_crm);

		this.txtf_pesquisar = new JTextField();
		this.txtf_pesquisar.setColumns(10);
		this.txtf_pesquisar.setBounds(161, 144, 381, 30);
		this.txtf_pesquisar.setEnabled(false);
		this.panel.add(this.txtf_pesquisar);

		this.btn_pesquisar = new JButton("Pesquisar");
		this.btn_pesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btn_pesquisarActionPerformed(e);
			}

		});
		this.btn_pesquisar.setBounds(552, 148, 89, 23);
		this.btn_pesquisar.setEnabled(false);
		this.panel.add(this.btn_pesquisar);

		this.btn_3p = new JButton("...");
		this.btn_3p.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btn_3pActionPerformed(e);
			}
		});
		this.btn_3p.setBounds(756, 31, 30, 20);
		this.panel.add(this.btn_3p);

		this.ckb_porNome = new JCheckBox("Por Nome");
		this.ckb_porNome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ckb_porNomeActionPerformed(e);
			}

		});
		this.ckb_porNome.setBounds(647, 185, 97, 23);
		this.panel.add(this.ckb_porNome);

		this.ckb_porEspecialidade = new JCheckBox("Por Especialidade");
		this.ckb_porEspecialidade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ckb_porEspecialidadeActionPerformed(e);
			}

		});
		this.ckb_porEspecialidade.setBounds(647, 237, 139, 23);
		this.panel.add(this.ckb_porEspecialidade);

		this.ckb_porCrm = new JCheckBox("Por CRM");
		this.ckb_porCrm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ckb_porCrmActionPerformed(e);
			}

		});
		this.ckb_porCrm.setBounds(647, 211, 97, 23);
		this.panel.add(this.ckb_porCrm);

		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(161, 185, 480, 181);
		this.panel.add(this.scrollPane);

		this.tbl_medico = new JTable(new MedicoTableModel());
		this.tbl_medico.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tbl_medicoKeyReleased(e);
			}
		});
		this.tbl_medico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tbl_medicoMouseClicked(e);
			}

		});

		this.scrollPane.setViewportView(this.tbl_medico);

		this.lbl_cadMedico = new JLabel("Cadastro de M\u00E9dico");
		this.lbl_cadMedico.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.lbl_cadMedico.setBounds(338, 11, 155, 22);
		getContentPane().add(this.lbl_cadMedico);
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

	private void tbl_medicoKeyReleased(KeyEvent e) {

		if (((e.getKeyCode() == KeyEvent.VK_DOWN) || (e.getKeyCode() == KeyEvent.VK_UP)) && (this.btn_cadastrar.isEnabled() == false)) {
			MedicoTableModel model = (MedicoTableModel) this.tbl_medico.getModel();
			Medico m = model.getValueAt(this.tbl_medico.getSelectedRow());

			this.txtf_nome.setText(m.getNome());
			this.txtf_crm.setText(String.valueOf(m.getCrm()));
			this.cb_especialidade.setEditable(true);
			this.cb_especialidade.setSelectedItem(m.getEspecialidade());
			this.cb_especialidade.setEditable(false);
			this.txtf_nome.setEnabled(true);
			this.txtf_crm.setEnabled(true);
			this.cb_especialidade.setEnabled(true);
			this.btn_editar.setEnabled(true);
			this.btn_excluir.setEnabled(true);

		}
	}

	private void tbl_medicoMouseClicked(MouseEvent e) {

		MedicoTableModel model = (MedicoTableModel) this.tbl_medico.getModel();
		Medico m = model.getValueAt(this.tbl_medico.getSelectedRow());

		this.txtf_nome.setText(m.getNome());
		this.txtf_crm.setText(String.valueOf(m.getCrm()));
		this.cb_especialidade.setEditable(true);
		this.cb_especialidade.setSelectedItem(m.getEspecialidade());
		this.cb_especialidade.setEditable(false);
		this.txtf_nome.setEnabled(true);
		this.txtf_crm.setEnabled(true);
		this.cb_especialidade.setEnabled(true);
		this.btn_editar.setEnabled(true);
		this.btn_excluir.setEnabled(true);

	}
}
