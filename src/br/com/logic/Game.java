package br.com.logic;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import br.com.utils.Utils;

@SuppressWarnings("serial")
public class Game extends Layout{
	
	// REFERENCIA DO PERSONAGEM
	private static Personagem personagem;
	// BUFFER DA IMAGEM
	public BufferedImage backBuffer;
	// REFERENCIA DAS FASES
	public Fases fases = new Fases();
	
	/**
	 * CONSTRUTOS PRINCIPAL
	 */
	Game() {
		// CRIA O PERSONAGEM
		personagem = new Personagem(Utils.POSICAO_X_INICIAL_PERSONAGEM, Utils.POSICAO_Y_INICIAL_PERSONAGEM);
		// CRIA BUFFER DA IMAGEM
		backBuffer = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
		// OBTEM AS COORDENADAS DA CERCA, BOX E ALVO
		obterCoordenadas(Utils.matrizPosicoesCercas, Utils.CAMINHO_FASE_001_CERCA);
		obterCoordenadas(Utils.matrizPosicoesBoxs, Utils.CAMINHO_FASE_001_BOX);
		obterCoordenadas(Utils.matrizPosicoesAlvos, Utils.CAMINHO_FASE_001_ALVO);
	}
	
	/**
	 * METODO MAIN (PRINCIPAL DA APP)
	 */
	public static void main(String[] args) {
		// INSTANCIA O JOGO
		Game game = new Game();	
		// INICIA O LAYOUT DO JOGO
		game.inicializarLayout();
		// INICIA O JOGO
		game.run();
	}
	
	/**
	 * METODO QUE OBTEM COORDENADAS DE ACORDO COM UM ARQUIVO, E SETA VALORES PARA MATRIZ
	 */
	public static void obterCoordenadas(int [][] matriz, String caminho) {
		try {
			// BUSCA ARQUIVO
			FileReader arq = new FileReader(caminho);
			BufferedReader lerArq = new BufferedReader(arq);
			// FAZ A LEITURA DA LINHA DO ARQUIVO
			String linha = lerArq.readLine();
			// CONTADOR DE LINHAS
			int i = 0;
			// LOOP PARA LER O ARQUIVO ATE EXISTIR LINHA EM BRANCO
			while (linha != null) {		
				// PEGA AS COORDENADAS DE ACORDO COM A LINHA X,Y,Z,W
				String valorLinha[] = linha.split(" ");
				// DETERMINA OS VALORES PARA MATRIZ INFORMADA COMO PARAMETRO
				matriz[i][0] = Integer.valueOf(valorLinha[0]);
				matriz[i][1] = Integer.valueOf(valorLinha[1]);
				matriz[i][2] = Integer.valueOf(valorLinha[2]);
				matriz[i][3] = Integer.valueOf(valorLinha[3]);
				// PROXIMA LINHA DO ARQUIVO
				linha = lerArq.readLine();
				// CONTADOR SOMA UM
				i++;
			}
			// FECHA O ARQUIVO
			arq.close();
		} catch (IOException e) {
			// EXIBE MENSAGEM DE ERRO
			System.err.println("ERRO METODO 'obterCoordenadas': " + e.getMessage());
		}
	}
	
	/**
	 * METODO QUE REALIZA PESQUISA NA MATRIZ
	 */
	public static boolean pesquisaMatriz(int x, int y, int[][] obj) {
		// PESQUISA OBJETOS DENTRO DE UMA MATRIZ INFORMADA POR PARAMETRO
		for (int i = 0; i < obj.length; i++) {
			if(x == obj[i][0] && y == obj[i][1]) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Método que realiza pesquisa de box.
	 */
	public static int pesquisaBox(int x, int y) {
		for (int i = 0; i < Utils.matrizPosicoesBoxs.length; i++) {
			if(x == Utils.matrizPosicoesBoxs[i][0] && y == Utils.matrizPosicoesBoxs[i][1]) {
				return i;
			}
		}
		return 999999;
	}
	
	/**
	 * M�todo respons�vel pelo desenho dos componentes do jogo.
	 */
	public void desenharGraficos() {
		Graphics g = getGraphics();
		Graphics bbg = backBuffer.getGraphics();
		
		
		// Nivel 1
		fases.desenharFase(bbg, this);
				
		bbg.drawImage(personagem.getImagemIcon().getImage(), personagem.getX(), personagem.getY(), this);
		g.drawImage(backBuffer, 0, 0, this);
	}
	
	/**
	 * M�todo respons�vel pelo evento Key.
	 */
	public static void keyPressed(KeyEvent e) {
		// left arrow
		if (e.getKeyCode() == 37) {
			// Verifica o limite de movimento do personagem.
			if (Personagem.verificarLimiteMovimentoLeft(personagem.getX(), personagem.getY())) {
				// Verifica posição do box a esquerda.
				if (!pesquisaMatriz(personagem.getX() - Utils.MOVIMENTO_PERSONAGEM, personagem.getY(), 
						Utils.matrizPosicoesBoxs)) {
					//Imprimir apenas o personagem.
					personagem.setX(personagem.getX() - Utils.MOVIMENTO_PERSONAGEM);
				} else if(pesquisaBox(personagem.getX() - (Utils.MOVIMENTO_PERSONAGEM*2), personagem.getY()) == 999999){
					// Existe box a esquerda.
					int aux = pesquisaBox(personagem.getX() - Utils.MOVIMENTO_PERSONAGEM, personagem.getY());
					// Verifica se é possivel mover o box.
					if (!pesquisaMatriz(Utils.matrizPosicoesBoxs[aux][0] - Utils.MOVIMENTO_PERSONAGEM, 
							Utils.matrizPosicoesBoxs[aux][1], Utils.matrizPosicoesCercas)) {
						//Imprimir nova posição do box e o personagem.
						Utils.matrizPosicoesBoxs[aux][0] = Utils.matrizPosicoesBoxs[aux][0] - Utils.MOVIMENTO_PERSONAGEM;
						Utils.matrizPosicoesBoxs[aux][1] = Utils.matrizPosicoesBoxs[aux][1];
						personagem.setX(personagem.getX() - Utils.MOVIMENTO_PERSONAGEM);
					}					
				}				
			}
		}
		// up arrow
		else if (e.getKeyCode() == 38) {
			// Verifica o limite de movimento do personagem.
			if (Personagem.verificarLimiteMovimentoUp(personagem.getX(), personagem.getY())) {
				// Verifica posição do box em cima.
				if (!pesquisaMatriz(personagem.getX(), personagem.getY() - Utils.MOVIMENTO_PERSONAGEM, 
						Utils.matrizPosicoesBoxs)) {
					//Imprimir apenas o personagem.
					personagem.setY(personagem.getY() - Utils.MOVIMENTO_PERSONAGEM);
				} else if(pesquisaBox(personagem.getX(), personagem.getY() - (Utils.MOVIMENTO_PERSONAGEM*2)) == 999999) {
					// Existe box em cima.
					int aux = pesquisaBox(personagem.getX(), personagem.getY() - Utils.MOVIMENTO_PERSONAGEM);
					// Verifica se é possivel mover o box.
					if (!pesquisaMatriz(Utils.matrizPosicoesBoxs[aux][0], 
							Utils.matrizPosicoesBoxs[aux][1] - Utils.MOVIMENTO_PERSONAGEM, Utils.matrizPosicoesCercas)) {
						//Imprimir nova posição do box e o personagem.
						Utils.matrizPosicoesBoxs[aux][0] = Utils.matrizPosicoesBoxs[aux][0];
						Utils.matrizPosicoesBoxs[aux][1] = Utils.matrizPosicoesBoxs[aux][1] - Utils.MOVIMENTO_PERSONAGEM;
						personagem.setY(personagem.getY() - Utils.MOVIMENTO_PERSONAGEM);
					}					
				}				
			}				
		}
		// right arrow
		else if (e.getKeyCode() == 39) {
			// Verifica o limite de movimento do personagem.
			if (Personagem.verificarLimiteMovimentoRight(personagem.getX(), personagem.getY())) {
				// Verifica posição do box a direita.
				if (!pesquisaMatriz(personagem.getX() + Utils.MOVIMENTO_PERSONAGEM, personagem.getY(), 
						Utils.matrizPosicoesBoxs)) {
					//Imprimir apenas o personagem.
					personagem.setX(personagem.getX() + Utils.MOVIMENTO_PERSONAGEM);
				} else if(pesquisaBox(personagem.getX() + (Utils.MOVIMENTO_PERSONAGEM*2), personagem.getY()) == 999999) {
					// Existe box a direita.
					int aux = pesquisaBox(personagem.getX() + Utils.MOVIMENTO_PERSONAGEM, personagem.getY());
					// Verifica se é possivel mover o box.
					if (!pesquisaMatriz(Utils.matrizPosicoesBoxs[aux][0] + Utils.MOVIMENTO_PERSONAGEM, 
							Utils.matrizPosicoesBoxs[aux][1], Utils.matrizPosicoesCercas)) {
						//Imprimir nova posição do box e o personagem.
						Utils.matrizPosicoesBoxs[aux][0] = Utils.matrizPosicoesBoxs[aux][0] + Utils.MOVIMENTO_PERSONAGEM;
						Utils.matrizPosicoesBoxs[aux][1] = Utils.matrizPosicoesBoxs[aux][1];
						personagem.setX(personagem.getX() + Utils.MOVIMENTO_PERSONAGEM);
					}					
				}				
			}			
		}
		// down arrow
		else if (e.getKeyCode() == 40) {
			// Verifica o limite de movimento do personagem.
			if (Personagem.verificarLimiteMovimentoDown(personagem.getX(), personagem.getY())) {
				// Verifica posição do box em baixo.
				if (!pesquisaMatriz(personagem.getX(), personagem.getY() + Utils.MOVIMENTO_PERSONAGEM, 
						Utils.matrizPosicoesBoxs)) {
					//Imprimir apenas o personagem.
					personagem.setY(personagem.getY() + Utils.MOVIMENTO_PERSONAGEM);
				} else if(pesquisaBox(personagem.getX(), personagem.getY() + (Utils.MOVIMENTO_PERSONAGEM*2)) == 999999) {
					// Existe box em baixo.
					int aux = pesquisaBox(personagem.getX(), personagem.getY() + Utils.MOVIMENTO_PERSONAGEM);
					// Verifica se é possivel mover o box.
					if (!pesquisaMatriz(Utils.matrizPosicoesBoxs[aux][0], 
							Utils.matrizPosicoesBoxs[aux][1] + Utils.MOVIMENTO_PERSONAGEM, Utils.matrizPosicoesCercas)) {
						//Imprimir nova posição do box e o personagem.
						Utils.matrizPosicoesBoxs[aux][0] = Utils.matrizPosicoesBoxs[aux][0];
						Utils.matrizPosicoesBoxs[aux][1] = Utils.matrizPosicoesBoxs[aux][1] + Utils.MOVIMENTO_PERSONAGEM;
						personagem.setY(personagem.getY() + Utils.MOVIMENTO_PERSONAGEM);
					}					
				}				
			}				
		}
	}
	
	/**
	 * M�todo run da thread.
	 */
	public void run() {		
		while(true) {
			// Verifica final do game.
			/*if((Utils.matrizPosicoesBoxs[0][0] == 150 && Utils.matrizPosicoesBoxs[0][1] == 300
					|| Utils.matrizPosicoesBoxs[0][0] == 200 && Utils.matrizPosicoesBoxs[0][1] == 300
					|| Utils.matrizPosicoesBoxs[0][0] == 250 && Utils.matrizPosicoesBoxs[0][1] == 300)
					&& (Utils.matrizPosicoesBoxs[1][0] == 150 && Utils.matrizPosicoesBoxs[1][1] == 300
							|| Utils.matrizPosicoesBoxs[1][0] == 200 && Utils.matrizPosicoesBoxs[1][1] == 300
							|| Utils.matrizPosicoesBoxs[1][0] == 250 && Utils.matrizPosicoesBoxs[1][1] == 300)
							&& (Utils.matrizPosicoesBoxs[2][0] == 150 && Utils.matrizPosicoesBoxs[2][1] == 300
									|| Utils.matrizPosicoesBoxs[2][0] == 200 && Utils.matrizPosicoesBoxs[2][1] == 300
									|| Utils.matrizPosicoesBoxs[2][0] == 250 && Utils.matrizPosicoesBoxs[2][1] == 300)) {
				System.out.println("FINAL");
				break;
			}	*/		
			// Desenha o personagem e o fundo.
			desenharGraficos();
			try {
				//System.out.println("LOG: ActionThreads starting.");
				// Tempo da thread.
				Thread.sleep(1);
			} catch (Exception e) {
				//System.out.println("LOG: ActionThreads interrupted.");
			} finally {
				//System.out.println("LOG: ActionThreads terminating.");
			}
		}
	}

}
