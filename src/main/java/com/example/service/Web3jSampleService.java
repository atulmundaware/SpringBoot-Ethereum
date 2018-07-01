package com.example.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.exceptions.TransactionTimeoutException;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import com.example.utils.Constants;


@Service
public class Web3jSampleService {

	@Autowired
	private Web3j web3j;

	String usersWalletPath = null;
	String receiversAddress = null;

	public String sendAsynchronousRequest() throws IOException, InterruptedException, ExecutionException {
		Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();
		return web3ClientVersion.getWeb3ClientVersion();
	}

	public String sendSynchronousRequest() throws IOException, InterruptedException, ExecutionException {
		Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
		return web3ClientVersion.getWeb3ClientVersion();
	}

	public String createWallet() throws NoSuchAlgorithmException, NoSuchProviderException,
			InvalidAlgorithmParameterException, CipherException, IOException {
		String fileName = WalletUtils.generateFullNewWalletFile(Constants.DEFAULT_PASSWORD,
				new File(Constants.WALLET_PATH));

		Credentials credentials = WalletUtils.loadCredentials(Constants.DEFAULT_PASSWORD,
				Constants.WALLET_PATH + fileName);
		System.out.println("Users wallet address : " + credentials.getAddress());
		return "Users wallet address : " + credentials.getAddress();
	}

	public String etherBalance(String user) throws InterruptedException, ExecutionException, NoSuchAlgorithmException,
			NoSuchProviderException, InvalidAlgorithmParameterException, CipherException, IOException {

		setWallet(user);
		
		Credentials credentials = WalletUtils.loadCredentials("123123123", usersWalletPath);

		EthGetBalance ethGetBalance = web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST)
				.sendAsync()
				.get();

		BigInteger wei = ethGetBalance.getBalance();
		return "Ether Balance = " + wei.toString();

	}

	
	
	public String transcat(String to, double ethers) throws IOException, CipherException, InterruptedException, ExecutionException, TransactionTimeoutException{
		
		setWallet(to);
		
		Credentials credentials = WalletUtils.loadCredentials(Constants.DEFAULT_PASSWORD,
				usersWalletPath);
		TransactionReceipt transactionReceipt = Transfer.sendFunds(
		        web3j, credentials, receiversAddress, BigDecimal.valueOf(ethers), Convert.Unit.ETHER);
		String currentTime = java.time.LocalDateTime.now().toString(); 	
		String data = "Transfered " + ethers + " ETH from " + credentials.getAddress() +
					  " to ->" + receiversAddress + " at " + currentTime +
					  "\n\n Gas used : " + transactionReceipt.getGasUsed() +
					  "\n Block Hash : " + transactionReceipt.getBlockHash() + 
					  "\n Transaction Hash : " + transactionReceipt.getTransactionHash() +
				  	  "\n Cumulative Gas Used : " + transactionReceipt.getCumulativeGasUsed() + 
				  	  "\n Block Number Raw : " + transactionReceipt.getBlockNumberRaw();
		
		Constants.writeFile(data, "TransactionRecipt - " + currentTime.replace(":", "-")  + Constants.TXT);
		
		EthGetBalance ethGetBalance = web3j
				  .ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST)
				  .sendAsync()
				  .get();

		BigInteger wei = ethGetBalance.getBalance();
		
		return "Tansaction Completed your balance is " + wei.toString();
	}
	
	
	public void setWallet(String to){
		if(to.equalsIgnoreCase("a")){
			receiversAddress = Constants.A_USER_ADDRESS;
			usersWalletPath = Constants.B_USER_WALLET_PATH;
		}else if(to.equalsIgnoreCase("b")){
			receiversAddress = Constants.B_USER_ADDRESS;
			usersWalletPath = Constants.A_USER_WALLET_PATH;
		}else{
			System.out.println("User does not exist...."); 
		}
	}
	
	
}