package CC_02_Wed_12_Group;

public class Rate {
    private String name;
    private float rate;
    private int upOrDown;
    private int count;

    public Rate(String countryName, float rate) {
        this.name = countryName;
        this.rate = rate;
        this.upOrDown = 0;
        this.count = 0;
    }

    public String getName() {
        return name;
    }

    public float getRate() {
        return this.rate;
    }

    public void setRate(float newRate) {
        this.rate = newRate;
    }

    public String getUpOrDown() {
        if (this.upOrDown == 0) {
            return "   ";
        }
        else if (this.upOrDown == 1) {
            return "(D)";
        }
        return "(I)";
    }

    public int getCount() {
        return this.count;
    }

    public void setUpOrDown(int newUpOrDown){
        this.upOrDown = newUpOrDown;
    }

    public void increaseCount(){
        this.count++;
    }
}
