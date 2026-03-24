package org.example.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.utils.DataUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "supplierId")
public class SupplierCreateModel {

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

    @JsonProperty("supplier_id")
    private String supplierId;

    public static SupplierCreateModel generate() {

        String timestamp = String.valueOf(System.currentTimeMillis());
        return SupplierCreateModel.builder()
                .name(DataUtils.generate(8))
                .contactName(DataUtils.generate(8))
                .contactEmail(timestamp + "@test.com")
                .phoneNumber("+1234567" + timestamp.substring(0, 4))
                .address("Test Address " + timestamp)
                .country("TestCountry")
                .city("TestCity")
                .website("https://" + timestamp + "-test.com")
                .build();
    }
}