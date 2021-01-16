package model;

/*
类描述：频率
 */
public class Frequency {
    private int n;//中心频率 193.1+n*0.00625 GHz
    private int m;//频谱宽度 12.5*m

    public Frequency(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    @Override
    public String toString() {
        return "Frequency{" +
                "n=" + n +
                ", m=" + m +
                '}';
    }

    @Override
    public int hashCode() {
        return 31 * n + m;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Frequency)) {
            return false;
        }
        Frequency frequency = (Frequency) obj;
        if(this == frequency) {
            return true;
        }
        return frequency.getN() == this.getN() && frequency.getM() == this.getM();
    }
}
