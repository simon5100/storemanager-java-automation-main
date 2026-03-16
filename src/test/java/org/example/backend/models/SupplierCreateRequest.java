package org.example.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("contact_name")
    private String contactName;

    @JsonProperty("contact_email")
    private String contactEmail;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("address")
    private String address;

    @JsonProperty("country")
    private String country;

    @JsonProperty("city")
    private String city;

    @JsonProperty("website")
    private String website;
}