package run;

import java.util.Locale;
import java.util.Scanner;

import model.classes.Account;
import model.exceptions.TransactionException;

public class SimpleTest {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner s = new Scanner(System.in);
		
		Account acc = new Account();
		Integer accNum = 0;
		String accHolder = null;
		double iniBalance = 0.0;
		
		try {
			System.out.println("Enter account data");
			System.out.print(" - Account Number:\t");
			accNum = s.nextInt();
			s.nextLine();
			System.out.print(" - Account Holder:\t");
			accHolder = s.nextLine();
			System.out.print(" - Initial Balance:\t");
			iniBalance = s.nextDouble();
			acc = new Account(accNum, accHolder, iniBalance);
			
			System.out.println(" - Withdraw Limit:\t" + acc.getWithdrawLimit());
			System.out.println("========================================");
			System.out.println("\n");
			System.out.print("Withdraw amount:\t");
			double withdrawValue = s.nextDouble();
			System.out.println("Last balance:\t\t$ " + String.format("%.2f", acc.getAccountBalance()));
			acc.withdraw(withdrawValue);
			System.out.println("Updated balance:\t$ " + String.format("%.2f", acc.getAccountBalance()) );
			System.out.println("\n");
			System.out.println("========================================");
			System.out.print("Deposit amount:\t");
			double depositValue = s.nextDouble();
			acc.deposit(depositValue);
			System.out.println(acc.toString());
		}
		catch (TransactionException e) {
			System.out.println(e.getMessage());
		}
		catch (RuntimeException e) {
			System.out.println(">> Runtime Exception >> Invalid insertion data!");
		} 
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		s.close();
		
	}
}
