package br.com.kevin.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EtchedBorder;

import br.com.kevin.model.bean.Especialidade;
import br.com.kevin.model.dao.EspecialidadeDAO;

@SuppressWarnings("serial")
public class CadMedicoView extends JDialog {

	private JPanel panel;
	private JButton btn_novo;
	private JButton btn_editar;
	private JButton btn_excluir;
	private JButton btn_salvar;
	private JButton btn_cancelar;
	private JLabel lbl_nome;
	private JTextField txtf_nome;
	private JLabel lbl_especialidade;
	private JComboBox<Especialidade> cb_especialidade;
	private JLabel lbl_crm;
	private JTextField txtf_crm;
	private JTextField txtf_pesquisar;
	private JButton btn_pesquisar;
	private JTable table;
	private JLabel lbl_cadMedico;
	private JButton btn_3p;

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
		setBounds(100, 100, 836, 477);
		setLocationRelativeTo(null);
		setModal(true);

		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 39, 800, 388);
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
		btn_excluir.setBounds(10, 94, 89, 23);
		btn_excluir.setEnabled(false);
		panel.add(btn_excluir);

		btn_salvar = new JButton("Salvar");
		btn_salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_salvar.setBounds(616, 354, 89, 23);
		btn_salvar.setEnabled(false);
		panel.add(btn_salvar);

		btn_cancelar = new JButton("Cancelar");
		btn_cancelar.setBounds(517, 354, 89, 23);
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

		table = new JTable();
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setBackground(SystemColor.control);
		table.setBounds(161, 162, 544, 181);
		panel.add(table);

		txtf_pesquisar = new JTextField();
		txtf_pesquisar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtf_pesquisar.getText().equals("Ex: Eliseu dos Santos")) {
					txtf_pesquisar.setForeground(Color.BLACK);
					txtf_pesquisar.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtf_pesquisar.getText().equals("")) {
					txtf_pesquisar.setForeground(Color.GRAY);
					txtf_pesquisar.setText("Ex: Eliseu dos Santos");
				}
			}
		});
		txtf_pesquisar.setForeground(Color.GRAY);
		txtf_pesquisar.setText("Ex: Eliseu dos Santos");
		txtf_pesquisar.setColumns(10);
		txtf_pesquisar.setBounds(161, 121, 445, 30);
		panel.add(txtf_pesquisar);

		btn_pesquisar = new JButton("Pesquisar");
		btn_pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_pesquisar.setBounds(616, 125, 89, 23);
		panel.add(btn_pesquisar);

		btn_3p = new JButton("...");
		btn_3p.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_3pActionPerformed(e);
			}
		});
		btn_3p.setBounds(756, 31, 30, 20);
		panel.add(btn_3p);

		lbl_cadMedico = new JLabel("Cadastro de M\u00E9dico");
		lbl_cadMedico.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_cadMedico.setBounds(349, 11, 155, 22);
		getContentPane().add(lbl_cadMedico);
	}

	private void btn_novoActionPerformed(ActionEvent e) {
		btn_salvar.setEnabled(true);
		txtf_nome.setEnabled(true);
		cb_especialidade.setEnabled(true);
		txtf_crm.setEnabled(true);
	}

	private void btn_3pActionPerformed(ActionEvent evt) {
		CadEspecialidadeView janela = new CadEspecialidadeView(vetor);
		janela.setVisible(true);

		vetor = janela.getArrayTable();
		cb_especialidade.setModel(new DefaultComboBoxModel<>(janela.getArrayTable()));
	}
}
