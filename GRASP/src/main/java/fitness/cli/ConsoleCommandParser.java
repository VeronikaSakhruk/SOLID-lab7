package fitness.cli;

import fitness.application.RegistrationRequest;
import java.util.Scanner;

public class ConsoleCommandParser {
    private final Scanner scanner = new Scanner(System.in);

    public RegistrationRequest read() {
        System.out.print("Enter client name: ");
        String name = scanner.nextLine();
        System.out.print("Enter plan type (Day/Full/VIP): ");
        String planType = scanner.nextLine();
        return new RegistrationRequest(name, planType);
    }
}
