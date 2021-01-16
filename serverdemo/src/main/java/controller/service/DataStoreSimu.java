package controller.service;

import model.AlarmState;
import model.Frequency;
import model.Port;

import java.util.HashMap;

class DataStoreSimu {
    final static HashMap<Integer, Port> portMap = new HashMap<>();

    static {
        Port port1 = new Port(1);
        port1.setLocation("1-2-1");
        port1.setOsnr(70);
        port1.setPower(-2);
        port1.setState(AlarmState.NORMAL);
        port1.setFreq(new Frequency(0, 8));

        Port port2 = new Port(2);
        port2.setLocation("1-2-2");
        port2.setOsnr(65);
        port2.setPower(-12);
        port2.setState(AlarmState.SD);
        port2.setFreq(new Frequency(6, 6));

        Port port3 = new Port(3);
        port3.setLocation("1-2-3");
        port3.setOsnr(20);
        port3.setPower(-14);
        port3.setState(AlarmState.SF);
        port3.setFreq(new Frequency(-16, 6));

        Port port4 = new Port(4);
        port4.setLocation("1-2-4");
        port4.setOsnr(65);
        port4.setPower(-3);
        port4.setState(AlarmState.NORMAL);
        port4.setFreq(new Frequency(10, 6));

        portMap.put(port1.getId(), port1);
        portMap.put(port2.getId(), port2);
        portMap.put(port3.getId(), port3);
        portMap.put(port4.getId(), port4);
    }
}
