package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.entity.PersonEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public PersonEntity toEntity(PersonDTO source) {
        if ( source == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        if ( source.getId() != null ) {
            personEntity.setId( source.getId() );
        }
        personEntity.setName( source.getName() );
        personEntity.setIdentificationNumber( source.getIdentificationNumber() );
        personEntity.setTaxNumber( source.getTaxNumber() );
        personEntity.setAccountNumber( source.getAccountNumber() );
        personEntity.setBankCode( source.getBankCode() );
        personEntity.setIban( source.getIban() );
        personEntity.setTelephone( source.getTelephone() );
        personEntity.setMail( source.getMail() );
        personEntity.setStreet( source.getStreet() );
        personEntity.setZip( source.getZip() );
        personEntity.setCity( source.getCity() );
        personEntity.setCountry( source.getCountry() );
        personEntity.setNote( source.getNote() );

        return personEntity;
    }

    @Override
    public PersonDTO toDTO(PersonEntity source) {
        if ( source == null ) {
            return null;
        }

        PersonDTO personDTO = new PersonDTO();

        personDTO.setId( source.getId() );
        personDTO.setName( source.getName() );
        personDTO.setIdentificationNumber( source.getIdentificationNumber() );
        personDTO.setTaxNumber( source.getTaxNumber() );
        personDTO.setAccountNumber( source.getAccountNumber() );
        personDTO.setBankCode( source.getBankCode() );
        personDTO.setIban( source.getIban() );
        personDTO.setTelephone( source.getTelephone() );
        personDTO.setMail( source.getMail() );
        personDTO.setStreet( source.getStreet() );
        personDTO.setZip( source.getZip() );
        personDTO.setCity( source.getCity() );
        personDTO.setCountry( source.getCountry() );
        personDTO.setNote( source.getNote() );

        return personDTO;
    }
}
