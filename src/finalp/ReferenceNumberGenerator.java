package finalp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.SecureRandom;

public class ReferenceNumberGenerator {
    public static String generateClientRefNo(int length) {
        boolean isUnique = false;
        String clientRefNo = "";

        SecureRandom random = new SecureRandom();

        while (isUnique == false) {
            String numberString = random.ints(length, '0', '9' + 1) // Generate a stream of ASCII values for '0' to '9'
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            clientRefNo = "CLI-" + numberString;

            String query = "SELECT 1 FROM Client WHERE client_reference_number = ?";

            try {
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, clientRefNo);

                try {
                    ResultSet rs = ps.executeQuery();

                    if (!rs.next()) {
                        isUnique = true;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return clientRefNo;
    }

    public static String generateDocumentRefNo(int length, String documentType) {
        boolean isUnique = false;
        String documentRefNo = "";
        String documentTypeID = "";

        switch (documentType) {
            case "Valid ID":
                documentTypeID = "01";
                break;
            case "Proof of Income":
                documentTypeID = "02";
                break;
            case "ITR":
                documentTypeID = "03";
                break;
            case "Proof of Billing":
                documentTypeID = "04";
                break;
            case "Employment Certificate":
                documentTypeID = "05";
                break;
            case "Business Permit":
                documentTypeID = "06";
                break;
            case "Collateral Documents":
                documentTypeID = "07";
                break;
            default:
                break;
        }

        SecureRandom random = new SecureRandom();

        while (isUnique == false) {
            String numberString = random.ints(length, '0', '9' + 1) // Generate a stream of ASCII values for '0' to '9'
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            documentRefNo = "DOC-" + documentTypeID + numberString;

            String query = "SELECT 1 FROM Document WHERE document_reference_number = ?";

            try {
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, documentRefNo);

                try {
                    ResultSet rs = ps.executeQuery();

                    if (!rs.next()) {
                        isUnique = true;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return documentRefNo;
    }

    public static String generateEmployeeRefNo(int length) {
        boolean isUnique = false;
        String employeeRefNo = "";

        SecureRandom random = new SecureRandom();

        while (isUnique == false) {
            String numberString = random.ints(length, '0', '9' + 1) // Generate a stream of ASCII values for '0' to '9'
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            employeeRefNo = "EMP-" + numberString;

            String query = "SELECT 1 FROM Employee WHERE employee_reference_number = ?";

            try {
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, employeeRefNo);

                try {
                    ResultSet rs = ps.executeQuery();

                    if (!rs.next()) {
                        isUnique = true;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return employeeRefNo;
    }

    public static String generateLoanRefNo(int length, int loanTypeID) {
        boolean isUnique = false;
        String loanRefNo = "";
        String loanTypeString = "";

        switch (loanTypeID) {
            case 1:
                loanTypeString = "PS";
                break;
            case 2:
                loanTypeString = "AT";
                break;
            case 3:
                loanTypeString = "HS";
                break;
            case 4:
                loanTypeString = "BN";
                break;
            case 5:
                loanTypeString = "ED";
                break;
            default:
                break;
        }

        SecureRandom random = new SecureRandom();

        while (isUnique == false) {
            String numberString = random.ints(length, '0', '9' + 1) // Generate a stream of ASCII values for '0' to '9'
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            loanRefNo = "LON-" + loanTypeString + "-" + numberString;

            String query = "SELECT 1 FROM Loan WHERE loan_reference_number = ?";

            try {
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, loanRefNo);

                try {
                    ResultSet rs = ps.executeQuery();

                    if (!rs.next()) {
                        isUnique = true;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return loanRefNo;

    }

    public static String generateLoanApplicationRefNo(int length, int loanTypeID) {
        boolean isUnique = false;
        String loanApplicationRefNo = "";
        String loanTypeString = "";

        switch (loanTypeID) {
            case 1:
                loanTypeString = "PS";
                break;
            case 2:
                loanTypeString = "AT";
                break;
            case 3:
                loanTypeString = "HS";
                break;
            case 4:
                loanTypeString = "BN";
                break;
            case 5:
                loanTypeString = "ED";
                break;
            default:
                break;
        }

        SecureRandom random = new SecureRandom();

        while (isUnique == false) {
            String numberString = random.ints(length, '0', '9' + 1) // Generate a stream of ASCII values for '0' to '9'
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            loanApplicationRefNo = "LNA-" + loanTypeString + "-" + numberString;

            String query = "SELECT 1 FROM Loan_Application WHERE loan_application_reference_number = ?";

            try {
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, loanApplicationRefNo);

                try {
                    ResultSet rs = ps.executeQuery();

                    if (!rs.next()) {
                        isUnique = true;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return loanApplicationRefNo;
    }

    public static String generatePaymentRefNo(int length) {
        boolean isUnique = false;
        String paymentRefNo = "";

        SecureRandom random = new SecureRandom();

        while (isUnique == false) {
            String numberString = random.ints(length, '0', '9' + 1) // Generate a stream of ASCII values for '0' to '9'
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            paymentRefNo = "PYM-" + paymentRefNo + numberString;

            String query = "SELECT 1 FROM Payment WHERE payment_reference_number = ?";

            try {
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, paymentRefNo);

                try {
                    ResultSet rs = ps.executeQuery();

                    if (!rs.next()) {
                        isUnique = true;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return paymentRefNo;
    }
}
