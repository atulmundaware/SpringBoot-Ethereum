package com.example.controller;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.exceptions.TransactionTimeoutException;

import com.example.service.Web3jSampleService;


@RestController
@RequestMapping(value = "/ethereum")
public class EthereumController {

	@Autowired
	private Web3jSampleService web3jService;
	
	@RequestMapping(value = "/sendAsyncRequest")
	public String sendAsyncRequest() throws IOException, InterruptedException, ExecutionException{
		System.out.println(web3jService.sendAsynchronousRequest());
		return web3jService.sendAsynchronousRequest();
	}
	
	@RequestMapping(value = "/sendSyncRequest")
	public String sendSyncRequest() throws IOException, InterruptedException, ExecutionException{
		System.out.println(web3jService.sendAsynchronousRequest());
		return web3jService.sendAsynchronousRequest();
	}
	
	@RequestMapping(value = "/createWallet")
	public String createWallet() throws IOException, InterruptedException, ExecutionException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException{
		return web3jService.createWallet();
	}
	
	@RequestMapping(value = "/getBalance/{user}")
	public String createWallet(@PathVariable("user") String user) throws IOException, InterruptedException, ExecutionException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException{
		return web3jService.etherBalance(user);
	}
	
	@RequestMapping(value = "/sendEther/{to}/{ethers}")
	public String sendEther(@PathVariable("to") String to,
			@PathVariable("ethers") double ethers) throws IOException, InterruptedException, ExecutionException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException, TransactionTimeoutException{
		return web3jService.transcat(to,ethers);
	}
	
}
