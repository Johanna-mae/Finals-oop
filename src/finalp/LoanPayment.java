package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class LoanPayment extends JPanel {

  // ===== COMBO ITEM =====
  class ComboItem {
    int id;
    String label;

    ComboItem(int id, String label) {
      this.id = id;
      this.label = label;
    }

    public String toString() {
      return label;
    }
  }

  // ===== LOAN META =====
  class LoanMeta {
    LocalDate loanEndDate;
    double principalAmount;
    double totalInterest;
    double totalAmountPayable;
    String loanReferenceNumber;
    int clientID;
  }

  JComboBox<ComboItem> cbClient, cbLoan, cbEmployee;
  JComboBox<String> cbMethod;
  JTextField tfDue, tfPrincipal, tfInterest, tfAmount, tfPenalty;
  JTextArea taRemarks;

  Map<Integer, LoanMeta> loanData = new HashMap<>();

  public LoanPayment() {
    setLayout(null);
    setBounds(280, 0, 1090, 800);
    setBackground(Color.WHITE);

    JLabel header = new JLabel("Loan Payments");
    header.setFont(new Font("Arial", Font.BOLD, 25));
    header.setBounds(20, 25, 500, 40);
    add(header);

    JLabel sub = new JLabel("View payment details");
    sub.setBounds(23, 55, 400, 20);
    add(sub);

    JSeparator sep = new JSeparator();
    sep.setBounds(23, 80, 1040, 2);
    add(sep);

    JPanel box = new JPanel(null);
    box.setBounds(150, 90, 780, 480);
    box.setBackground(new Color(0xAAC3DD));
    add(box);

    int y = 20;

    // ===== CLIENT NAME =====
    box.add(label("Client Name", y));
    cbClient = new JComboBox<>();
    cbClient.setEnabled(false);
    cbClient.setBounds(200, y, 540, 28);
    box.add(cbClient);
    y += 38;

    // ===== LOAN REFERENCE NUMBER =====
    box.add(label("Loan Reference No.", y));
    cbLoan = new JComboBox<>();
    cbLoan.setBounds(200, y, 540, 28);
    box.add(cbLoan);
    y += 38;

    // ===== DUE DATE =====
    box.add(label("Due Date", y));
    tfDue = new JTextField();
    tfDue.setEditable(false);
    tfDue.setBounds(200, y, 540, 28);
    box.add(tfDue);
    y += 38;

    // ===== PRINCIPAL =====
    box.add(label("Principal Paid", y));
    tfPrincipal = new JTextField();
    tfPrincipal.setEditable(false);
    tfPrincipal.setBounds(200, y, 540, 28);
    box.add(tfPrincipal);
    y += 38;

    // ===== INTEREST =====
    box.add(label("Interest Paid", y));
    tfInterest = new JTextField();
    tfInterest.setEditable(false);
    tfInterest.setBounds(200, y, 540, 28);
    box.add(tfInterest);
    y += 38;

    // ===== AMOUNT =====
    box.add(label("Amount Paid", y));
    tfAmount = new JTextField();
    tfAmount.setEditable(false);
    tfAmount.setBounds(200, y, 540, 28);
    box.add(tfAmount);
    y += 38;

    // ===== PENALTY =====
    box.add(label("Penalty Fee", y));
    tfPenalty = new JTextField("0.00");
    tfPenalty.setEditable(true);
    tfPenalty.setBounds(200, y, 540, 28);
    box.add(tfPenalty);
    y += 38;

    // ===== PAYMENT METHOD =====
    box.add(label("Payment Method", y));
    cbMethod = new JComboBox<>(new String[] {
        "Cash", "Bank Transfer", "GCash", "PayMaya", "Check"
    });
    cbMethod.setBounds(200, y, 540, 28);
    box.add(cbMethod);
    y += 38;

    // ===== EMPLOYEE =====
    box.add(label("Processed By", y));
    cbEmployee = new JComboBox<>();
    cbEmployee.setBounds(200, y, 540, 28);
    box.add(cbEmployee);
    y += 38;

    // ===== REMARKS =====
    box.add(label("Remarks", y));
    taRemarks = new JTextArea();
    JScrollPane sp = new JScrollPane(taRemarks);
    sp.setBounds(200, y, 540, 50);
    box.add(sp);

    JButton pay = new JButton("Pay");
    pay.setBounds(830, 590, 100, 40);
    add(pay);

    cbLoan.addActionListener(e -> updateLoanDetails());
    pay.addActionListener(e -> savePayment());

    loadLoans();
    loadEmployees();

    cbClient.setSelectedIndex(-1);
    cbLoan.setSelectedIndex(-1);
    tfPenalty.setText("");
    cbMethod.setSelectedIndex(-1);
    cbEmployee.setSelectedIndex(-1);
  }

  JLabel label(String text, int y) {
    JLabel l = new JLabel(text);
    l.setBounds(20, y, 180, 28);
    l.setFont(new Font("Arial", Font.BOLD, 13));
    return l;
  }

  // ===== LOAD LOANS =====
  void loadLoans() {

    String query = """
        SELECT 
          l.loan_id, 
          l.loan_reference_number, 
          l.loan_end_date,
          l.principal_amount, 
          l.total_interest, 
          l.total_amount_payable,
          c.client_id,
          c.first_name, 
          c.last_name 
          FROM Loan l
          JOIN Client c ON l.client_id = c.client_id
          """;
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {

      while (rs.next()) {
        int loanId = rs.getInt("loan_id");

        cbLoan.addItem(new ComboItem(
            loanId,
            rs.getString("loan_reference_number")));

        cbClient.addItem(new ComboItem(
            rs.getInt("client_id"),
            rs.getString("first_name") + " " + rs.getString("last_name")));

        LoanMeta meta = new LoanMeta();
        meta.loanEndDate = rs.getDate("loan_end_date").toLocalDate();
        meta.principalAmount = rs.getDouble("principal_amount");
        meta.totalInterest = rs.getDouble("total_interest");
        meta.totalAmountPayable = rs.getDouble("total_amount_payable");
        meta.loanReferenceNumber = rs.getString("loan_reference_number");
        meta.clientID = rs.getInt("client_id");

        loanData.put(loanId, meta);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // ===== LOAD EMPLOYEES (RETAINED) =====
  void loadEmployees() {
    String query = "SELECT employee_id, first_name, last_name FROM Employee";


    try (Connection conn = DatabaseConnection.getConnection();
         ResultSet rs = conn.createStatement().executeQuery(query)) {

      while (rs.next()) {
        cbEmployee.addItem(new ComboItem(
            rs.getInt("employee_id"),
            rs.getString("first_name") + " " + rs.getString("last_name")));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // ===== UPDATE UI FROM LOAN =====
  void updateLoanDetails() {
    ComboItem loanItem = (ComboItem) cbLoan.getSelectedItem();
    if (loanItem == null)
      return;

    LoanMeta meta = loanData.get(loanItem.id);
    if (meta == null)
      return;

    tfDue.setText(meta.loanEndDate.toString());
    tfPrincipal.setText(String.format("%.2f", meta.principalAmount));
    tfInterest.setText(String.format("%.2f", meta.totalInterest));
    tfAmount.setText(String.format("%.2f", meta.totalAmountPayable));

    for (int i = 0; i < cbClient.getItemCount(); i++) {
      ComboItem client = cbClient.getItemAt(i);
      if (client.id == meta.clientID) {
        cbClient.setSelectedIndex(i);
        break;
      }
    }

  }

  // ===== SAVE PAYMENT =====
  void savePayment() {

    String query = """
        INSERT INTO Payment (
            payment_reference_number,
            paymenr_date,
            due_date,
            amount_paid,
            principal_paid,
            interest_paid,
            penalty_fee,
            payment_method,
            remarks,
            processed_by_employee_id,
            loan_id
        ) VALUES (?, NOW(), ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

    try (Connection con = DatabaseConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(query)){

      if (cbLoan.getSelectedItem() == null ||
          cbEmployee.getSelectedItem() == null) {

        JOptionPane.showMessageDialog(this, "Please complete all required fields.");
        return;
      }

      ComboItem loan = (ComboItem) cbLoan.getSelectedItem();
      ComboItem emp = (ComboItem) cbEmployee.getSelectedItem();
      LoanMeta meta = loanData.get(loan.id);

      // Auto-generated payment reference
      String paymentRef = ReferenceNumberGenerator.generatePaymentRefNo(5);

      ps.setString(1, paymentRef);
      ps.setDate(2, Date.valueOf(tfDue.getText()));
      ps.setDouble(3, Double.parseDouble(tfAmount.getText()));
      ps.setDouble(4, Double.parseDouble(tfPrincipal.getText()));
      ps.setDouble(5, Double.parseDouble(tfInterest.getText()));
      ps.setDouble(6, Double.parseDouble(tfPenalty.getText()));
      ps.setString(7, cbMethod.getSelectedItem().toString());
      ps.setString(8, taRemarks.getText());
      ps.setInt(9, emp.id);
      ps.setInt(10, loan.id);

      ps.executeUpdate();

      JOptionPane.showMessageDialog(this, "Payment saved successfully!");

    } catch (SQLException e) {
        e.printStackTrace();
      JOptionPane.showMessageDialog(this, "Error saving payment:\n" + e.getMessage());
    } catch (NumberFormatException nfe) {
        nfe.printStackTrace();
        JOptionPane.showMessageDialog(this, "Please input the amount of the penalty fee.");
    }
  }

}
