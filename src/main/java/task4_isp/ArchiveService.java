package task4_isp;

public class ArchiveService {

    private final ScanDevice scanner;

    public ArchiveService(ScanDevice scanner) {
        this.scanner = scanner;
    }

    public void archive() {
        scanner.scanDocument();
    }
}
