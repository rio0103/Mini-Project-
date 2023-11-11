import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class UserLoginSystem {

    private HashMap<String, String> userCredentials = new HashMap<>();
    private HashMap<String, String> userFaculties = new HashMap<>();

    public void loadUserCredentials(String staffFilePath, String studentFilePath) throws IOException {
        // Load staff and student credentials from provided file paths
        loadCredentialsFromCSV(staffFilePath);
        loadCredentialsFromCSV(studentFilePath);
    }

    private void loadCredentialsFromCSV(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Change this if your delimiter is different
                if (parts.length >= 3) {
                    String email = parts[1].trim();
                    String userID = email.substring(0, email.indexOf('@'));
                    String faculty = parts[2].trim();
                    userCredentials.put(userID, "password");
                    userFaculties.put(userID, faculty);
                }
            }
        }
    }

    public boolean authenticateUser(String userID, String password) {
        String realPassword = userCredentials.get(userID);
        return realPassword != null && realPassword.equals(password);
    }

    public void changePassword(String userID, String oldPassword, String newPassword) {
        if (authenticateUser(userID, oldPassword)) {
            userCredentials.put(userID, newPassword);
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Old password is incorrect.");
        }
    }

    public String getFaculty(String userID) {
        return userFaculties.get(userID);
    }
}






