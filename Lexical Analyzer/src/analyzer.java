import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;

public class analyzer {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					analyzer window = new analyzer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public analyzer() {
		initialize();

	}

	static boolean isKeyword(String lexeme) {

		if (lexeme.equals("int") || lexeme.equals("float") || lexeme.equals("double") || lexeme.equals("char")
				|| lexeme.equals("if") || lexeme.equals("while") || lexeme.equals("for") || lexeme.equals("break")
				|| lexeme.equals("switch") || lexeme.equals("case")) {
			
			return true;
		} else {
			return false;
		}
	}

	static boolean isLetter(char word) {
		if ((word >= 'a' && word <= 'z') || (word >= 'A' && word <= 'Z'))
			return true;
		else
			return false;
	}

	static boolean isDigit(char word) {
		if (word >= '0' && word <= '9')
			return true;
		else
			return false;
	}

	static boolean isDelimeter(char word) {
		if (word == ' ' || word == '\t' || word == '\n')
			return true;
		else
			return false;
	}

	static int printLexeme(String type, String lexeme, int state, DefaultListModel<String> dlm) {

		dlm.addElement(MessageFormat.format("Tokken < {0}, {1} > \n.", type, lexeme));
		return state;
	}

	void lexicalAnalyzer(String input, DefaultListModel<String> dlm) {
		int state = 0;
		input += " ";
		input += "\0";
		String lexeme = "";
		boolean flag = false;
		char currentChar = ' ';
		int i = 0;

		while (input.charAt(i) != '\0') {
			currentChar = input.charAt(i);
			flag = false;
			switch (state) {
			case 0:
				if (isLetter(currentChar)) {
					state = 1;
				} else if (isDigit(currentChar)) {
					state = 2;
				} else if (currentChar == '=') {
					state = 6;
				} else if (currentChar == '+') {
					state = 4;
				} else if (currentChar == '-') {
					state = 5;
				} else if (currentChar == '*') {
					state = printLexeme("AO", String.valueOf(currentChar), 0, dlm);
					lexeme = "";
					flag = true;
				} else if (currentChar == '/') {
					state = 7;
				} else if (currentChar == ';') {
					state = printLexeme("ST", String.valueOf(currentChar), 0, dlm);
					lexeme = "";
					flag = true;
				} else if (currentChar == '!') {
					state = printLexeme("NOT", String.valueOf(currentChar), 0, dlm);
					lexeme = "";
					flag = true;
				} else if (currentChar == '&') {
					state = printLexeme("AO", String.valueOf(currentChar), 0, dlm);
					lexeme = "";
					flag = true;
				} else if (currentChar == '<') {
					state = 3;
				} else if (currentChar == '_') {
					state = 9;
				} else if (currentChar == '>') {
					state = printLexeme("GT", String.valueOf(currentChar), 0, dlm);
					lexeme = "";
					flag = true;
				} else if (currentChar == '{') {
					state = printLexeme("OCP", String.valueOf(currentChar), 0, dlm);
					lexeme = "";
					flag = true;
				} else if (currentChar == '}') {
					state = printLexeme("CCP", String.valueOf(currentChar), 0, dlm);
					lexeme = "";
					flag = true;
				} else if (currentChar == '[') {
					state = printLexeme("OSB", String.valueOf(currentChar), 0, dlm);
					lexeme = "";
					flag = true;
				} else if (currentChar == ']') {
					state = printLexeme("CSB", String.valueOf(currentChar), 0, dlm);
					lexeme = "";
					flag = true;
				} else if (currentChar == '(') {
					state = printLexeme("OB", String.valueOf(currentChar), 0, dlm);
					lexeme = "";
					flag = true;
				} else if (currentChar == ')') {
					state = printLexeme("CB", String.valueOf(currentChar), 0, dlm);
					lexeme = "";
					flag = true;
				} else if (isDelimeter(currentChar)) {
					flag = true;
				} else {
					dlm.addElement("Invalid Token");
					flag = true;
				}

				break;

			case 1:
				if (!(isLetter(currentChar) || isDigit(currentChar))) {

					state = 0;
					if (isKeyword(lexeme)) {
						printLexeme("KW", String.valueOf(lexeme), 0, dlm);
					} else {
						printLexeme("ID", String.valueOf(lexeme), 0, dlm);
					}
					lexeme = "";

					continue;
				}
				break;

			case 2:
				if (currentChar == '.') {
					state = 69;
				} else if (!isDigit(currentChar)) {
					state = printLexeme("IL", String.valueOf(lexeme), 0, dlm);
					lexeme = "";
					continue;
				}
				break;

			case 69:
				if (!isDigit(currentChar)) {
					state = printLexeme("Float", String.valueOf(lexeme), 0, dlm);
					lexeme = "";
					continue;
				}
				break;

			case 3:
				if (currentChar == '=') {
					// Before this it was printing like < LT, <= > and <Lit, =3> Multiple flag
					// solved the problem
					state = printLexeme("LE", String.valueOf(lexeme + currentChar), 0, dlm);
					lexeme = "";
					flag = true;

				} else {
					state = printLexeme("LT", String.valueOf(lexeme), 0, dlm);
					lexeme = "";
					continue;
				}
				break;

			case 4:
				if (currentChar == '+') {
					state = printLexeme("Increase", String.valueOf(lexeme + currentChar), 0, dlm);

					lexeme = "";
					flag = true;
				} else {

					state = printLexeme("AO", String.valueOf(lexeme), 0, dlm);
					lexeme = "";
					continue;
				}
				break;
			case 5:
				if (currentChar == '-') {
					state = printLexeme("Decrease", String.valueOf(lexeme + currentChar), 0, dlm);
					lexeme = "";
					flag = true;
				} else {
					state = printLexeme("AO", String.valueOf(lexeme), 0, dlm);
					lexeme = "";
					continue;
				}
				break;
			case 6:
				if (currentChar == '=') {
					state = printLexeme("Equal", String.valueOf(lexeme + currentChar), 0, dlm);
					lexeme = "";
					flag = true;
				} else {
					state = printLexeme("AS", String.valueOf(lexeme), 0, dlm);
					lexeme = "";
					continue;
				}
				break;
			case 7:
				if (currentChar == '/') {
					state = 8;
				} else if (currentChar == '*') {
					state = 11;
				} else {
					state = printLexeme("AO", lexeme, 0, dlm);
					lexeme = "";
					continue;
				}
				break;

			case 8:
				if (currentChar == '\n') {
					state = printLexeme("SingleComment", lexeme, 0, dlm);
					lexeme = "";
					flag = true;
				}
				break;
			case 9:
				if (isLetter(currentChar)) {
					state = 10;
				} else {
					state = printLexeme("Invalid", lexeme, 0, dlm);
					lexeme = "";
					continue;
				}
				break;

			case 10:
				if (!(isLetter(currentChar) || isDigit(currentChar))) {
					state = 0;
					printLexeme("ID", String.valueOf(lexeme), 0, dlm);
					lexeme = "";
					continue;
				}
				break;

			case 11:
				if (currentChar == '*') {
					state = 12;
				}
				break;
			case 12:
				if (currentChar == '/') {
					state = printLexeme("Multi Comment", null, 0, dlm);
					lexeme = "";
					flag = true;
				}
			}
			if (!(flag)) {
				lexeme = lexeme + currentChar;
			}
			i++;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 667, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Input your code: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 39, 107, 23);
		frame.getContentPane().add(lblNewLabel);
		DefaultListModel<String> dlm = new DefaultListModel<String>();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(392, 23, 203, 262);
		frame.getContentPane().add(scrollPane);
		JList list = new JList(dlm);
		list.setFont(new Font("Arial", Font.PLAIN, 15));
		scrollPane.setViewportView(list);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		textArea.setBounds(128, 23, 254, 103);
		frame.getContentPane().add(textArea);

		JButton btnNewButton = new JButton("Generate Tokkens");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlm.clear();
				String input = textArea.getText();
				lexicalAnalyzer(input, dlm);
			}
		});
		btnNewButton.setBounds(178, 155, 167, 64);
		frame.getContentPane().add(btnNewButton);

	}
}
