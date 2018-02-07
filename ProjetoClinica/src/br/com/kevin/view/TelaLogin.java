package br.com.kevin.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

@SuppressWarnings("serial")
public class TelaLogin extends JFrame {

	private JLabel lbl_usuario;
	private JLabel lbl_senha;
	private JTextField txtf_usuario;
	private JPasswordField pwdf_senha;
	private JButton btn_sair;
	private JLabel lbl_fundoTelaLogin;
	private JButton btn_acessar;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				TelaLogin frame = new TelaLogin();
				frame.setVisible(true);
			}
		});
	}

	public TelaLogin() {
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
		setSize(new Dimension(516, 265));
		setLocationRelativeTo(null);

		getContentPane().setLayout(null);

		lbl_usuario = new JLabel("Usu\u00E1rio:");
		lbl_usuario.setBounds(250, 81, 48, 14);

		lbl_senha = new JLabel("Senha:");
		lbl_senha.setBounds(258, 111, 40, 14);

		txtf_usuario = new JTextField();
		txtf_usuario.setBounds(300, 74, 140, 28);
		txtf_usuario.setColumns(10);

		pwdf_senha = new JPasswordField();
		pwdf_senha.setBounds(300, 104, 140, 28);

		btn_sair = new JButton("Sair");
		btn_sair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btn_sairActionPerformed(evt);
			}
		});
		btn_sair.setBounds(310, 143, 51, 23);

		btn_acessar = new JButton("Acessar");
		btn_acessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btn_acessarActionPerformed(evt);
			}
		});
		btn_acessar.setBounds(363, 143, 77, 23);
		getRootPane().setDefaultButton(btn_acessar);

		lbl_fundoTelaLogin = new JLabel();
		lbl_fundoTelaLogin.setIcon(new ImageIcon(getClass().getResource("/br/com/kevin/img/fundotelaLogin.png")));
		lbl_fundoTelaLogin.setBounds(0, 0, 500, 260);

		getContentPane().add(lbl_usuario);
		getContentPane().add(lbl_senha);
		getContentPane().add(txtf_usuario);
		getContentPane().add(pwdf_senha);
		getContentPane().add(btn_sair);
		getContentPane().add(btn_acessar);
		getContentPane().add(lbl_fundoTelaLogin);
	}

	@SuppressWarnings("deprecation")
	private void btn_acessarActionPerformed(ActionEvent evt) {
		if (txtf_usuario.getText().equals("admin") && pwdf_senha.getText().equals("admin")) {
			TelaPrincipal telaPrincipal = new TelaPrincipal();
			telaPrincipal.setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void btn_sairActionPerformed(ActionEvent evt) {
		System.exit(0);
	}
}
