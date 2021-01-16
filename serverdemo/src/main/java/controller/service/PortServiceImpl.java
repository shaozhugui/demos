package controller.service;

import api.PortService;
import api.SetFreqRequest;
import model.AlarmState;
import model.Frequency;
import model.Port;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PortServiceImpl implements PortService {
    private final static Logger LOG = LogManager.getLogger(PortServiceImpl.class);
    private static HashMap<Integer, Port> portHashMap = DataStoreSimu.portMap;

    public Frequency getFreq(int portId) {
        Frequency freq = null;
        if (portHashMap.containsKey(portId)) {
            freq = portHashMap.get(portId).getFreq();
        }
        LOG.debug("get port [id = {}] frequency is : {}", portId, freq);
        return freq;
    }

    @Override
    public boolean setFreq(SetFreqRequest request) {
        Frequency freq = null;
        int portId = request.getPortId();
        Frequency frequency = request.getFreq();
        if (null == frequency) {
            LOG.error("frequency is null.");
            return false;
        }
        if (!portHashMap.containsKey(portId)) {
            LOG.error("port [id = {}] is absent.", portId);
            return false;
        }
        freq = portHashMap.get(portId).getFreq();
        portHashMap.get(portId).setFreq(frequency);
        LOG.debug("set freq from {} to {} for port [id = {}]", freq, frequency, portId);
        return true;
    }

    public Port getPort(int portId) {
        Port port = portHashMap.get(portId);
        LOG.debug("get port of id = {}, the port is {}", portId, port);
        return port;
    }

    public List<Port> getPortsByState(AlarmState state) {
        List<Port> ports = portHashMap.values().stream().filter(port -> port.getState() == state)
                .collect(Collectors.toList());
        LOG.debug("get ports of state is {}, ports is {}", state, ports);
        return ports;
    }

    public boolean configNewPort(Port port) {
        if (portHashMap.containsKey(port.getId())) {
            LOG.error("port id = {} is configed, should not be config again.", port.getId());
            return false;
        }
        Port portTemp = new Port(port);
        portHashMap.put(port.getId(), port);
        LOG.debug("config new port: [{}] success.", port);
        return true;
    }
}
