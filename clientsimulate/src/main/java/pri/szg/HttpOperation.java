package pri.szg;

import model.AlarmState;
import model.Frequency;
import model.Port;

import java.util.List;

public interface HttpOperation {
    Frequency getFreq(int portId);

    boolean setFreq(int portId, Frequency frequency);

    Port getPort(int portId);

    List<Port> getPortsByState(AlarmState state);

    boolean configNewPort(Port port);
}
