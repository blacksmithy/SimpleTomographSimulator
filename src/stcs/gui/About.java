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
		sb.append("Witaj w prostym symulatorze CT!\n\nProgram ten pozwala na graficzne por�wnanie");
		sb.append(" \"oryginalnego\" obrazu \nz przybli�onym obrazem wygenerowanym przez CT.");
		sb.append(" Przybli�enie odbywa si� poprzez dzia�anie prostego algorytmu iteracyjnego. ");
		sb.append("Ze wzgl�du na analiz� danych wej�ciowych (sum z wierszy/kolumn) tylko z 2 wymiar�w");
		sb.append(" mo�na �atwo zaobserwowa� wady takiego podej�cia.\n\n");
		sb.append("Ustawianie r�czne obszaru wejsciowego: \nlewy przycisk myszy - ja�niej, ");
		sb.append("prawy przycisk myszy - ciemniej.\n");
		sb.append("Program wywo�any z konsoli dodatkowo wy�wietla warto�ci poszczeg�lnych p�l ");
		sb.append("obraz�w: oryginalnego i wygenerowanego.\n\n");
		sb.append("Autorzy: Micha� El Fartas & Micha� Kowalski\n");
		sb.append("Pozna�, 2013");
		textPane_1.setText(sb.toString());
	}

}
