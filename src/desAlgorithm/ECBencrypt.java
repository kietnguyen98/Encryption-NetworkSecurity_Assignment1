package desAlgorithm;
import GUI.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESKeySpec;
import org.apache.commons.io.IOUtils;


public class ECBencrypt {
	public static String success;
	public static void desECBencrypt(String key, File input, File output) 
	throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IOException, InterruptedException
	{
		FileInputStream fileInput1 = new FileInputStream(input); // tao object dai dien cho file input
		FileInputStream fileInput2 = new FileInputStream(input); // tao object dai dien cho file input
		FileOutputStream fileOutput = new FileOutputStream(output); // tao object dai dien cho file output
		
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes()); // tao doi object dai dien cho khoa nhap vao
		
		SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES"); //chay ham tao khoa bi mat tu giai thuat DES
		SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
		
		Cipher  cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, SecureRandom.getInstance("SHA1PRNG"));
		CipherInputStream cipherInputStream1 = new CipherInputStream(fileInput1, cipher);
		CipherInputStream cipherInputStream2 = new CipherInputStream(fileInput2, cipher);
		
		byte[] allBytes = IOUtils.toByteArray(cipherInputStream1);
		int fileBytesLength = allBytes.length;

		write(cipherInputStream2, fileOutput, fileBytesLength);
		success = "Encrypt with DES - ECB succesful !";
		System.out.print(success);
	}
	
	private static void write(InputStream input, OutputStream output, int fileBytesLength) throws IOException
	{
		byte[] buffer =	new byte[8];
		int numOfBytesRead;
		int length = fileBytesLength / 100;
		int value;
		int totalBytesRead = 0;
		while((numOfBytesRead = input.read(buffer)) != -1) {
			totalBytesRead = totalBytesRead + numOfBytesRead;
			value = totalBytesRead / length;

			output.write(buffer, 0, numOfBytesRead);
			GUI.MainFrame.progressBar.setValue(value);
		}
		input.close();
		output.close();
	}
}








