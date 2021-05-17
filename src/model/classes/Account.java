package model.classes;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.exceptions.TransactionException;

public class Account {
	
	private static final double minAccountValue = 100.0;
	
	private Integer accountNumber;
	private String accountHolder;
	private Double accountBalance;
	private Double withdrawLimit;
	private Date accountCreation;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:ss");
	
	public Account() {
	}
	public Account(Integer accountNumber, String accountHolder, Double accountBalance) throws TransactionException, RuntimeException {
		try {
			if (accountBalance < minAccountValue) {
				throw new TransactionException("Couldn't initiated the account lesser than $" + minAccountValue);
			}
			validateName(accountHolder);
			this.accountCreation = new Date();
			this.accountNumber= accountNumber;
			this.accountBalance = accountBalance;
			this.withdrawLimit = withdrawLimit();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	public Integer getAccountNumber() {
		return this.accountNumber;
	}
	public String getAccountHolder() {
		return this.accountHolder;
	}
	public Double getAccountBalance() {
		return this.accountBalance;
	}
	public Double getWithdrawLimit() {
		return this.withdrawLimit;
	}
	public Date getCreationDate() {
		return this.accountCreation;
	}
	
	
	
	private void validateName(String name) throws Exception, IllegalArgumentException {
		if ( name.length() < 6 ) {
			throw new IllegalArgumentException(">> Insufficient letters to recognized it as a full name.");
		}
		String[] invalidParam = "0123456789/*-+.'!@#$%¨&*()_+`´[]~^/°ºª?".split("");
		String[] names;
		names = name.split("");
		for (int i = 0; i < names.length; i++) {
			for (int j = 0; j < invalidParam.length; j++) {
				if ( names[i].contains(invalidParam[j]) ) {
					throw new IllegalArgumentException(">> Invalid insertion");
				}
			}
		}
		this.accountHolder = toCapitalizeCase(name);
	}
	
	
	
	private String toCapitalizeCase(String str) {
		String[] separate = str.toLowerCase().split(" ");
		String newStr = "";
		for (int i = 0; i < separate.length; i++) {
			newStr += separate[i].toUpperCase().substring(0,1);
			if (i >= separate.length) {
				newStr += separate[i].substring(1,separate[i].length());
			} else {
				newStr += separate[i].substring(1,separate[i].length()) + " ";
			}
		}
		return newStr;
	}
	
	
	
	public void deposit(Double amount) throws TransactionException {	
		if ( amount < 0 ) {
			throw new TransactionException("Couldn't complete transaction");
		} 
		this.accountBalance += amount;
	}
	
	public void withdraw(Double amount) throws TransactionException {
		if ( amount < 0 ) {
			throw new TransactionException("Couldn't complete transaction");
		}
		if ( amount > this.withdrawLimit ) {
			throw new TransactionException("The amount exceeds withdraw limit");
		}
		if ( amount > this.accountBalance ) {
			throw new TransactionException("Insufficient balance to complete the transaction");
		}
		this.accountBalance -= amount;
	}
	
	
	
	public Double withdrawLimit() {
		// Limite baseado no Saldo Inicial + 40%
		this.withdrawLimit = this.accountBalance + (this.accountBalance * 0.40);
		return this.withdrawLimit;
	}
	
	
	
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("> Account: \n");
		str.append(" - Number:\t\t" + this.accountNumber + "\n");
		str.append(" - Holder name:\t\t" + this.accountHolder + "\n");
		str.append(" - Account Creation:\t" + dateFormat.format(this.accountCreation) + "\n");
		str.append(" - Balance:\t\t" + String.format("%.2f", this.accountBalance) + "\n");
		str.append(" - Withdraw Limit:\t" + String.format("%.2f", this.withdrawLimit) + "\n");
		return str.toString();
	}
}
