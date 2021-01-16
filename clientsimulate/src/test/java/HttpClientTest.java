import model.AlarmState;
import model.Frequency;
import model.Port;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pri.szg.HttpOperation;
import pri.szg.HttpOperationImpl;

import java.util.List;

public class HttpClientTest {
    private HttpOperation operation;

    @BeforeTest
    private void setup() {
        operation = new HttpOperationImpl("127.0.0.1", "8081");
    }

    @Test
    private void getFreqTest() {
        Frequency frequency = operation.getFreq(1);
        Frequency expectFreq = new Frequency(0, 8);
        Assert.assertEquals(frequency, expectFreq);
    }

    @Test
    private void setFreqTest() {
        Frequency freq = new Frequency(2, 8);
        boolean result = operation.setFreq(1, freq);
        Assert.assertTrue(result);
        Assert.assertEquals(operation.getFreq(1), freq);
    }

    @Test
    private void getPortTest() {
        Port port = operation.getPort(2);
        Port port2 = new Port(2);
        port2.setLocation("1-2-2");
        port2.setOsnr(65);
        port2.setPower(-12);
        port2.setState(AlarmState.SD);
        port2.setFreq(new Frequency(6, 6));
        Assert.assertEquals(port, port2);
    }

    @Test
    private void getPortsByStateTest() {
        List<Port> ports = operation.getPortsByState(AlarmState.NORMAL);
        Assert.assertEquals(ports.size(), 2);
    }

    @Test
    private void configPort() {
        Port port2 = new Port(6);
        port2.setLocation("1-2-6");
        port2.setPower(2);
        port2.setFreq(new Frequency(18, 6));
        Assert.assertTrue(operation.configNewPort(port2));
        Assert.assertEquals(operation.getPort(port2.getId()), port2);
    }
}
