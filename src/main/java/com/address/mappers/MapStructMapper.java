package com.address.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.address.repository.entity.AddressEntity;
import com.address.rest.dto.AddressDto;


@Mapper(componentModel = "spring")
public interface MapStructMapper {

	AddressDto addressEntityToAddressDto(AddressEntity addressEntity);
	
	AddressEntity addressDtoToAddressEntity(AddressDto addressDto);
	
	List<AddressDto> addressEntityToAddressDto(List<AddressEntity> addressEntities);
	
	List<AddressEntity> addressDtoToAddressEntity(List<AddressDto> addressDtos);
}
