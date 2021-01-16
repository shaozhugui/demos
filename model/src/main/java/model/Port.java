package model;

public class Port {
    private String location;
    private int power;
    private int osnr;
    private int id;
    private Frequency freq;
    private AlarmState state;

    public Port(int id) {
        this.id = id;
    }

    public Port(String location, int power, int osnr, int id, Frequency freq, AlarmState state) {
        this.location = location;
        this.power = power;
        this.osnr = osnr;
        this.id = id;
        this.freq = freq;
        this.state = state;
    }

    public Port(Port base) {
        this.id = base.getId();
        this.location = base.getLocation();
        this.power = base.getPower();
        this.osnr = base.getOsnr();
        this.freq = base.getFreq();
        this.state = base.getState();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getOsnr() {
        return osnr;
    }

    public void setOsnr(int osnr) {
        this.osnr = osnr;
    }

    public AlarmState getState() {
        return state;
    }

    public void setState(AlarmState state) {
        this.state = state;
    }

    public Frequency getFreq() {
        return freq;
    }

    public void setFreq(Frequency freq) {
        this.freq = freq;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Port{" +
                "location='" + location + '\'' +
                ", power=" + power +
                ", osnr=" + osnr +
                ", id=" + id +
                ", freq=" + freq +
                ", state=" + state +
                '}';
    }

    @Override
    public int hashCode() {
        int result = location.hashCode();
        result = 31 * result + power;
        result = 31 * result + osnr;
        result = 31 * result + id;
        result = 31 * result + freq.hashCode();
        result = 31 * result + state.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Port)) {
            return false;
        }
        Port port = (Port) obj;
        if (port == this) {
            return true;
        }
        if (!port.getLocation().equals(this.getLocation())) {
            return false;
        }
        if (port.getPower() != this.getPower()) {
            return false;
        }
        if (port.getOsnr() != this.getOsnr()) {
            return false;
        }
        if (port.getId() != this.getId()) {
            return false;
        }
        if (!port.getFreq().equals(this.getFreq())) {
            return false;
        }
        return port.getState() == this.getState();
    }
}

