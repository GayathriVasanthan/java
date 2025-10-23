import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BankAccount {
    private double balance;

    public BankAccount() {
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class BankApp extends JFrame {
    private BankAccount account;
    private JLabel balanceLabel;
    private JTextField depositField;
    private JTextField withdrawField;

    public BankApp() {
        account = new BankAccount();
        setTitle("Bank Account Management");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        balanceLabel = new JLabel("Balance: $0.00");
        add(balanceLabel);

        JLabel depositLabel = new JLabel("Deposit:");
        depositField = new JTextField(10);
        add(depositLabel);
        add(depositField);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(e -> {
            double amount = Double.parseDouble(depositField.getText());
            account.deposit(amount);
            updateBalance();
        });
        add(depositButton);

        JLabel withdrawLabel = new JLabel("Withdraw:");
        withdrawField = new JTextField(10);
        add(withdrawLabel);
        add(withdrawField);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(e -> {
            double amount = Double.parseDouble(withdrawField.getText());
            account.withdraw(amount);
            updateBalance();
        });
        add(withdrawButton);

        setVisible(true);
    }

    private void updateBalance() {
        balanceLabel.setText("Balance: $" + account.getBalance());
    }

    public static void main(String[] args) {
        new BankApp();
    }
}
