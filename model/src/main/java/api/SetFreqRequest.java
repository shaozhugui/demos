package api;

import model.Frequency;

public class SetFreqRequest {
    private int portId;
    private Frequency freq;

    public SetFreqRequest(int portId, Frequency freq) {
        this.portId = portId;
        this.freq = freq;
    }

    public int getPortId() {
        return portId;
    }

    public void setPortId(int portId) {
        this.portId = portId;
    }

    public Frequency getFreq() {
        return freq;
    }

    public void setFreq(Frequency freq) {
        this.freq = freq;
    }
}
