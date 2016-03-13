package br.com.logic;

import javax.swing.ImageIcon;

import br.com.utils.Utils;

public class Personagem {

	// IMAGEM DO PERSONAGEM
	private ImageIcon imagemIcon = new ImageIcon(Utils.CAMINHO_IMAGEM_PERSONAGEM);
	// POSICAO HORIZONTAL DO PERSONAGEM
	private int x;
	// POSICAO VERTICAL DO PERSONAGEM
	private int y;

	/**
	 * METODO CONSTRUTOR QUE DETERMINA A POSICAO DO PERSONAGEM
	 */
	public Personagem(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * METODO QUE VERIFICA O LIMITE DE MOVIMENTO DO PERSONAGEM DE ACORDO COM AS CERCAS (ESQUERDA)
	 */
	public static boolean verificarLimiteMovimentoLeft(int x, int y) {
		// LEFT
		for (int i = 0; i < Utils.matrizPosicoesCercas.length; i++) {
			if(x - Utils.MOVIMENTO_PERSONAGEM == Utils.matrizPosicoesCercas[i][0] 
					&& y == Utils.matrizPosicoesCercas[i][1]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * METODO QUE VERIFICA O LIMITE DE MOVIMENTO DO PERSONAGEM DE ACORDO COM AS CERCAS (DIREITA)
	 */
	public static boolean verificarLimiteMovimentoRight(int x, int y) {
		// RIGHT
		for (int i = 0; i < Utils.matrizPosicoesCercas.length; i++) {
			if(x + Utils.MOVIMENTO_PERSONAGEM == Utils.matrizPosicoesCercas[i][0] 
					&& y == Utils.matrizPosicoesCercas[i][1]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * METODO QUE VERIFICA O LIMITE DE MOVIMENTO DO PERSONAGEM DE ACORDO COM AS CERCAS (CIMA)
	 */
	public static boolean verificarLimiteMovimentoUp(int x, int y) {
		// UP
		for (int i = 0; i < Utils.matrizPosicoesCercas.length; i++) {
			if(x == Utils.matrizPosicoesCercas[i][0] 
					&& y - Utils.MOVIMENTO_PERSONAGEM == Utils.matrizPosicoesCercas[i][1]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * METODO QUE VERIFICA O LIMITE DE MOVIMENTO DO PERSONAGEM DE ACORDO COM AS CERCAS (BAIXO)
	 */
	public static boolean verificarLimiteMovimentoDown(int x, int y) {
		// DOWN
		for (int i = 0; i < Utils.matrizPosicoesCercas.length; i++) {
			if(x == Utils.matrizPosicoesCercas[i][0] 
					&& y + Utils.MOVIMENTO_PERSONAGEM == Utils.matrizPosicoesCercas[i][1]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the imagem
	 */
	public ImageIcon getImagemIcon() {
		return imagemIcon;
	}

}
