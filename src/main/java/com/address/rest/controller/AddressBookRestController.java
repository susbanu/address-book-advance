package com.address.rest.controller;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.address.rest.dto.AddressDetailDto;
import com.address.rest.dto.AddressDto;
import com.address.service.AddressBookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Validated
public class AddressBookRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressBookRestController.class);
	
	@Autowired
	private AddressBookService addressBookService;
	
	@PostMapping("/address")
	@Operation(summary = "Save address", description = "Save one address", tags = { "AddressBook" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Address created", content = @Content(schema = @Schema(implementation = AddressDto.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input"),
			@ApiResponse(responseCode = "409", description = "Address already exists") })
	public ResponseEntity<AddressDto> saveAddress(@Valid @RequestBody AddressDto addressDto) {
		LOGGER.info("Save address : {}", addressDto);
		return new ResponseEntity<>(addressBookService.saveAddress(addressDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/address")
	@Operation(summary = "Search address", description = "Search address by any address fields", tags = { "AddressBook" })
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "successful operation",
	                content = @Content(schema = @Schema(implementation = AddressDetailDto.class))),
	        @ApiResponse(responseCode = "404", description = "Address not found for address field"),
	        @ApiResponse(responseCode = "400", description = "Address search field is empty")})
	public ResponseEntity<AddressDetailDto> searchAddress(@RequestParam(value = "field") @NotEmpty String searchField) {
		LOGGER.info("Search address field: {}", searchField);
		return new ResponseEntity<>(addressBookService.searchAddress(searchField), HttpStatus.OK);
	}
}
