package DTO.Inside;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Restaurant {
    private String nameRestaurant;
    private String phoneRestaurant;
    private String emailRestaurant;
    private String passwordRestaurant;

    public String getNameRestaurant() {
        return nameRestaurant;
    }

    public void setNameRestaurant(String nameRestaurant) {
        this.nameRestaurant = nameRestaurant;
    }

    public String getPhoneRestaurant() {
        return phoneRestaurant;
    }

    public void setPhoneRestaurant(String phoneRestaurant) {
        this.phoneRestaurant = phoneRestaurant;
    }

    public String getEmailRestaurant() {
        return emailRestaurant;
    }

    public void setEmailRestaurant(String emailRestaurant) {
        this.emailRestaurant = emailRestaurant;
    }

    public String getPasswordRestaurant() {
        return passwordRestaurant;
    }

    public void setPasswordRestaurant(String passwordRestaurant) {
        this.passwordRestaurant = passwordRestaurant;
    }
}
