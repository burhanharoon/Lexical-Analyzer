import java.awt.*;
import java.util.ArrayList; // import the ArrayList class
import javax.swing.JTextArea;
import java.awt.event.*;
import javax.swing.JList;
import javax.swing.JDesktopPane;

public class analyzer extends Frame {
	Label statement;
	TextField tf1;
	TextField tf2;
	static Label l1;
	Button b;
	JTextArea text;
	static ArrayList<String> tokkenList = new ArrayList<String>(); // Create an ArrayList object
	String gg = "";

	analyzer() {
		setTitle("Adder");
		statement = new Label("Enter string: ");
		statement.setBounds(10, 50, 70, 20);
		tf1 = new TextField();
		tf1.setBounds(100, 50, 85, 20);
		b = new Button("YES");
		b.setBounds(110, 220, 60, 40);
		// l1 = new Label("");
		// l1.setBounds(100, 120, 85, 20);
		text = new JTextArea(50, 20);
		text.setBounds(110, 220, 60, 40);
		add(statement);
		add(b);
		add(tf1);
		// add(l1);
		add(text);
		setSize(300, 300);
		setVisible(true);
		boolean flag = true;
		b.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String a = tf1.getText();
				// l1.setText("Their sum is = " + myMethod(a));
				myMethod(a);
				int size = tokkenList.size();

				for (int i = 0; i < size; i++) {
					text.setText(text.getText() + tokkenList.get(i));
				}
			}

		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		new analyzer();
		// myMethod();
	}

	private static boolean isKeyword(String ch) {
		if (ch == "int" || ch == "float")
			return true;
		else
			return false;
	}

	private static boolean isLetter(char ch) {
		if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
			return true;
		else
			return false;
	}

	private static boolean isDegit(char ch) {
		if (ch >= '0' && ch <= '9')
			return true;
		else
			return false;
	}

	private static void myMethod(String input) {

		int state = 0;
		int i = 0;
		String lexeme = "";
		boolean flag = true;
		input += "\0"; // Becuase Java doesn't hava a End of line special char as C
		char currentChar = '\0';

		while (input.charAt(i) != '\0') {
			currentChar = input.charAt(i);
			// System.out.println(currentChar);
			// if (isLetter(currentChar)) {
			// l1.setText(String.valueOf(currentChar));
			// } else {
			// System.out.println("No");
			// }
			switch (state) {
				case 0:
					if (isLetter(currentChar)) {
						state = 1;
					}
					break;

				case 1:
					if (!(isLetter(currentChar) || isDegit(currentChar))) {
						state = 0;
						if (isKeyword(lexeme)) {
							tokkenList.add("Tokken < Keyword, " + String.valueOf(lexeme) + ">");
						} else {
							tokkenList.add("Tokken < Identifier, " + String.valueOf(lexeme) + ">");
						}
						lexeme = "";
					}
					break;
			}
			if (flag)
				lexeme = lexeme + currentChar;
			i++;
		}

	}

}