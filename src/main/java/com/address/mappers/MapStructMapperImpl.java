package com.address.mappers;

import com.address.repository.entity.AddressEntity;
import com.address.rest.dto.AddressDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-07T15:11:05+0530",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public AddressDto addressEntityToAddressDto(AddressEntity addressEntity) {
        if ( addressEntity == null ) {
            return null;
        }

        AddressDto addressDto = new AddressDto();

        addressDto.setFirstName( addressEntity.getFirstName() );
        addressDto.setLastName( addressEntity.getLastName() );
        addressDto.setStreet( addressEntity.getStreet() );
        addressDto.setCity( addressEntity.getCity() );
        addressDto.setState( addressEntity.getState() );
        addressDto.setZip( addressEntity.getZip() );

        return addressDto;
    }

    @Override
    public AddressEntity addressDtoToAddressEntity(AddressDto addressDto) {
        if ( addressDto == null ) {
            return null;
        }

        AddressEntity addressEntity = new AddressEntity();

        addressEntity.setFirstName( addressDto.getFirstName() );
        addressEntity.setLastName( addressDto.getLastName() );
        addressEntity.setStreet( addressDto.getStreet() );
        addressEntity.setCity( addressDto.getCity() );
        addressEntity.setState( addressDto.getState() );
        addressEntity.setZip( addressDto.getZip() );

        return addressEntity;
    }

    @Override
    public List<AddressDto> addressEntityToAddressDto(List<AddressEntity> addressEntities) {
        if ( addressEntities == null ) {
            return null;
        }

        List<AddressDto> list = new ArrayList<AddressDto>( addressEntities.size() );
        for ( AddressEntity addressEntity : addressEntities ) {
            list.add( addressEntityToAddressDto( addressEntity ) );
        }

        return list;
    }

    @Override
    public List<AddressEntity> addressDtoToAddressEntity(List<AddressDto> addressDtos) {
        if ( addressDtos == null ) {
            return null;
        }

        List<AddressEntity> list = new ArrayList<AddressEntity>( addressDtos.size() );
        for ( AddressDto addressDto : addressDtos ) {
            list.add( addressDtoToAddressEntity( addressDto ) );
        }

        return list;
    }
}
