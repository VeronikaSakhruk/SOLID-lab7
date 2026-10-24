package task4_isp;

public class FaxService {

    private final FaxDevice faxDevice;

    public FaxService(FaxDevice faxDevice) {
        this.faxDevice = faxDevice;
    }

    public void send(String number, String doc) {
        faxDevice.sendFax(number, doc);
    }
}
