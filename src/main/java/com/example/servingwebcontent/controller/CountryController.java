package com.example.servingwebcontent.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.servingwebcontent.entity.CountryEntity;
import com.example.servingwebcontent.form.CountrySearchForm;
import com.example.servingwebcontent.repository.CountryEntityMapper;
import com.google.gson.Gson;

@Controller
public class CountryController {

	@Autowired
	private CountryEntityMapper mapper;

	@GetMapping("/country")
	public String init(CountrySearchForm countrySearchForm) {

		return "country/country";
	}

	/**
	 * Represents a sequence of characters. In this context, it is used to return a
	 * JSON representation of a CountryEntity object.
	 */
	@PostMapping("/country/getCountry")
	@ResponseBody
	public String getCountry(@Validated CountrySearchForm countrySearchForm, BindingResult bindingResult) {

		/**
		 * Optional object containing the result of the database query for the country
		 * with the specified country code.
		 */
		Optional<CountryEntity> countryEntity = mapper.selectByPrimaryKey(countrySearchForm.getMstCountrycd());
		if (countryEntity == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return new Gson().toJson(countryEntity.get());
	}

	@PostMapping("/country/editCountry")
	@ResponseBody
	public String editCountry(@Validated CountrySearchForm countryListForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "country";
		}

		CountryEntity countryEntity = new CountryEntity();
		countryEntity.setMstcountrycd(countryListForm.getMstCountrycd());
		countryEntity.setMstcountrynanme(countryListForm.getMstcountrynanme());
		mapper.updateByPrimaryKey(countryEntity);
		return "country";
	}

	@PostMapping("/country/addCountry")
	@ResponseBody
	public String addCustomer(@Validated CountrySearchForm countryListForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "country";
		}

		CountryEntity countryEntity = new CountryEntity();
		countryEntity.setMstcountrycd(countryListForm.getMstCountrycd());
		countryEntity.setMstcountrynanme(countryListForm.getMstcountrynanme());
		mapper.insert(countryEntity);
		return "country";
	}

	@DeleteMapping("/country/del/{id}")
	public String deleteItem(@PathVariable("id") String id) {
		mapper.deleteByPrimaryKey(id);
		return "country";
	}

	/*
	 * 创建一个方法，监听/country/createCountry，
	 * 实现根据请求的参数创建一个CountryEntity对象，并将其插入到数据库中。
	 */
	@PostMapping("/country/createCountry")
	@ResponseBody
	public String createCountry(@RequestBody CountryEntity countryEntity) {
		// Method body goes here
		// For example, you might save the countryEntity to the database
		// Then return a success message or the saved entity
		return "Country created successfully";
	}

}
