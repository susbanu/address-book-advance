package com.address.service;

import com.address.rest.dto.AddressDetailDto;
import com.address.rest.dto.AddressDto;

public interface AddressBookService {

	public AddressDto saveAddress(AddressDto addressDto);
	
	public AddressDetailDto searchAddress(String addressField);
}
