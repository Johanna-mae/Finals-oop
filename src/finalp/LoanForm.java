package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.time.LocalDate;

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
            new String[]{"Juan Dela Cruz", "Maria Santos", "Pedro Reyes"}
        );
        cbClient.setBounds(180, 20, 550, 30);
        box.add(cbClient);

        // LOAN TYPE
        JLabel loanType = new JLabel("Loan Type");
        loanType.setBounds(20, 70, 150, 30);
        loanType.setFont(new Font("Arial", Font.BOLD, 14));
        box.add(loanType);

        JComboBox<String> cbLoanType = new JComboBox<>(
            new String[]{"Personal Loan", "Business Loan", "Emergency Loan"}
        );
        cbLoanType.setBounds(180, 70, 550, 30);
        box.add(cbLoanType);

        // REQUESTED AMOUNT
        JLabel requestedAmount = new JLabel("Requested Amount");
        requestedAmount.setBounds(20, 120, 150, 30);
        requestedAmount.setFont(new Font("Arial", Font.BOLD, 14));
        box.add(requestedAmount);

        JTextField tfAmount = new JTextField();
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
            new String[]{"3", "6", "12", "18", "24"}
        );
        cbTerm.setBounds(180, 170, 550, 30);
        box.add(cbTerm);

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
                double amount = Double.parseDouble(tfAmount.getText());
                int months = Integer.parseInt(cbTerm.getSelectedItem().toString());
                double perMonth = amount / months;
                tfEstimate.setText("₱" + String.format("%.2f", perMonth));
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

            setVisible(false);
            loanapplication.setVisible(true);
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
    
    }
    
}
        
        
