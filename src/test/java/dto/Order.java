package dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class Order {

    private String firstName;
    private String lastName;
    private String address;
    private Integer metroStation;
    private String phone;
    private Integer rentTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate;
    private String comment;
    private List<String> color;

    public String getFirstName() {
        return firstName;
    }

    public Order setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Order setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Order setAddress(String address) {
        this.address = address;
        return this;
    }

    public Integer getMetroStation() {
        return metroStation;
    }

    public Order setMetroStation(Integer metroStation) {
        this.metroStation = metroStation;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Order setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Integer getRentTime() {
        return rentTime;
    }

    public Order setRentTime(Integer rentTime) {
        this.rentTime = rentTime;
        return this;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public Order setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Order setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public List<String> getColor() {
        return color;
    }

    public Order setColor(List<String> color) {
        this.color = color;
        return this;
    }
}
