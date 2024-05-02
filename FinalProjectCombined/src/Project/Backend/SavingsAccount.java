package Project.Backend;
public class SavingsAccount extends Account{


	private double interestRate;
	private double maxWithdraw;
	
	public SavingsAccount(String accType, int accNum, double accBalance) {
		super(accType, accNum, accBalance);
	}
	
	public SavingsAccount() { //default constructor
		super.accType = "Savings";
		interestRate = 0.05;
		maxWithdraw = 1000.00;
	}
	
	public String toString() {
		return accType + "," + accNum + "," + accBalance + "," + interestRate + "," + maxWithdraw + ",";
	}
	
}
