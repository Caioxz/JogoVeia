import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoVelha extends JFrame {

    private JTextField nomeJogo1;
    private JTextField nomeJogo2;
    private JLabel nome1;
    private JLabel nome2;
    private JButton game;
    private JButton newGame;
    private JRadioButton jogador1X, jogador1O;
    private JRadioButton jogador2X, jogador2O;
    private ButtonGroup jogador1Group, jogador2Group;
    private JButton[][] buttons;
    private boolean jogadorX = true;
    private boolean jogoAcabou = false;

    public JogoVelha() {
        this.setTitle("Jogo da Velha");
        this.setSize(780, 630);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(new Color(255, 255, 255));
        this.setResizable(false);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel jogador1 = new JPanel();
        jogador1.setLayout(null);

        nome1 = new JLabel("Nome:");
        nome1.setBounds(10, 10, 90, 50);
        nome1.setFont(new Font("Poppins", Font.PLAIN, 20));
        nome1.setForeground(Color.BLACK);
        jogador1.add(nome1);

        nomeJogo1 = new JTextField();
        nomeJogo1.setBounds(70, 20, 170, 30);
        nomeJogo1.setFont(new Font("Poppins", Font.PLAIN, 20));
        jogador1.add(nomeJogo1);

        JLabel escolha1 = new JLabel("Escolha X ou O:");
        escolha1.setBounds(10, 60, 200, 20);
        escolha1.setFont(new Font("Poppins", Font.PLAIN, 16));
        jogador1.add(escolha1);

        jogador1X = new JRadioButton("X");
        jogador1X.setBounds(10, 90, 50, 30);
        jogador1X.setFont(new Font("Poppins", Font.PLAIN, 16));
        jogador1O = new JRadioButton("O");
        jogador1O.setBounds(70, 90, 50, 30);
        jogador1O.setFont(new Font("Poppins", Font.PLAIN, 16));

        jogador1Group = new ButtonGroup();
        jogador1Group.add(jogador1X);
        jogador1Group.add(jogador1O);

        jogador1.add(jogador1X);
        jogador1.add(jogador1O);

        tabbedPane.addTab("Jogador 1", null, jogador1, "Digite o nome do primeiro jogador");

        JPanel jogador2 = new JPanel();
        jogador2.setLayout(null);
        jogador2.setBackground(new Color(220, 220, 220));

        nome2 = new JLabel("Nome:");
        nome2.setBounds(10, 10, 90, 50);
        nome2.setFont(new Font("Poppins", Font.PLAIN, 20));
        nome2.setForeground(Color.BLACK);
        jogador2.add(nome2);

        nomeJogo2 = new JTextField();
        nomeJogo2.setBounds(140, 20, 170, 30);
        nomeJogo2.setFont(new Font("Poppins", Font.PLAIN, 20));
        jogador2.add(nomeJogo2);

        JLabel escolha2 = new JLabel("Escolha X ou O:");
        escolha2.setBounds(10, 60, 200, 20);
        escolha2.setFont(new Font("Poppins", Font.PLAIN, 16));
        jogador2.add(escolha2);

        jogador2X = new JRadioButton("X");
        jogador2X.setBounds(10, 90, 50, 30);
        jogador2X.setFont(new Font("Poppins", Font.PLAIN, 16));
        jogador2O = new JRadioButton("O");
        jogador2O.setBounds(70, 90, 50, 30);
        jogador2O.setFont(new Font("Poppins", Font.PLAIN, 16));

        jogador2Group = new ButtonGroup();
        jogador2Group.add(jogador2X);
        jogador2Group.add(jogador2O);

        jogador2.add(jogador2X);
        jogador2.add(jogador2O);

        tabbedPane.addTab("Jogador 2", null, jogador2, "Digite o nome do segundo jogador");

        JPanel gameRun = new JPanel();
        gameRun.setLayout(null);
        tabbedPane.addTab("Jogar", null, gameRun, "Se prepare para jogar!");

        JLabel imgTab = new JLabel();
        imgTab.setBounds(10, 90, 340, 320);
        ImageIcon boardimg = new ImageIcon("tictac.png");
        Image boardRe = boardimg.getImage().getScaledInstance(imgTab.getWidth(), imgTab.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newBoard = new ImageIcon(boardRe);
        imgTab.setIcon(newBoard);
        gameRun.add(imgTab);

        newGame = new JButton("Novo game");
        newGame.setBackground(new Color(255, 255, 255));
        newGame.setBounds(100, 500, 100, 40);
        newGame.setFocusPainted(false);
        gameRun.add(newGame);

        // Botões do jogo da velha
        buttons = new JButton[3][3];
        int buttonSize = 100; // Aumentar o tamanho dos botões
        int xOffset = 20; // Ajustar o espaço à esquerda
        int yOffset = 90; // Ajustar o espaço acima

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setBounds(xOffset + col * (buttonSize + 10), yOffset + row * (buttonSize + 10), buttonSize, buttonSize); // Adicionar 10 pixels de espaçamento entre os botões
                buttons[row][col].setBackground(new Color(238, 238, 238));
                buttons[row][col].setBorder(null);
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!jogoAcabou) {
                            JButton button = (JButton) e.getSource();
                            if (button.getText().isEmpty()) {
                                if (jogadorX) {
									if (jogador1X.isSelected()) {
										button.setText("X");
										button.setBackground(Color.RED);
									} else {
										button.setText("O");
										button.setBackground(Color.BLUE);
									}
									jogadorX = !jogadorX;
	
									// Verificar vitória
									if (verificarVitoria()) {
										jogoAcabou = true;
										JOptionPane.showMessageDialog(null, "Jogador " + (jogadorX ? "O" : "X") + " venceu!", "Fim do Jogo", JOptionPane.INFORMATION_MESSAGE);
									}
								}
							}
						}
					}});
					gameRun.add(buttons[row][col]);
				}
			}
	
			// Configurar o botão Novo Jogo
			newGame.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					reiniciarJogo();
				}
			});
	
			add(tabbedPane);
			this.setVisible(true);
		}
	
		// Método para verificar se há um vencedor
		private boolean verificarVitoria() {
			// Verificar linhas e colunas
			for (int i = 0; i < 3; i++) {
				if (!buttons[i][0].getText().isEmpty() &&
					buttons[i][0].getText().equals(buttons[i][1].getText()) &&
					buttons[i][0].getText().equals(buttons[i][2].getText())) {
					return true; // Linha vencedora

				}
				if (!buttons[0][i].getText().isEmpty() &&
					buttons[0][i].getText().equals(buttons[1][i].getText()) &&
					buttons[0][i].getText().equals(buttons[2][i].getText())) {
					return true; // Coluna vencedora
				}
			}
	
			// Verificar diagonais
			if (!buttons[0][0].getText().isEmpty() &&
				buttons[0][0].getText().equals(buttons[1][1].getText()) &&
				buttons[0][0].getText().equals(buttons[2][2].getText())) {
				return true; // Diagonal principal
			}
			if (!buttons[0][2].getText().isEmpty() &&
				buttons[0][2].getText().equals(buttons[1][1].getText()) &&
				buttons[0][2].getText().equals(buttons[2][0].getText())) {
				return true; // Diagonal secundária
			}
	
			return false;
		}
	
		// Método para reiniciar o jogo
		private void reiniciarJogo() {
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					buttons[row][col].setText("");
					buttons[row][col].setBackground(new Color(238, 238, 238));
				}
			}
			jogoAcabou = false;
			jogadorX = true;
			jogador1Group.clearSelection();
			jogador2Group.clearSelection();
		}
	
		public static void main(String[] args) {
			SwingUtilities.invokeLater(() -> {
				new JogoVelha();
			});
		}
	}
	
									