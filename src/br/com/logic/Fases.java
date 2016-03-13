package br.com.logic;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

import br.com.utils.Utils;

public class Fases {

	// IMAGEM DA CERCA
	private ImageIcon imagemCerca = new ImageIcon(Utils.CAMINHO_IMAGEM_CERCA);
	// IMAGEM DO ALVO
	private ImageIcon imagemAlvo = new ImageIcon(Utils.CAMINHO_IMAGEM_ALVO);
	// IMAGEM DO BOX
	private ImageIcon imagemBox = new ImageIcon(Utils.CAMINHO_IMAGEM_BOX);
	// IMAGEM DO FUNDO
	private ImageIcon imagemFundo = new ImageIcon(Utils.CAMINHO_IMAGEM_FUNDO);
	
	/**
	 * DESENHA OS COMPONENTES (FUNDO, CERCAS, BOXS E ALVO)
	 */
	public void desenharFase(Graphics bbg, ImageObserver imgOb) {	
		try {
			// DESENHA A IMAGEM DE FUNDO DA TELA
			bbg.drawImage(imagemFundo.getImage(), 0, 0, Utils.SIZE_WIDTH, Utils.SIZE_HEIGHT, imgOb);
			// DESENHA AS IMAGENS DAS CERCAS
			for (int i = 0; i < Utils.matrizPosicoesCercas.length; i++) {
				bbg.drawImage(imagemCerca.getImage(),
						Utils.matrizPosicoesCercas[i][0],
						Utils.matrizPosicoesCercas[i][1],
						Utils.matrizPosicoesCercas[i][2],
						Utils.matrizPosicoesCercas[i][3], 
						imgOb);
			}
			// DESENHA AS IMAGENS DOS BOXS
			for (int i = 0; i < Utils.matrizPosicoesBoxs.length; i++) {
				bbg.drawImage(imagemBox.getImage(),
						Utils.matrizPosicoesBoxs[i][0],
						Utils.matrizPosicoesBoxs[i][1],
						Utils.matrizPosicoesBoxs[i][2],
						Utils.matrizPosicoesBoxs[i][3], 
						imgOb);
			}
			// DESENHA AS IMAGENS DOS ALVOS
			for (int i = 0; i < Utils.matrizPosicoesAlvos.length; i++) {
				bbg.drawImage(imagemAlvo.getImage(),
						Utils.matrizPosicoesAlvos[i][0],
						Utils.matrizPosicoesAlvos[i][1],
						Utils.matrizPosicoesAlvos[i][2],
						Utils.matrizPosicoesAlvos[i][3], 
						imgOb);
			}				
		} catch (Exception e) {
			// EXIBE MENSAGEM DE ERRO
			System.err.println("ERRO METODO 'desenharFase': " + e.getMessage());
		}
	}

	/**
	 * @return the imagemCerca
	 */
	public ImageIcon getImagemCerca() {
		return imagemCerca;
	}
	
	/**
	 * @return the imagemAlvo
	 */
	public ImageIcon getImagemAlvo() {
		return imagemAlvo;
	}

	/**
	 * @return the imagemBox
	 */
	public ImageIcon getImagemBox() {
		return imagemBox;
	}

	/**
	 * @return the imagemFundo
	 */
	public ImageIcon getImagemFundo() {
		return imagemFundo;
	}

}
