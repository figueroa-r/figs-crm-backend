package com.rfigueroa.figscrm.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CustomerDTO {

    private String avatarUrl;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Customer name must be between 3 and 50 characters long")
    private String name;
    private String alias;
    private String companyType;

    @NotNull(message = "isActive must have a true or false value")
    private Boolean isActive;

    @NotNull(message = "isVerified must have a true or false value")
    private Boolean isVerified;

    @NotBlank(message = "Address 1 is required")
    @Size(min = 5, message = "Address must be at least 5 characters in length")
    private String address1;
    private String address2;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Zip is required")
    @Pattern(
            regexp = "^(?!0{5})(\\d{5})(?!-?0{4})(-?\\d{4})?$",
            message = "Please enter a valid 5 or 9 digit zip code")
    private String zip;

    public CustomerDTO(String avatarUrl,
                       String name,
                       String alias,
                       String companyType,
                       Boolean isActive,
                       Boolean isVerified,
                       String address1,
                       String address2,
                       String city,
                       String state,
                       String zip) {
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.alias = alias;
        this.companyType = companyType;
        this.isActive = isActive;
        this.isVerified = isVerified;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public CustomerDTO() {
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean verified) {
        isVerified = verified;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
