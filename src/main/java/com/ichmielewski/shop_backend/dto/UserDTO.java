package com.ichmielewski.shop_backend.dto;

import com.ichmielewski.shop_backend.pdf.annotation.HeaderName;
import com.ichmielewski.shop_backend.pdf.annotation.Title;

@Title("Users")
public class UserDTO extends AbstractDTO {
    @HeaderName("e-mail")
    private String email;
    @HeaderName("first name")
    private String firstName;
    @HeaderName("second name")
    private String secondName;
    private String street;
    private String city;
    private String country;
    @HeaderName("postal code")
    private String postalCode;
    @HeaderName("phone")
    private String phoneNumber;

    public UserDTO() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
