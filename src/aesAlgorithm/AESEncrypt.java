package aesAlgorithm;
import GUI.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.IOUtils;


public class AESEncrypt {
	public static String success;
	public static void desECBencrypt(String key, File input, File output) 
	throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IOException, InterruptedException
	{
		FileInputStream fileInput1 = new FileInputStream(input); // tao object dai dien cho file input
		FileInputStream fileInput2 = new FileInputStream(input); // tao object dai dien cho file input
		FileOutputStream fileOutput = new FileOutputStream(output); // tao object dai dien cho file output
		
		MessageDigest sha1  = MessageDigest.getInstance("SHA-1");
		byte[] byteArrayKey = key.getBytes();
		byteArrayKey = sha1.digest(byteArrayKey);
		byteArrayKey = Arrays.copyOf(byteArrayKey, 16);// secret key cua giai thuat AES la 128-bits (=16 bytes) cho nen 
		SecretKeySpec secretKey = new SecretKeySpec(byteArrayKey,"AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		CipherInputStream cipherInputStream1 = new CipherInputStream(fileInput1, cipher);
		CipherInputStream cipherInputStream2 = new CipherInputStream(fileInput2, cipher);
		
		byte[] allBytes = IOUtils.toByteArray(cipherInputStream1);
		int fileBytesLength = allBytes.length;

		write(cipherInputStream2, fileOutput, fileBytesLength);
		success = "Encrypt with AES - ECB succesful !";
		System.out.print(success);
	}
	
	private static void write(InputStream input, OutputStream output, int fileBytesLength) throws IOException
	{
		byte[] buffer =	new byte[16];
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








