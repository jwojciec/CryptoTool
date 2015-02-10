package DefaultGUI;

import CryptoToolAlgo.*;
import Conversion.*;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

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
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
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

		AesAlgo aes_algo = new AesAlgo();
		DesAlgo des_algo = new DesAlgo();
		TripleDesAlgo tdes_algo = new TripleDesAlgo();
		TripleDesAlgoCBC tdes_algoCbc = new TripleDesAlgoCBC();
		Conversion conv = new Conversion();

		setTitle("CryptoTool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 189);
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
				lblKeyLen.setText(Integer.toString(textFieldKey.getText()
						.length()));
			}
		});
		textFieldKey.setBounds(62, 11, 227, 20);
		contentPane.add(textFieldKey);
		textFieldKey.setColumns(10);

		textFieldValue = new JTextField();
		textFieldValue.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				lblValueLen.setText(Integer.toString(textFieldValue.getText()
						.length()));
			}
		});
		textFieldValue.setBounds(62, 36, 227, 20);
		contentPane.add(textFieldValue);
		textFieldValue.setColumns(10);

		textFieldResult = new JTextField();
		textFieldResult.setBounds(62, 61, 227, 20);
		contentPane.add(textFieldResult);
		textFieldResult.setColumns(10);

		lblKeyLen = new JLabel("Len");
		lblKeyLen.setBounds(299, 14, 27, 14);
		contentPane.add(lblKeyLen);

		lblValueLen = new JLabel("Len");
		lblValueLen.setBounds(299, 39, 27, 14);
		contentPane.add(lblValueLen);

		lblResultLen = new JLabel("Len");
		lblResultLen.setBounds(299, 64, 46, 14);
		contentPane.add(lblResultLen);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 119, 117, 20);
		comboBox.addItem("");
		comboBox.addItem("AES");
		comboBox.addItem("DES");
		comboBox.addItem("3DES CBC");
		comboBox.addItem("3DES ECB");
		contentPane.add(comboBox);

		btnKeyRandom = new JButton("Random");
		btnKeyRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String chosen_algo = comboBox.getSelectedItem().toString();
				switch (chosen_algo) {
				case "AES": textFieldKey.setText(conv.getRandomHexString(64)); break;
				case "DES": textFieldKey.setText(conv.getRandomHexString(16)); break;
				case "3DES CBC": textFieldKey.setText(conv.getRandomHexString(32)); break;
				case "3DES ECB": textFieldKey.setText(conv.getRandomHexString(32)); break;
				}
				lblKeyLen.setText(Integer.toString(textFieldKey.getText()
						.length()));
			}
		});
		btnKeyRandom.setBounds(336, 11, 88, 20);
		contentPane.add(btnKeyRandom);

		btnNewButton = new JButton("Random");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String chosen_algo = comboBox.getSelectedItem().toString();
				switch (chosen_algo) {
				case "AES": textFieldValue.setText(conv.getRandomHexString(64)); break;
				case "DES": textFieldValue.setText(conv.getRandomHexString(16)); break;
				case "3DES CBC": textFieldValue.setText(conv.getRandomHexString(32)); break;
				case "3DES ECB": textFieldValue.setText(conv.getRandomHexString(32)); break;
				}
				lblValueLen.setText(Integer.toString(textFieldValue.getText()
						.length()));
			}
		});
		btnNewButton.setBounds(336, 36, 88, 20);
		contentPane.add(btnNewButton);

		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Encrypt
				textFieldResult.setForeground(Color.BLACK);
				String chosen_algo = comboBox.getSelectedItem().toString();
				try {
					switch (chosen_algo) {
					case "AES":
						textFieldResult.setText(aes_algo.encrypt(
								textFieldKey.getText(),
								textFieldValue.getText()));
						break;
					case "DES":
						textFieldResult.setText(des_algo.encrypt(
								textFieldKey.getText(),
								textFieldValue.getText()));
						break;
					case "3DES CBC":
						textFieldResult.setText(tdes_algoCbc.encrypt(
								textFieldKey.getText(),
								textFieldValue.getText()));
						break;
					case "3DES ECB":
						textFieldResult.setText(tdes_algo.encrypt(
								textFieldKey.getText(),
								textFieldValue.getText()));
						break;
					}
				} catch (Exception cipher_ex) {
					System.out.println("Error: " + cipher_ex);
					textFieldResult.setForeground(Color.RED);
					textFieldResult.setText("Error: " + cipher_ex);
				}
				lblResultLen.setText(Integer.toString(textFieldResult.getText()
						.length()));
			}
		});
		btnEncrypt.setBounds(140, 119, 88, 20);
		contentPane.add(btnEncrypt);

		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Decrypt
				textFieldResult.setForeground(Color.BLACK);
				String chosen_algo = comboBox.getSelectedItem().toString();
				try {
					switch (chosen_algo) {
					case "AES":
						textFieldResult.setText(aes_algo.decrypt(
								textFieldKey.getText(),
								textFieldValue.getText()));
						break;
					case "DES":
						textFieldResult.setText(des_algo.decrypt(
								textFieldKey.getText(),
								textFieldValue.getText()));
						break;
					case "3DES CBC":
						textFieldResult.setText(tdes_algoCbc.decrypt(
								textFieldKey.getText(),
								textFieldValue.getText()));
						break;
					case "3DES ECB":
						textFieldResult.setText(tdes_algo.decrypt(
								textFieldKey.getText(),
								textFieldValue.getText()));
						break;
					}
				} catch (Exception cipher_ex) {
					System.out.println("Error: " + cipher_ex);
					textFieldResult.setForeground(Color.RED);
					textFieldResult.setText("Error: " + cipher_ex);
				}
				lblResultLen.setText(Integer.toString(textFieldResult.getText()
						.length()));
			}
		});
		btnDecrypt.setBounds(238, 119, 88, 20);
		contentPane.add(btnDecrypt);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
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
		btnClear.setBounds(336, 119, 88, 20);
		contentPane.add(btnClear);
	}
}
