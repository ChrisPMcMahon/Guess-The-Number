/*
Christopher McMahon
January, 30, 2015.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class GUIGuessingGame extends JFrame implements ActionListener {
	private JPanel panelA = new JPanel();
	private JPanel panelB = new JPanel();
	
	private JTextField guessTxtField;
	private JButton newGameButton;
	private JButton guessButton;
	private JLabel guessLabel;
	
	private static Color background = Color.LIGHT_GRAY;
	private static Color foreground = Color.BLACK;
	private static Random ranGen = new Random();
	
	private static int  ranNum;
	private static int max = 100;
	private static int min = 1;
	private static int curDist;
	private static int prevDist;
	private static int guessCount = 0;
	private static boolean guessFlag = false;
	private static String guessMessage = "Start a New Game";

	public GUIGuessingGame () {
		super("Guessing Game");
		
		setSize(255,200);
		setResizable(false);
		setLayout(new GridLayout(2,1,0,25));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelA.setLayout(new BorderLayout());
		panelB.setLayout(new BorderLayout());

		newGameButton = new JButton("New Game");
		this.newGameButton.addActionListener(this);
		
		guessButton = new JButton("Guess");
		guessButton.setEnabled(false);
		this.guessButton.addActionListener(this);
		
		guessLabel = new JLabel("",SwingConstants.CENTER);
		
		guessTxtField = new JTextField(5);
		guessTxtField.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(guessButton.isEnabled( ) == true) {
					String guess = guessTxtField.getText();
					
					if(guess.matches("[0-9]+")) {
						if((Integer.parseInt(guess) > min) && (Integer.parseInt(guess) < max)) {
							checkGuess(Integer.parseInt(guess));
			
							if(guessFlag == true) {
								guessButton.setEnabled(false);
								guessTxtField.setText("");
								guessMessage = "Number of Guesses : " + guessCount;
								foreground = Color.BLACK;
								background = Color.LIGHT_GRAY;
							}//if
						}//if
						else {
							guessMessage = "Guess a Number Between " + min + " and " + max;
							guessTxtField.setText("");			
						}//else	
					}//if
					else {
						guessMessage = "Guess a Number Between " + min + " and " + max;
						guessTxtField.setText("");		
					}//else
					repaint();
		    	}//if
		    }//actionPerformed
		});//anonymous class
		
		panelA.add(guessTxtField,BorderLayout.CENTER);
		panelA.add(guessButton, BorderLayout.SOUTH);
		
		panelB.add(newGameButton,BorderLayout.SOUTH);
		panelB.add(guessLabel,BorderLayout.NORTH);
		
		(panelA).setBackground(background);
		(panelB).setBackground(background);
		(getContentPane()).setBackground(background);
		
		add(panelA);
		add(panelB);
	}//GUIGuessingGame constructor
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == newGameButton) {
			guessTxtField.setText("");
			guessButton.setEnabled(true);
			guessMessage = "Guess a Number Between " + min + " and " + max;
			newGame();
		}//if
		else if(evt.getSource() == guessButton) {
			String guess = guessTxtField.getText();
				
			if(guess.matches("[0-9]+")) {
				if((Integer.parseInt(guess) > min) && (Integer.parseInt(guess) < max)) {
					checkGuess(Integer.parseInt(guess));
	
					if(guessFlag == true) {
						guessButton.setEnabled(false);
						guessTxtField.setText("");
						guessMessage = "Number of Guesses : " + guessCount;
						foreground = Color.BLACK;
						background = Color.LIGHT_GRAY;
					}//if
				}//if
				else {
					guessMessage = "Guess a Number Between " + min + " and " + max;
					guessTxtField.setText("");			
				}//else	
			}//if
			else {
				guessMessage = "Guess a Number Between " + min + " and " + max;
				guessTxtField.setText("");		
			}//else
		}//else if 
		
		repaint();
	}//actionPerformed

	public static void newGame() {
		ranNum = ranGen.nextInt(max - min + 1) + min;
		System.out.println(ranNum);
		curDist = max;
		prevDist = 0;
		guessCount = 0;
		guessFlag = false;
		foreground = Color.BLACK;
		background = Color.LIGHT_GRAY;
	}//newGame method
	
	public static void checkGuess(int guess) {
		curDist = Math.abs(ranNum - guess);

		if(guess == ranNum) {
			JOptionPane.showMessageDialog(null,"You Guessed the Number! ( " + ranNum + " )");
			guessFlag = true;
		}//if
		
		if(prevDist != 0) {
			if(curDist > prevDist) {
				background = Color.BLUE;
				foreground = Color.WHITE;
				guessMessage = "You're Getting Colder";
			}//else if 
			else if(curDist < prevDist) {
				background = Color.RED;
				foreground = Color.WHITE;
				guessMessage = "You're Getting Warmer";
			}//else if
		}//if
		guessCount ++;
		prevDist = curDist;
	}//checkGuess
	   
	public void paint(Graphics g) {
		super.paint(g);
		
		guessLabel.setForeground(foreground);
		guessLabel.setText(guessMessage);
		(panelA).setBackground(background);
		(panelB).setBackground(background);
		(getContentPane()).setBackground(background);
	} // end method paint
	   
	public static void main(String[] args) {
		GUIGuessingGame Frame = new GUIGuessingGame();
		Frame.setVisible(true);
	}//main
}//GUIGuessingGame
