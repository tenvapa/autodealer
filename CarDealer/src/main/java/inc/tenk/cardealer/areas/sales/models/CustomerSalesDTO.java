package inc.tenk.cardealer.areas.sales.models;

public class CustomerSalesDTO {
    private String name;
    private Long boughtCars;
    private double totalSpentMoney;

    public CustomerSalesDTO() {
    }

    public CustomerSalesDTO(String name) {
        this.name = name;
    }

    public CustomerSalesDTO(String name, Long boughtCars, double totalSpentMoney) {
        this.name = name;
        this.boughtCars = boughtCars;
        this.totalSpentMoney = totalSpentMoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(Long boughtCars) {
        this.boughtCars = boughtCars;
    }

    public double getTotalSpentMoney() {
        return totalSpentMoney;
    }

    public void setTotalSpentMoney(double totalSpentMoney) {
        this.totalSpentMoney = totalSpentMoney;
    }
}
