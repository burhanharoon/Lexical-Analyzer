import java.awt.*;
// import java.net.*;
import java.awt.event.*;

public class analyzer extends Frame {
	Label statement;
	TextField tf1;
	TextField tf2;
	Label l1;
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
				// int a = Integer.parseInt(tf1.getText());
				String c = "Hello World";
				l1.setText("Their sum is = " + String.valueOf(c));
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
	}
}