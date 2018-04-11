package inc.tenk.cardealer.areas.sales.models;

public class SaleDTO {
    private String carMake;
    private String carModel;
    private long carTravelledDistance;
    private String customerName;
    private double saleAmount;
    private int discount;

    public SaleDTO() {
    }

    public SaleDTO(String carMake, String carModel, long carTravelledDistance, String customerName, double saleAmount, double discount) {
        this.carMake = carMake;
        this.carModel = carModel;
        this.carTravelledDistance = carTravelledDistance;
        this.customerName = customerName;
        this.saleAmount = saleAmount;
        this.discount = (int) discount;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public long getCarTravelledDistance() {
        return carTravelledDistance;
    }

    public void setCarTravelledDistance(long carTravelledDistance) {
        this.carTravelledDistance = carTravelledDistance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
