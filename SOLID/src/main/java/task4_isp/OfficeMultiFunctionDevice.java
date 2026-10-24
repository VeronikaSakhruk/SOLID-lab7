package task4_isp;

public class OfficeMultiFunctionDevice implements PrintDevice, DuplexPrintDevice, ScanDevice, FaxDevice, StapleDevice {

    @Override
    public void printDocument(String doc) {
        System.out.println("Printing: " + doc);
    }

    @Override
    public void duplexPrint(String doc) {
        System.out.println("Duplex printing: " + doc);
    }

    @Override
    public void scanDocument() {
        System.out.println("Scanning physical document...");
    }

    @Override
    public void sendFax(String number, String doc) {
        System.out.println("Sending fax to " + number + ": " + doc);
    }

    @Override
    public void staplePages(int count) {
        System.out.println("Stapling " + count + " pages.");
    }
}
