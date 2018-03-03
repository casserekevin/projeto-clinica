package br.com.kevin.main;

import java.awt.EventQueue;

import br.com.kevin.view.TelaLogin;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				TelaLogin frame = new TelaLogin();
				frame.setVisible(true);
			}
		});

	}
}
