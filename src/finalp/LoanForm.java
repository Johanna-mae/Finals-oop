package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;

class LoanForm extends JPanel {
    
    Color NORMAL = new Color(0xAAC3DD);
    Color ACTIVE = new Color(0x8AA1B9);

    JButton selected = null;
    int applicationCounter = 1000; // start ng numbering sa id
    
    LoanApplication loanapplication;

    public LoanForm(LoanApplication loanapplication) {
        this.loanapplication = loanapplication;
        
        setLayout(null);
        setBounds(280, 0, 1090, 800);
        setBackground(Color.WHITE);

        JLabel header = new JLabel("Create Loan Application");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 400, 40);
        add(header);

        JLabel sub = new JLabel("Enter details to avail loan");
        sub.setBounds(23, 55, 400, 20);
        add(sub);
        
        JSeparator sep = new JSeparator();
        sep.setBounds(23, 80, 1040, 2);
        add(sep);

        JPanel box = new JPanel();
        box.setLayout(null);
        box.setBackground(NORMAL);
        box.setBounds(170, 110, 750, 430);
        add(box);

        // CLIENT
        JLabel clientName = new JLabel("Client Name");
        clientName.setBounds(20, 20, 150, 30);
        clientName.setFont(new Font("Arial", Font.BOLD, 14));
        box.add(clientName);

        JComboBox<String> cbClient = new JComboBox<>(
            new String[100]
        );
        retrieveClientList(cbClient);
        cbClient.setBounds(180, 20, 550, 30);
        box.add(cbClient);

        // LOAN TYPE
        JLabel loanType = new JLabel("Loan Type");
        loanType.setBounds(20, 70, 150, 30);
        loanType.setFont(new Font("Arial", Font.BOLD, 14));
        box.add(loanType);

        JComboBox<String> cbLoanType = new JComboBox<>(
            new String[10]
        );
        retrieveLoanTypesList(cbLoanType);
        cbLoanType.setBounds(180, 70, 550, 30);
        box.add(cbLoanType);

        // REQUESTED AMOUNT
        JLabel requestedAmount = new JLabel("Requested Amount");
        requestedAmount.setBounds(20, 120, 150, 30);
        requestedAmount.setFont(new Font("Arial", Font.BOLD, 14));
        box.add(requestedAmount);

        NumberFormat format = NumberFormat.getIntegerInstance();
        JFormattedTextField tfAmount = new JFormattedTextField(format);
        tfAmount.setColumns(10);
        tfAmount.setBounds(180, 120, 350, 30);
        box.add(tfAmount);

        // ESTIMATED PER TERM (SIDE)
        JLabel estimatedPerTerm = new JLabel("Est. / Month");
        estimatedPerTerm.setBounds(540, 120, 100, 30);
        estimatedPerTerm.setFont(new Font("Arial", Font.BOLD, 14));
        box.add(estimatedPerTerm);

        JTextField tfEstimate = new JTextField();
        tfEstimate.setBounds(630, 120, 100, 30);
        tfEstimate.setEditable(false);
        box.add(tfEstimate);

        // TERM
        JLabel Term = new JLabel("Term (Months)");
        Term.setBounds(20, 170, 150, 30);
        Term.setFont(new Font("Arial", Font.BOLD, 14));
        box.add(Term);

        JComboBox<String> cbTerm = new JComboBox<>(
            new String[5]
        );
        //retrieveMinMaxTerms(retrieveLoanTypesList(cbLoanType), cbTerm);
        cbTerm.setBounds(180, 170, 550, 30);
        box.add(cbTerm);

        if (cbLoanType.getSelectedItem() != null) {
            retrieveMinMaxTerms(cbLoanType.getSelectedItem().toString(), cbTerm);
        }

        // PURPOSE
        JLabel Purpose = new JLabel("Purpose");
        Purpose.setBounds(20, 220, 150, 30);
        Purpose.setFont(new Font("Arial", Font.BOLD, 14));
        box.add(Purpose);

        JTextArea taPurpose = new JTextArea();
        taPurpose.setLineWrap(true);
        JScrollPane spPurpose = new JScrollPane(taPurpose);
        spPurpose.setBounds(180, 220, 550, 60);
        box.add(spPurpose);

        // STATUS
        JLabel Status = new JLabel("Status");
        Status.setBounds(20, 300, 150, 30);
        Status.setFont(new Font("Arial", Font.BOLD, 14));
        box.add(Status);

        JComboBox<String> cbStatus = new JComboBox<>(new String[]{"Pending"});
        cbStatus.setBounds(180, 300, 550, 30);
        box.add(cbStatus);

        // DOCUMENTS
        JLabel documents = new JLabel("Documents (Max 2)");
        documents.setBounds(20, 350, 150, 30);
        documents.setFont(new Font("Arial", Font.BOLD, 14));
        box.add(documents);

        DefaultListModel<File> fileModel = new DefaultListModel<>();
        JList<File> fileList = new JList<>(fileModel);
        JScrollPane fileScroll = new JScrollPane(fileList);
        fileScroll.setBounds(180, 350, 350, 60);
        box.add(fileScroll);

        JButton upload = new JButton("Upload Files");
        upload.setBounds(540, 350, 190, 30);
        upload.setBackground(NORMAL);
        box.add(upload);

        JButton removeFile = new JButton("Remove Selected");
        removeFile.setBounds(540, 380, 190, 30);
        removeFile.setBackground(NORMAL);
        box.add(removeFile);

        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);

        // UPLOAD LOGIC
        upload.addActionListener(e -> {
            int result = chooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File[] files = chooser.getSelectedFiles();

                for (File f : files) {
                    if (fileModel.size() >= 2) {
                        JOptionPane.showMessageDialog(
                            this,
                            "Maximum of 2 files only.",
                            "Upload Limit",
                            JOptionPane.ERROR_MESSAGE
                        );
                        break;
                    }

                    if (!fileModel.contains(f)) {
                        fileModel.addElement(f);
                    }
                }
            }
        });

        // REMOVE FILE
        removeFile.addActionListener(e -> {
            int index = fileList.getSelectedIndex();

            if (index != -1) {
                fileModel.remove(index);
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Please select a file to remove.",
                    "No File Selected",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        });

        // COMPUTE ESTIMATED PER MONTH
        ActionListener computeEstimate = e -> {
            try {
                Object value = tfAmount.getValue();
                if (value == null || cbTerm.getSelectedItem() == null) {
                    tfEstimate.setText("");
                    return;
                }

                //double amount = ((Number) value).doubleValue();
                double principal = ((Number) value).doubleValue();
                String termString = cbTerm.getSelectedItem().toString();
                int months = Integer.parseInt(termString.split(" ")[0]);
               
                // Get interest rate from database based on loan type
                String loanTypeEntry = cbLoanType.getSelectedItem().toString();
                double annualInterestRate = retrieveInterestRate(loanTypeEntry);
                
                if (months > 0 && principal > 0) {
                    double monthlyPayment;
                    
                    if (annualInterestRate > 0) {
                        // Monthly interest rate (annual / 12 / 100)
                        double monthlyRate = annualInterestRate / 12 / 100;
                        
                        // Amortization formula: P * [r(1+r)^n] / [(1+r)^n - 1]
                        // Where: P = principal, r = monthly rate, n = number of months
                        double numerator = monthlyRate * Math.pow(1 + monthlyRate, months);
                        double denominator = Math.pow(1 + monthlyRate, months) - 1;
                        monthlyPayment = principal * (numerator / denominator);

                        //System.out.println("monthly rate is " + monthlyRate); //debugs
                        //System.out.println("numerator is " + numerator);
                        //System.out.println("denominator is " + denominator);
                        //System.out.println("compounded monthly payment is " + monthlyPayment);

                    } else {
                        // No interest (0% loans)
                        monthlyPayment = principal / months;
                        //System.out.println("no interest monthly payment is " + monthlyPayment);
                    }

                    // ✅ FORMAT AND DISPLAY THE RESULT (this was missing!)
                    NumberFormat formatter = NumberFormat.getInstance();
                    formatter.setMinimumFractionDigits(2);
                    formatter.setMaximumFractionDigits(2);

                    tfEstimate.setText("₱" + formatter.format(monthlyPayment));
        
                }
            } catch (Exception ex) {
                tfEstimate.setText("");
            }
        };

        tfAmount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                computeEstimate.actionPerformed(null);
            }
        });
        cbTerm.addActionListener(computeEstimate);

        // BUTTONS
        JButton cancel = new JButton("Cancel");
        cancel.setBounds(555, 550, 120, 40);
        cancel.setBackground(NORMAL);
        add(cancel);

        JButton createLoanApplication = new JButton("Create Loan Application");
        createLoanApplication.setBounds(690, 550, 230, 40);
        createLoanApplication.setBackground(NORMAL);
        add(createLoanApplication);

        createLoanApplication.addActionListener(e -> {
            applicationCounter++;
            
            loanapplication.setApplicationData(
                cbClient.getSelectedItem().toString(),
                cbLoanType.getSelectedItem().toString(),
                tfAmount.getText(),
                tfEstimate.getText(),
                cbTerm.getSelectedItem().toString(),
                taPurpose.getText(),
                "APP-" + applicationCounter,
                LocalDate.now().toString()
            );

            //setVisible(false);
            
        });
        
        //functions ng button mouselistener
        cancel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                if (selected != cancel) {
                    cancel.setBackground(ACTIVE);
                }
            }
            public void mouseExited(MouseEvent e) {
                if (selected != cancel) {
                    cancel.setBackground(NORMAL);
                }
            }
            public void mousePressed(MouseEvent cl) {
                selected = cancel;

                cancel.setBackground(ACTIVE);
                createLoanApplication.setBackground(NORMAL);
            }
        });
        
        createLoanApplication.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                if (selected != createLoanApplication) {
                    createLoanApplication.setBackground(ACTIVE);
                }
            }
            public void mouseExited(MouseEvent e) {
                if (selected != createLoanApplication) {
                    createLoanApplication.setBackground(NORMAL);
                }
            }
            public void mousePressed(MouseEvent cl) {
                selected = createLoanApplication;

                cancel.setBackground(NORMAL);
                createLoanApplication.setBackground(ACTIVE);
            }
        });

        cbLoanType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent i) {
                if (i.getStateChange() == ItemEvent.SELECTED) {
                    String selectedLoanType = (String) cbLoanType.getSelectedItem();
                    if (selectedLoanType != null && !selectedLoanType.isEmpty()) {
                        retrieveMinMaxTerms(selectedLoanType, cbTerm);
                    }
                }
            }
        });
    
    }

    public void retrieveClientList(JComboBox<String> cbClient) {
        String query = """
            SELECT client_id, 
            CONCAT(last_name, ', ', first_name, ' ', LEFT(middle_name, 1), '.') AS full_name
            FROM Client
                """;;

        cbClient.removeAllItems();

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String full_name = rs.getString("full_name");
                cbClient.addItem(full_name);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    public void retrieveLoanTypesList(JComboBox<String> cbLoanTypes) {
        String loan_type = "";
        String query = """
            SELECT loan_type_id, type_name FROM Loan_Type
                """;
        cbLoanTypes.removeAllItems();

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                loan_type = rs.getString("type_name");
                cbLoanTypes.addItem(loan_type);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
            "Error loading loan types: " + e.getMessage(),
            "Database Error", 
            JOptionPane.ERROR_MESSAGE);
        }      
    }

    public void retrieveMinMaxTerms(String typeName, JComboBox<String> cbTerm) {
        cbTerm.removeAllItems();
        
        int min = 0;
        int max = 0;

        String query = """
            SELECT min_term_months, max_term_months FROM Loan_Type WHERE type_name = ?
                """;

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, typeName);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                min = rs.getInt("min_term_months");
                max = rs.getInt("max_term_months");
            

                for (int i = min; i <= max; i+=6) {
                    cbTerm.addItem(String.valueOf(i) + " Months");
                }
            } else {
            // No loan type found
                JOptionPane.showMessageDialog(null,
                "Loan type '" + typeName + "' not found in database.",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
        }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
            "Error loading loan terms: " + e.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE);
        }
    }

    public double retrieveInterestRate(String typeName) {
        double interestRate = 0.0;
        
        String query = "SELECT annual_interest_rate FROM Loan_Type WHERE type_name = ?";
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, typeName);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                interestRate = rs.getDouble("annual_interest_rate");
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return interestRate;
    }
    
}
        
        
