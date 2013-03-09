package stcs.gui;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class About extends JDialog {

	private static final long serialVersionUID = 9165708892492673345L;

	private final JPanel contentPanel = new JPanel();
	private JTextPane textPane_1;

	/**
	 * Create the dialog.
	 */
	public About() {
		setType(Type.POPUP);
		setBounds(100, 100, 450, 319);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textPane_1 = new JTextPane();
			textPane_1.setBackground(UIManager
					.getColor("ComboBox.buttonBackground"));
			textPane_1.setEditable(false);
			textPane_1.setBounds(10, 11, 414, 258);
			contentPanel.add(textPane_1);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("Witaj w prostym symulatorze CT!\n\nProgram ten pozwala na graficzne porównanie");
		sb.append(" \"oryginalnego\" obrazu \nz przybli¿onym obrazem wygenerowanym przez CT.");
		sb.append(" Przybli¿enie odbywa siê poprzez dzia³anie prostego algorytmu iteracyjnego. ");
		sb.append("Ze wzglêdu na analizê danych wejœciowych (sum z wierszy/kolumn) tylko z 2 wymiarów");
		sb.append(" mo¿na ³atwo zaobserwowaæ wady takiego podejœcia.\n\n");
		sb.append("Ustawianie rêczne obszaru wejsciowego: \nlewy przycisk myszy - jaœniej, ");
		sb.append("prawy przycisk myszy - ciemniej.\n");
		sb.append("Program wywo³any z konsoli dodatkowo wyœwietla wartoœci poszczególnych pól ");
		sb.append("obrazów: oryginalnego i wygenerowanego.\n\n");
		sb.append("Autorzy: Micha³ El Fartas & Micha³ Kowalski\n");
		sb.append("Poznañ, 2013");
		textPane_1.setText(sb.toString());
	}

}
