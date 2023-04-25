package com.rfigueroa.figscrm.dto;

public class ContactDetailDTO {

    private Integer id;
    private String contactType;
    private String contactDetail;

    public ContactDetailDTO() {
    }

    public ContactDetailDTO(Integer id, String contactType, String contactDetail) {
        this.id = id;
        this.contactType = contactType;
        this.contactDetail = contactDetail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(String contactDetail) {
        this.contactDetail = contactDetail;
    }
}
