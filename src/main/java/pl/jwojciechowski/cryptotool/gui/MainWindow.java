package pl.jwojciechowski.cryptotool.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import pl.jwojciechowski.cryptotool.cryptoalgos.CryptoAlgorithm;
import pl.jwojciechowski.cryptotool.randomgenerator.RandomNumbersGenerator;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldKey;
	private JTextField textFieldValue;
	private JTextField textFieldResult;
	private JLabel lblKeyLen;
	private JLabel lblValueLen;
	private JLabel lblResultLen;
	private JButton btnKeyRandom;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		RandomNumbersGenerator randomNumbers = new RandomNumbersGenerator();
		CryptoAlgorithm cryptoAlgorithm = new CryptoAlgorithm();

		setTitle("CryptoTool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblKey = new JLabel("Key:");
		lblKey.setBounds(10, 14, 46, 14);
		contentPane.add(lblKey);

		JLabel lblValue = new JLabel("Value:");
		lblValue.setBounds(10, 39, 46, 14);
		contentPane.add(lblValue);

		JLabel lblResult = new JLabel("Result:");
		lblResult.setBounds(10, 64, 46, 14);
		contentPane.add(lblResult);

		textFieldKey = new JTextField();
		textFieldKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				lblKeyLen.setText(Integer.toString(textFieldKey.getText().length()));
			}
		});
		textFieldKey.setBounds(62, 11, 425, 20);
		contentPane.add(textFieldKey);
		textFieldKey.setColumns(10);

		textFieldValue = new JTextField();
		textFieldValue.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				lblValueLen.setText(Integer.toString(textFieldValue.getText().length()));
			}
		});
		textFieldValue.setBounds(62, 36, 425, 20);
		contentPane.add(textFieldValue);
		textFieldValue.setColumns(10);

		textFieldResult = new JTextField();
		textFieldResult.setBounds(62, 61, 425, 20);
		contentPane.add(textFieldResult);
		textFieldResult.setColumns(10);

		lblKeyLen = new JLabel("Len");
		lblKeyLen.setBounds(497, 14, 27, 14);
		contentPane.add(lblKeyLen);

		lblValueLen = new JLabel("Len");
		lblValueLen.setBounds(497, 39, 27, 14);
		contentPane.add(lblValueLen);

		lblResultLen = new JLabel("Len");
		lblResultLen.setBounds(497, 64, 27, 14);
		contentPane.add(lblResultLen);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(10, 119, 117, 20);
		comboBox.addItem("AES");
		comboBox.addItem("DES");
		comboBox.addItem("TripleDES");
		contentPane.add(comboBox);

		JRadioButton rdbtnECB = new JRadioButton("ECB");
		rdbtnECB.setSelected(true);
		rdbtnECB.setBounds(133, 118, 55, 23);
		contentPane.add(rdbtnECB);

		JRadioButton rdbtnCBC = new JRadioButton("CBC");
		rdbtnCBC.setBounds(190, 118, 55, 23);
		contentPane.add(rdbtnCBC);

		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnECB);
		radioGroup.add(rdbtnCBC);

		btnKeyRandom = new JButton("Random");
		btnKeyRandom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String chosen_algo = comboBox.getSelectedItem().toString();
				switch (chosen_algo) {
				case "AES":
					textFieldKey.setText(randomNumbers.generateHex(64));
					break;
				case "DES":
					textFieldKey.setText(randomNumbers.generateHex(16));
					break;
				case "TripleDES":
					textFieldKey.setText(randomNumbers.generateHex(32));
					break;
				}
				lblKeyLen.setText(Integer.toString(textFieldKey.getText().length()));
			}
		});
		btnKeyRandom.setBounds(534, 11, 88, 20);
		contentPane.add(btnKeyRandom);

		btnNewButton = new JButton("Random");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String chosen_algo = comboBox.getSelectedItem().toString();
				switch (chosen_algo) {
				case "AES":
					textFieldValue.setText(randomNumbers.generateHex(64));
					break;
				case "DES":
					textFieldValue.setText(randomNumbers.generateHex(16));
					break;
				case "TripleDES":
					textFieldValue.setText(randomNumbers.generateHex(32));
					break;
				}
				lblValueLen.setText(Integer.toString(textFieldValue.getText().length()));
			}
		});
		btnNewButton.setBounds(534, 36, 88, 20);
		contentPane.add(btnNewButton);

		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Encrypt
				textFieldResult.setForeground(Color.BLACK);
				String chosen_algo = comboBox.getSelectedItem().toString();
				String chosen_mode = "";
				if (rdbtnECB.isSelected())
					chosen_mode = "ECB";
				else if (rdbtnCBC.isSelected())
					chosen_mode = "CBC";

				try {
					cryptoAlgorithm.setKeyHex(textFieldKey.getText());
					cryptoAlgorithm.setPlaintextHex(textFieldValue.getText());
					cryptoAlgorithm.setEcbCbcMode(chosen_mode);
					cryptoAlgorithm.setAlgo_mode(chosen_algo);
					textFieldResult.setText(cryptoAlgorithm.encrypt());

				} catch (Exception cipher_ex) {
					System.out.println("Error: " + cipher_ex);
					textFieldResult.setForeground(Color.RED);
					textFieldResult.setText("Error: " + cipher_ex);
				}
				lblResultLen.setText(Integer.toString(textFieldResult.getText().length()));
			}
		});
		btnEncrypt.setBounds(338, 119, 88, 20);
		contentPane.add(btnEncrypt);

		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Decrypt
				textFieldResult.setForeground(Color.BLACK);
				String chosen_algo = comboBox.getSelectedItem().toString();
				String chosen_mode = "";
				if (rdbtnECB.isSelected())
					chosen_mode = "ECB";
				else if (rdbtnCBC.isSelected())
					chosen_mode = "CBC";

				try {
					cryptoAlgorithm.setKeyHex(textFieldKey.getText());
					cryptoAlgorithm.setPlaintextHex(textFieldValue.getText());
					cryptoAlgorithm.setEcbCbcMode(chosen_mode);
					cryptoAlgorithm.setAlgo_mode(chosen_algo);
					textFieldResult.setText(cryptoAlgorithm.decrypt());

				} catch (Exception cipher_ex) {
					System.out.println("Error: " + cipher_ex);
					textFieldResult.setForeground(Color.RED);
					textFieldResult.setText("Error: " + cipher_ex);
				}
				lblResultLen.setText(Integer.toString(textFieldResult.getText().length()));
			}
		});
		btnDecrypt.setBounds(436, 119, 88, 20);
		contentPane.add(btnDecrypt);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Clear all fields
				textFieldKey.setText("");
				textFieldValue.setText("");
				textFieldResult.setText("");
				comboBox.setSelectedIndex(0);

				// Clear lengths:
				lblKeyLen.setText("Len");
				lblValueLen.setText("Len");
				lblResultLen.setText("Len");
			}
		});
		btnClear.setBounds(534, 119, 88, 20);
		contentPane.add(btnClear);
	}
}
