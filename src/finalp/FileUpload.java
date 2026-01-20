package finalp;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

public class FileUpload {
    public void uploadDocument(int applicationID, DefaultListModel<File> fileModel, JComboBox<String> cbDocType1, JComboBox<String> cbDocType2){

        String projectPath = System.getProperty("user.home");
        File storageDir = new File(projectPath + File.separator + "PaLoanSystem" + File.separator + "Documents");
        if (!storageDir.exists()) {storageDir.mkdirs();};

        for (int i = 0; i < fileModel.getSize(); i++){
            File sourceFile = fileModel.getElementAt(i);

            try {
                String fileName = "LAP-" + applicationID + "-" + sourceFile.getName();
                File destFile = new File(storageDir, fileName);
                Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                 String documentType = "";
                if (i == 0) {
                    documentType = cbDocType1.getSelectedItem().toString();
                } else {
                    documentType = cbDocType2.getSelectedItem().toString();
                }

                LocalDateTime timestamp = LocalDateTime.now();

                String documentRefNo = ReferenceNumberGenerator.generateDocumentRefNo(5, documentType);

                String query = """
                    INSERT INTO Document (application_id, document_type, file_path, upload_date, verification_status, document_reference_number) VALUES
                        (?, ?, ?, ?, ?, ?)
                        """;

                try {
                    Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setInt(1, applicationID); System.out.println(applicationID);
                    ps.setString(2, documentType); System.out.println(documentType);
                    ps.setString(3, destFile.getPath()); System.out.println(destFile.getPath());
                    ps.setObject(4, timestamp); System.out.println(timestamp);
                    ps.setString(5, "For Approval");
                    ps.setString(6, documentRefNo);
                    ps.executeUpdate();
                    ps.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }catch (Exception e){
                e.printStackTrace();
            }

        }


                


    }


}
