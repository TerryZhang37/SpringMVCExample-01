package com.example.servingwebcontent.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CountrySearchForm {

    @NotBlank(message = "ID should not be blank")
    private String mstCountrycd;

    @NotBlank(message = "Name should not be blank")
    private String mstcountrynanme;

    public CountrySearchForm() {
    }

    public CountrySearchForm(String mstCountryCd,String mstcountrynanme) {
        this.mstCountrycd = mstCountryCd;
        this.mstcountrynanme = mstcountrynanme;
    }
}