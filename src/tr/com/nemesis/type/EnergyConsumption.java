package tr.com.nemesis.type;

import java.util.Date;

public class EnergyConsumption extends Device{

    private Date timePeriod;
    private float quantity;
    private String energyType;


    public Date getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(Date timePeriod) {
        this.timePeriod = timePeriod;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }


    @Override
    public int getDeviceID() {
        return super.getDeviceID();
    }

    @Override
    public void setDeviceID(int deviceID) {
        super.setDeviceID(deviceID);
    }
}
