package com.example.utils;

import java.io.File;
import java.io.FileWriter;

public class Constants {
	public static final String A_USER_ADDRESS = "0x48fee5d00e4a39a92c3477f54103a2b63a7ae97d";

	public static final String B_USER_ADDRESS = "0x39c633e6fa59b7fdc4025ecca55ca9e91d544e8a";

	public static final String WALLET_PATH = "D:\\Ethereum\\";

	public static final String A_USER_WALLET_PATH = WALLET_PATH + "UTC--2017-09-12T10-13-08.77000000Z--48fee5d00e4a39a92c3477f54103a2b63a7ae97d.json";

	public static final String B_USER_WALLET_PATH = WALLET_PATH + "UTC--2017-09-12T10-14-23.678000000Z--39c633e6fa59b7fdc4025ecca55ca9e91d544e8a.json";

	public static final String DEFAULT_PASSWORD = "123123123";

	public static final String TRANSACTION_RECEIPT_PATH = "D:\\Ethereum\\TransactionReceipt\\";

	public static final String TXT = ".txt";

	public static void writeFile(String data, String fileName) {
		try {
			File file = new File(TRANSACTION_RECEIPT_PATH + fileName);
				if (file.createNewFile()) {
					System.out.println("File is created!");
				} else {
					System.out.println("File already exists.");
				}
			FileWriter fw = new FileWriter(TRANSACTION_RECEIPT_PATH + fileName);
			fw.write(data);
			fw.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Transaction Details saved ... @ => " + TRANSACTION_RECEIPT_PATH + fileName);
	}

}
