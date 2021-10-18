import java.awt.*;
// import java.net.*;
import java.awt.event.*;
// import java.lang.ProcessBuilder.Redirect.Type;

public class analyzer extends Frame {
	Label statement;
	TextField tf1;
	TextField tf2;
	static Label l1;
	Button b;

	analyzer() {
		setTitle("Adder");
		statement = new Label("Enter string: ");
		statement.setBounds(10, 50, 70, 20);
		tf1 = new TextField();
		tf1.setBounds(100, 50, 85, 20);
		b = new Button("YES");
		b.setBounds(110, 220, 60, 40);
		l1 = new Label("");
		l1.setBounds(100, 120, 85, 20);
		add(statement);
		add(b);
		add(tf1);
		add(l1);
		setSize(300, 300);
		setVisible(true);

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = tf1.getText();
				// l1.setText("Their sum is = " + myMethod(a));
				myMethod(a);

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

	private static void myMethod(String input) {
		int state = 0;
		int i = 0;
		String lexeme = "";
		boolean flag = true;
		input += "/0";
		char c = '\0';

		while (input.charAt(i) != '\0') {
			switch (state) {
				case 0:

					break;

				case 1:

					break;
			}
			if (flag)
				lexeme = lexeme + c;

			i++;
		}

	}
}