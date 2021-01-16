package api;

import model.AlarmState;
import model.Frequency;
import model.Port;

import java.util.List;

public interface PortService {
    Frequency getFreq(int portId);

    boolean setFreq(SetFreqRequest request);

    Port getPort(int portId);

    List<Port> getPortsByState(AlarmState state);

    boolean configNewPort(Port port);
}
