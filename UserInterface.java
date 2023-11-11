import java.io.IOException;
import java.util.Scanner;

public class UserInterface {

    private final UserLoginSystem loginSystem;
    private final Scanner scanner;

    public UserInterface(UserLoginSystem loginSystem) {
        this.loginSystem = loginSystem;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the CAMS Login System");
        String userID = null; // Declare userID outside the loop

        while (true) {
            System.out.print("Enter your user ID: ");
            userID = scanner.nextLine(); // userID assigned a value here
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            if (loginSystem.authenticateUser(userID, password)) {
                System.out.println("Login successful!");
                System.out.println("Faculty: " + loginSystem.getFaculty(userID));
                break; // Exit loop after successful login
            } else {
                System.out.println("Login failed. Please check your credentials.");
            }
        }

        // Check if user is logged in before changing password
        if (userID != null) {
            System.out.print("Do you want to change your password? (yes/no): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("yes")) {
                System.out.print("Enter your old password: ");
                String oldPassword = scanner.nextLine();
                System.out.print("Enter your new password: ");
                String newPassword = scanner.nextLine();
                loginSystem.changePassword(userID, oldPassword, newPassword);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Program started.");
        UserLoginSystem loginSystem = new UserLoginSystem();
        try {
            loginSystem.loadUserCredentials("staff_list.csv", "student_list.csv");
            new UserInterface(loginSystem).start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while trying to read user credentials.");
        }
    }
}


