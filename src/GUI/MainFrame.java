package GUI;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.crypto.NoSuchPaddingException;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.SwingWorker;
import javax.swing.JFileChooser;
import java.awt.Toolkit;
import javax.swing.JProgressBar;


public class MainFrame extends javax.swing.JFrame{
	private JFrame frmCrytography;
	private JTextField inputFileTextField;
	private JTextField outputFileTextField;
	private JLabel lblOpenFileTo;
	private JLabel lblDecryptedFileDirectory;
	private JLabel lblChoseCryptographyFor;
	private JTextField keyTextField;
	private JButton btnFindKey;
	private JLabel lblChoseDirectoryOf;
	private JButton btnStart;
	private JRadioButton rdbtnEncryptDESwithECB;
	private JRadioButton rdbtnEncryptDESwithCBC;
	private JRadioButton rdbtnDecryptDESwithCBC;
	private JRadioButton rdbtnDecryptDESwithECB;
	private JLabel lblState;
	private JRadioButton rdbtnDecryptAES;
	private JRadioButton rdbtnEncryptAES;
	public static JProgressBar progressBar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmCrytography.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	class CryptographyAlgorithm extends SwingWorker<Void, Void>{

		@Override
		protected Void doInBackground() throws Exception {
			lblState.setText("");
			progressBar.setValue(0);
			String inputPath, outputPath, keyPath;
			
			inputPath = inputFileTextField.getText();
			outputPath = outputFileTextField.getText();
			keyPath = keyTextField.getText();
			
			if(rdbtnEncryptDESwithECB.isSelected() && (inputPath != null) && (outputPath != null) && (keyPath != null)) {
				File input = new File(inputPath);
				File output = new File(outputPath);
				try {
					System.out.print("processing...");
					lblState.setText("processing...");
					Thread.sleep(1000);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					desAlgorithm.ECBencrypt.desECBencrypt(keyPath, input, output);
				} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException
						| NoSuchPaddingException | IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if (rdbtnEncryptDESwithCBC.isSelected() && (inputPath != null) && (outputPath != null) && (keyPath != null)) {
				File input = new File(inputPath);
				File output = new File(outputPath);
				lblState.setText("processing...");
				try {
					desAlgorithm.CBCencrypt.desCBCencrypt(keyPath, input, output);
				} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException
						| NoSuchPaddingException | InvalidAlgorithmParameterException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			else if (rdbtnDecryptDESwithCBC.isSelected() && (inputPath != null) && (outputPath != null) && (keyPath != null)) {
				File input = new File(inputPath);
				File output = new File(outputPath);
				lblState.setText("processing...");
				try {
					desAlgorithm.CBCdecrypt.desCBCdecrypt(keyPath, input, output);
				} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException
						| NoSuchPaddingException | InvalidAlgorithmParameterException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			else if (rdbtnDecryptDESwithECB.isSelected() && (inputPath != null) && (outputPath != null) && (keyPath != null)) {
				File input = new File(inputPath);
				File output = new File(outputPath);
				lblState.setText("processing...");
				try {
					desAlgorithm.ECBdecrypt.desECBdecrypt(keyPath, input, output);
				} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException
						| NoSuchPaddingException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if (rdbtnEncryptAES.isSelected() && (inputPath != null) && (outputPath != null) && (keyPath != null)) {
				File input = new File(inputPath);
				File output = new File(outputPath);
				lblState.setText("processing...");
				try {
					aesAlgorithm.AESEncrypt.desECBencrypt(keyPath, input, output);
				} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException
						| NoSuchPaddingException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if (rdbtnDecryptAES.isSelected() && (inputPath != null) && (outputPath != null) && (keyPath != null)) {
				File input = new File(inputPath);
				File output = new File(outputPath);
				lblState.setText("processing...");
				try {
					aesAlgorithm.AESDecrypt.desECBencrypt(keyPath, input, output);
				} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException
						| NoSuchPaddingException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return null;
		}
		
		@Override
		protected void done() {
			if(rdbtnEncryptDESwithECB.isSelected()) {
				lblState.setText(desAlgorithm.ECBencrypt.success);
			}
			else if(rdbtnEncryptDESwithCBC.isSelected()) {
				lblState.setText(desAlgorithm.CBCencrypt.success);
			}
			else if(rdbtnDecryptDESwithCBC.isSelected()) {
				lblState.setText(desAlgorithm.CBCdecrypt.success);
			}
			else if(rdbtnDecryptDESwithECB.isSelected()) {
				lblState.setText(desAlgorithm.ECBdecrypt.success);
			}
			else if(rdbtnEncryptAES.isSelected()) {
				lblState.setText(aesAlgorithm.AESEncrypt.success);
			}
			else if(rdbtnDecryptAES.isSelected()) {
				lblState.setText(aesAlgorithm.AESDecrypt.success);
			}
		}
	}
	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrytography = new JFrame();
		frmCrytography.setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/com/sun/javafx/scene/web/skin/Copy_16x16_JFX.png")));
		frmCrytography.setTitle("Cryptography v1.0");
		frmCrytography.setResizable(false);
		frmCrytography.getContentPane().setBackground(new Color(0, 0, 0));
		frmCrytography.setBounds(100, 100, 583, 510);
		frmCrytography.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrytography.getContentPane().setLayout(null);
		
		JLabel lblDesEncrypttion = new JLabel("DES-AES ENCRYPTTION - DECRYPTION v1.0");
		lblDesEncrypttion.setForeground(Color.MAGENTA);
		lblDesEncrypttion.setFont(new Font("Tekton Pro", Font.BOLD | Font.ITALIC, 20));
		lblDesEncrypttion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesEncrypttion.setBounds(77, 11, 423, 38);
		frmCrytography.getContentPane().add(lblDesEncrypttion);
		
		inputFileTextField = new JTextField();
		inputFileTextField.setBounds(31, 88, 440, 20);
		frmCrytography.getContentPane().add(inputFileTextField);
		inputFileTextField.setColumns(10);
		
		JButton btnFindInputFile = new JButton("Find");
		btnFindInputFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Select Input File");

				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					inputFileTextField.setText(chooser.getSelectedFile().toString());
				}
			}
		});
		btnFindInputFile.setBounds(485, 87, 59, 23);
		frmCrytography.getContentPane().add(btnFindInputFile);
		
		outputFileTextField = new JTextField();
		outputFileTextField.setColumns(10);
		outputFileTextField.setBounds(31, 146, 440, 20);
		frmCrytography.getContentPane().add(outputFileTextField);
		
		JButton btnFindOutputFile = new JButton("Find");
		btnFindOutputFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Select Input File");

				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					outputFileTextField.setText(chooser.getSelectedFile().toString());
				}
			}
		});
		btnFindOutputFile.setBounds(485, 145, 59, 23);
		frmCrytography.getContentPane().add(btnFindOutputFile);
		
		lblOpenFileTo = new JLabel("Chose Directory Of  Input File");
		lblOpenFileTo.setIcon(new ImageIcon(MainFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		lblOpenFileTo.setFont(new Font("Tekton Pro", Font.BOLD, 16));
		lblOpenFileTo.setForeground(new Color(0, 204, 0));
		lblOpenFileTo.setBackground(new Color(0, 0, 0));
		lblOpenFileTo.setBounds(31, 47, 337, 61);
		frmCrytography.getContentPane().add(lblOpenFileTo);
		
		lblDecryptedFileDirectory = new JLabel("Chose Directory of Output File");
		lblDecryptedFileDirectory.setIcon(new ImageIcon(MainFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		lblDecryptedFileDirectory.setForeground(new Color(0, 204, 0));
		lblDecryptedFileDirectory.setFont(new Font("Tekton Pro", Font.BOLD, 16));
		lblDecryptedFileDirectory.setBackground(Color.BLACK);
		lblDecryptedFileDirectory.setBounds(31, 106, 364, 61);
		frmCrytography.getContentPane().add(lblDecryptedFileDirectory);
		
		lblChoseCryptographyFor = new JLabel("Chose Cryptography Algoritm for Encrypt - Decrypt ");
		lblChoseCryptographyFor.setIcon(new ImageIcon(MainFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/menu.gif")));
		lblChoseCryptographyFor.setForeground(new Color(255, 102, 0));
		lblChoseCryptographyFor.setFont(new Font("Tekton Pro", Font.BOLD, 16));
		lblChoseCryptographyFor.setBackground(Color.BLACK);
		lblChoseCryptographyFor.setBounds(31, 226, 364, 61);
		frmCrytography.getContentPane().add(lblChoseCryptographyFor);
		
		keyTextField = new JTextField();
		keyTextField.setColumns(10);
		keyTextField.setBounds(31, 203, 440, 20);
		frmCrytography.getContentPane().add(keyTextField);
		
		btnFindKey = new JButton("Find");
		btnFindKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Select Input File");

				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					keyTextField.setText(chooser.getSelectedFile().toString());
				}
			}
		});
		btnFindKey.setBounds(485, 202, 59, 23);
		frmCrytography.getContentPane().add(btnFindKey);
		
		lblChoseDirectoryOf = new JLabel("Chose Directory of  EncryptKey / DecryptKey (text File)");
		lblChoseDirectoryOf.setIcon(new ImageIcon(MainFrame.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Paste-Yellow.png")));
		lblChoseDirectoryOf.setForeground(new Color(0, 204, 0));
		lblChoseDirectoryOf.setFont(new Font("Tekton Pro", Font.BOLD, 16));
		lblChoseDirectoryOf.setBackground(Color.BLACK);
		lblChoseDirectoryOf.setBounds(31, 163, 440, 61);
		frmCrytography.getContentPane().add(lblChoseDirectoryOf);
		
		btnStart = new JButton("START");
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnStart.setBackground(new Color(51, 204, 102));
		btnStart.setIcon(new ImageIcon(MainFrame.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {		
				CryptographyAlgorithm action = new CryptographyAlgorithm();
				action.execute();
			}
		});	
		
		btnStart.setBounds(31, 419, 149, 41);
		frmCrytography.getContentPane().add(btnStart);
		
		JLabel lblEncryptAlogrithm = new JLabel("Encrypt Algorithm");
		lblEncryptAlogrithm.setBounds(74, 280, 137, 17);
		frmCrytography.getContentPane().add(lblEncryptAlogrithm);
		lblEncryptAlogrithm.setForeground(Color.ORANGE);
		lblEncryptAlogrithm.setFont(new Font("Tekton Pro", Font.BOLD | Font.ITALIC, 18));
		
		JLabel lblDecryptAlogrithm = new JLabel("Decrypt Algorithm");
		lblDecryptAlogrithm.setBounds(360, 280, 149, 17);
		frmCrytography.getContentPane().add(lblDecryptAlogrithm);
		lblDecryptAlogrithm.setForeground(Color.ORANGE);
		lblDecryptAlogrithm.setFont(new Font("Tekton Pro", Font.BOLD | Font.ITALIC, 18));
		
		rdbtnEncryptDESwithECB = new JRadioButton("DES with ECB");
		rdbtnEncryptDESwithECB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnEncryptDESwithECB.isSelected()) {
					rdbtnEncryptDESwithCBC.setSelected(false);
					rdbtnDecryptDESwithCBC.setSelected(false);
					rdbtnDecryptDESwithECB.setSelected(false);
					rdbtnEncryptAES.setSelected(false);
					rdbtnDecryptAES.setSelected(false);
				}
			}
		});
		rdbtnEncryptDESwithECB.setBackground(new Color(0, 0, 0));
		rdbtnEncryptDESwithECB.setFont(new Font("Tekton Pro", Font.BOLD | Font.ITALIC, 16));
		rdbtnEncryptDESwithECB.setForeground(new Color(0, 102, 255));
		rdbtnEncryptDESwithECB.setBounds(74, 304, 180, 23);
		frmCrytography.getContentPane().add(rdbtnEncryptDESwithECB);
		
		rdbtnEncryptDESwithCBC = new JRadioButton("DES with CBC");
		rdbtnEncryptDESwithCBC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnEncryptDESwithCBC.isSelected()) {
					rdbtnEncryptDESwithECB.setSelected(false);
					rdbtnDecryptDESwithCBC.setSelected(false);
					rdbtnDecryptDESwithECB.setSelected(false);
					rdbtnEncryptAES.setSelected(false);
					rdbtnDecryptAES.setSelected(false);
				}
			}
		});
		rdbtnEncryptDESwithCBC.setForeground(new Color(0, 102, 255));
		rdbtnEncryptDESwithCBC.setFont(new Font("Tekton Pro", Font.BOLD | Font.ITALIC, 16));
		rdbtnEncryptDESwithCBC.setBackground(Color.BLACK);
		rdbtnEncryptDESwithCBC.setBounds(74, 330, 180, 23);
		frmCrytography.getContentPane().add(rdbtnEncryptDESwithCBC);
		
		rdbtnDecryptDESwithCBC = new JRadioButton("DES with CBC");
		rdbtnDecryptDESwithCBC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnDecryptDESwithCBC.isSelected()) {
					rdbtnEncryptDESwithCBC.setSelected(false);
					rdbtnEncryptDESwithECB.setSelected(false);
					rdbtnDecryptDESwithECB.setSelected(false);
					rdbtnEncryptAES.setSelected(false);
					rdbtnDecryptAES.setSelected(false);
				}
			}
		});
		rdbtnDecryptDESwithCBC.setForeground(new Color(0, 102, 255));
		rdbtnDecryptDESwithCBC.setFont(new Font("Tekton Pro", Font.BOLD | Font.ITALIC, 16));
		rdbtnDecryptDESwithCBC.setBackground(Color.BLACK);
		rdbtnDecryptDESwithCBC.setBounds(360, 330, 180, 23);
		frmCrytography.getContentPane().add(rdbtnDecryptDESwithCBC);
		
		rdbtnDecryptDESwithECB = new JRadioButton("DES with ECB");
		rdbtnDecryptDESwithECB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnDecryptDESwithECB.isSelected()) {
					rdbtnEncryptDESwithCBC.setSelected(false);
					rdbtnEncryptDESwithECB.setSelected(false);
					rdbtnDecryptDESwithCBC.setSelected(false);
					rdbtnEncryptAES.setSelected(false);
					rdbtnDecryptAES.setSelected(false);
				}
			}
		});
		rdbtnDecryptDESwithECB.setForeground(new Color(0, 102, 255));
		rdbtnDecryptDESwithECB.setFont(new Font("Tekton Pro", Font.BOLD | Font.ITALIC, 16));
		rdbtnDecryptDESwithECB.setBackground(Color.BLACK);
		rdbtnDecryptDESwithECB.setBounds(360, 304, 180, 23);
		frmCrytography.getContentPane().add(rdbtnDecryptDESwithECB);
		
		lblState = new JLabel("");
		lblState.setForeground(Color.RED);
		lblState.setFont(new Font("Tekton Pro", Font.BOLD, 16));
		lblState.setBackground(Color.BLACK);
		lblState.setBounds(218, 400, 252, 17);
		frmCrytography.getContentPane().add(lblState);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setForeground(Color.GREEN);
		progressBar.setBounds(218, 419, 326, 41);
		frmCrytography.getContentPane().add(progressBar);
		
		rdbtnEncryptAES = new JRadioButton("AES with ECB");
		rdbtnEncryptAES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnEncryptAES.isSelected()) {
					rdbtnDecryptDESwithECB.setSelected(false);
					rdbtnEncryptDESwithCBC.setSelected(false);
					rdbtnEncryptDESwithECB.setSelected(false);
					rdbtnDecryptDESwithCBC.setSelected(false);
					rdbtnDecryptAES.setSelected(false);
				}
			}
		});
		rdbtnEncryptAES.setForeground(new Color(0, 102, 255));
		rdbtnEncryptAES.setFont(new Font("Tekton Pro", Font.BOLD | Font.ITALIC, 16));
		rdbtnEncryptAES.setBackground(Color.BLACK);
		rdbtnEncryptAES.setBounds(74, 356, 180, 23);
		frmCrytography.getContentPane().add(rdbtnEncryptAES);
		
		rdbtnDecryptAES = new JRadioButton("AES with ECB");
		rdbtnDecryptAES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnDecryptAES.isSelected()) {
					rdbtnDecryptDESwithECB.setSelected(false);
					rdbtnEncryptDESwithCBC.setSelected(false);
					rdbtnEncryptDESwithECB.setSelected(false);
					rdbtnDecryptDESwithCBC.setSelected(false);
					rdbtnEncryptAES.setSelected(false);
				}
			}
		});
		rdbtnDecryptAES.setForeground(new Color(0, 102, 255));
		rdbtnDecryptAES.setFont(new Font("Tekton Pro", Font.BOLD | Font.ITALIC, 16));
		rdbtnDecryptAES.setBackground(Color.BLACK);
		rdbtnDecryptAES.setBounds(360, 356, 180, 23);
		frmCrytography.getContentPane().add(rdbtnDecryptAES);
		
	}
}
