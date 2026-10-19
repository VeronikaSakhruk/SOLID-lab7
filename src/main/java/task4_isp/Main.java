package task4_isp;

public class Main {

    public static void main(String[] args) {
        BasicLaserPrinter printer = new BasicLaserPrinter();
        DocumentScanner scanner = new DocumentScanner();
        OfficeMultiFunctionDevice multiFunctionDevice = new OfficeMultiFunctionDevice();

        new PrintService(printer).print("Report.pdf");
        new ArchiveService(scanner).archive();
        new FaxService(multiFunctionDevice).send("+380441234567", "Contract.pdf");
        new PrintService(multiFunctionDevice).print("Invoice.pdf");
    }
}
