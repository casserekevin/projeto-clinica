package br.com.kevin.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {

	// Menu
	private JMenuBar mnBar;
	private JMenu mn_cadastrar;
	private JMenuItem mni_medico;
	private JMenuItem mni_paciente;
	private JMenuItem mni_enfermeiro;
	private JMenuItem mni_usuario;
	private JMenu mn_relatorio;
	private JMenu mn_ferramentas;
	private JMenuItem mni_tela_boas_vindas;
	private JMenu mn_sair;
	private JMenuItem mni_sair;

	// Fundo TelaPrincipal
	private JLabel lbl_fundoTelaPrincipal;

	// Internal Frame
	private JInternalFrame if_bemVindo;
	private JLabel lbl_sis_ger;
	private JButton btn_close;
	private JPanel panel_internal;
	private JLabel lbl_pi_cadastrar;
	private JButton btn_pi_medico;
	private JButton btn_pi_enfermeira;
	private JButton btn_pi_paciente;
	private JLabel lbl_pi_agendar;
	private JButton btn_pi_agenda;
	private JLabel lbl_fundoPanelInternal;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				TelaPrincipal frame = new TelaPrincipal();
				frame.setVisible(true);
			}
		});
	}

	public TelaPrincipal() {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 650);
		setLocationRelativeTo(null);

		getContentPane().setLayout(null);

		initMenu();

		initInternalFrame();

		lbl_fundoTelaPrincipal = new JLabel();
		lbl_fundoTelaPrincipal.setIcon(new ImageIcon(getClass().getResource("/br/com/kevin/img/fundo_telaprincipal.jpg")));
		lbl_fundoTelaPrincipal.setBounds(0, 0, 1084, 590);
		getContentPane().add(lbl_fundoTelaPrincipal);
	}

	private void initMenu() {
		mnBar = new JMenuBar();
		setJMenuBar(mnBar);

		mn_cadastrar = new JMenu("Cadastrar");
		mnBar.add(mn_cadastrar);

		mni_medico = new JMenuItem("M\u00E9dico");
		mni_medico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mni_medicoActionPerformed(evt);
			}
		});
		mn_cadastrar.add(mni_medico);

		mni_paciente = new JMenuItem("Paciente");
		mn_cadastrar.add(mni_paciente);

		mni_enfermeiro = new JMenuItem("Enfermeira");
		mn_cadastrar.add(mni_enfermeiro);

		mni_usuario = new JMenuItem("Usu\u00E1rio");
		mn_cadastrar.add(mni_usuario);

		mn_relatorio = new JMenu("Relat\u00F3rios");
		mnBar.add(mn_relatorio);

		mn_ferramentas = new JMenu("Ferramentas");
		mnBar.add(mn_ferramentas);

		mni_tela_boas_vindas = new JMenuItem("Tela de Boas Vindas");
		mni_tela_boas_vindas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mni_tela_boas_vindasActionPerformed(evt);
			}
		});
		mn_ferramentas.add(mni_tela_boas_vindas);

		mn_sair = new JMenu("Sair");
		mnBar.add(mn_sair);

		mni_sair = new JMenuItem("Sair");
		mni_sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mni_sairActionPerformed(evt);
			}
		});
		mn_sair.add(mni_sair);
	}

	private void initInternalFrame() {
		if_bemVindo = new JInternalFrame("Bem Vindo");
		if_bemVindo.setBounds(0, 110, 1084, 477);
		if_bemVindo.setVisible(true);
		if_bemVindo.getContentPane().setLayout(null);

		panel_internal = new JPanel();
		panel_internal.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_internal.setBounds(0, 78, 1073, 369);
		panel_internal.setLayout(null);
		if_bemVindo.getContentPane().add(panel_internal);

		lbl_pi_cadastrar = new JLabel("Cadastrar -");
		lbl_pi_cadastrar.setBounds(10, 10, 65, 15);
		panel_internal.add(lbl_pi_cadastrar);

		btn_pi_medico = new JButton();
		btn_pi_medico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mni_medicoActionPerformed(evt);
			}
		});
		btn_pi_medico.setToolTipText("M\u00E9dico");
		btn_pi_medico.setIcon(new ImageIcon(getClass().getResource("/br/com/kevin/img/Medico-icon.png")));
		btn_pi_medico.setBounds(10, 35, 136, 136);
		panel_internal.add(btn_pi_medico);

		btn_pi_enfermeira = new JButton();
		btn_pi_enfermeira.setToolTipText("Enfermeira");
		btn_pi_enfermeira.setIcon(new ImageIcon(getClass().getResource("/br/com/kevin/img/Enfermeira-icon.png")));
		btn_pi_enfermeira.setBounds(156, 35, 136, 136);
		panel_internal.add(btn_pi_enfermeira);

		btn_pi_paciente = new JButton();
		btn_pi_paciente.setToolTipText("Paciente");
		btn_pi_paciente.setIcon(new ImageIcon(getClass().getResource("/br/com/kevin/img/Paciente-icon.png")));
		btn_pi_paciente.setBounds(302, 35, 136, 136);
		panel_internal.add(btn_pi_paciente);

		lbl_pi_agendar = new JLabel("Agendar -");
		lbl_pi_agendar.setBounds(10, 182, 53, 14);
		panel_internal.add(lbl_pi_agendar);

		btn_pi_agenda = new JButton();
		btn_pi_agenda.setToolTipText("Agendamento");
		btn_pi_agenda.setIcon(new ImageIcon(getClass().getResource("/br/com/kevin/img/Agenda-icon.png")));
		btn_pi_agenda.setBounds(10, 207, 136, 136);
		panel_internal.add(btn_pi_agenda);

		lbl_fundoPanelInternal = new JLabel();
		lbl_fundoPanelInternal.setIcon(new ImageIcon(getClass().getResource("/br/com/kevin/img/internalframe.png")));
		lbl_fundoPanelInternal.setBounds(0, 0, 1073, 369);
		panel_internal.add(lbl_fundoPanelInternal);

		lbl_sis_ger = new JLabel("Sistema de Gerenciamento:");
		lbl_sis_ger.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_sis_ger.setBounds(10, 30, 215, 16);
		if_bemVindo.getContentPane().add(lbl_sis_ger);

		btn_close = new JButton();
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btn_closeActionPerformed(evt);
			}
		});
		btn_close.setToolTipText("Fechar Tela de Boas Vindas");
		btn_close.setIcon(new ImageIcon(getClass().getResource("/br/com/kevin/img/exit-icon.svg.png")));
		btn_close.setBounds(1053, 0, 20, 20);
		if_bemVindo.getContentPane().add(btn_close);
		getContentPane().add(if_bemVindo);
	}

	private void mni_medicoActionPerformed(ActionEvent evt) {
		CadMedicoView cadastroMedico = new CadMedicoView();
		cadastroMedico.setVisible(true);
	}

	private void btn_closeActionPerformed(ActionEvent evt) {
		if_bemVindo.dispose();
	}

	private void mni_sairActionPerformed(ActionEvent evt) {
		System.exit(0);
	}

	private void mni_tela_boas_vindasActionPerformed(ActionEvent evt) {
		if_bemVindo.setBounds(0, 110, 1084, 477);
		if_bemVindo.setVisible(true);
	}
}
