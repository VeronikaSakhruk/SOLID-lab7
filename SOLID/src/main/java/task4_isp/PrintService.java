package task4_isp;

public class PrintService {

    private final PrintDevice printer;

    public PrintService(PrintDevice printer) {
        this.printer = printer;
    }

    public void print(String doc) {
        printer.printDocument(doc);
    }
}
