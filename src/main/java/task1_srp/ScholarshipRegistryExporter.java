package task1_srp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ScholarshipRegistryExporter {

    private final String filePath;

    public ScholarshipRegistryExporter(String filePath) {
        this.filePath = filePath;
    }

    public void export(Student student, double gpa, BigDecimal scholarship) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String line = String.format("%s;%s;%.2f;%s;%s%n", student.id(), student.name(), gpa, scholarship, date);
        try (FileOutputStream stream = new FileOutputStream(filePath, true)) {
            stream.write(line.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.err.println("CSV export error: " + e.getMessage());
        }
    }
}
