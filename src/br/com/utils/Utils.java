package br.com.utils;

public class Utils {

	// TITULO DO JOGO
	public static final String TITULO_JOGO = "JOGO";
	// LARGURA DA TELA
	public static final int SIZE_WIDTH = 500;
	// ALTURA DA TELA
	public static final int SIZE_HEIGHT = 500;

	// URL DA IMAGEM DO PERSONAGEM
	public static final String CAMINHO_IMAGEM_PERSONAGEM = "src/br/com/resource/galinha_50x51.png";
	// URL DA IMAGEM DE FUNDO
	public static final String CAMINHO_IMAGEM_FUNDO = "src/br/com/resource/fundo_500x500.png";
	// URL DA IMAGEM DA CERCA
	public static final String CAMINHO_IMAGEM_CERCA = "src/br/com/resource/cerca_50x51.png";
	// URL DA IMAGEM DO ALVO
	public static final String CAMINHO_IMAGEM_ALVO = "src/br/com/resource/buraco_50x51.png";
	// URL DA IMAGEM DO BOX
	public static final String CAMINHO_IMAGEM_BOX = "src/br/com/resource/box_50x51.png";

	// URL DA IMAGEM DA CERCA FASE 1
	public static final String CAMINHO_FASE_001_CERCA = "src/br/com/inf/Fase_001_Cerca";
	// URL DA IMAGEM DA BOX FASE 1
	public static final String CAMINHO_FASE_001_BOX = "src/br/com/inf/Fase_001_Box";
	// URL DA IMAGEM DA ALVO FASE 1
	public static final String CAMINHO_FASE_001_ALVO = "src/br/com/inf/Fase_001_Alvo";
	
	// POSICAO INICIAL X DO PERSONAGEM
	public static final int POSICAO_X_INICIAL_PERSONAGEM = 250;
	// POSICAO INICIAL Y DO PERSONAGEM
	public static final int POSICAO_Y_INICIAL_PERSONAGEM = 150;
	// VALOR DO MOVIMENTO DO PERSONAGEM
	public static final int MOVIMENTO_PERSONAGEM = 50;
	
	// MATRIZ COM AS POSICOES DOS BOXS
	public static int matrizPosicoesBoxs[][] = new int[23][4];
	// MATRIZ COM AS POSICOES DOS FUNDOS
	public static int matrizPosicoesCercas[][] = new int[23][4];
	// MATRIZ COM AS POSICOES DOS ALVOS
	public static int matrizPosicoesAlvos[][] = new int[23][4];
	
}
