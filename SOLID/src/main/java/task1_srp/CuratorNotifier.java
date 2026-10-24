package task1_srp;

public class CuratorNotifier {

    public void notify(String curatorEmail, Student student, String transcript) {
        if (curatorEmail == null || curatorEmail.isBlank()) {
            return;
        }
        System.out.println("Sending mail to curator " + curatorEmail + " via mail.univ.local");
        System.out.println("Subject: Student performance " + student.name());
        System.out.println("Body: " + transcript);
    }
}
