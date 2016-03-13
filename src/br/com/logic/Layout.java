package br.com.logic;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import br.com.utils.Utils;

@SuppressWarnings("serial")
public class Layout extends JFrame {

	/**
	 * METODO QUE INICIA LAYOUT
	 */
	public void inicializarLayout() {
		// DETERMINA TITULO DO PROGRAMA
		setTitle(Utils.TITULO_JOGO);
		// DETERMINA TAMANHO DA TELA DO PROGRAMA
		setSize(Utils.SIZE_WIDTH, Utils.SIZE_HEIGHT);
		// DETERMINA SE E POSSIVEL REDIMENSIONAR TELA
		setResizable(false);
		// PADRAO PARA O CLOSE
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// LAYOUT
		setLayout(null);
		// DEIXA VISIVEL O PROGRAMA
		setVisible(true);
		// CRIA EVENTO DE KEYPRESS
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				Game.keyPressed(e);
			}
		});
	}
	
}
