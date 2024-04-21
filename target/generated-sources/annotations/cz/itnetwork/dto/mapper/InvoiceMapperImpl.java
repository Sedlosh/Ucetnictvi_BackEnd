package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public InvoiceEntity toEntity(InvoiceDTO source) {
        if ( source == null ) {
            return null;
        }

        InvoiceEntity invoiceEntity = new InvoiceEntity();

        invoiceEntity.setId( source.getId() );
        invoiceEntity.setInvoiceNumber( source.getInvoiceNumber() );
        invoiceEntity.setIssued( source.getIssued() );
        invoiceEntity.setDueDate( source.getDueDate() );
        invoiceEntity.setProduct( source.getProduct() );
        if ( source.getPrice() != null ) {
            invoiceEntity.setPrice( source.getPrice() );
        }
        invoiceEntity.setVat( source.getVat() );
        invoiceEntity.setNote( source.getNote() );
        invoiceEntity.setBuyer( personDTOToPersonEntity( source.getBuyer() ) );
        invoiceEntity.setSeller( personDTOToPersonEntity( source.getSeller() ) );

        return invoiceEntity;
    }

    @Override
    public InvoiceDTO toDTO(InvoiceEntity source) {
        if ( source == null ) {
            return null;
        }

        InvoiceDTO invoiceDTO = new InvoiceDTO();

        invoiceDTO.setId( source.getId() );
        invoiceDTO.setInvoiceNumber( source.getInvoiceNumber() );
        invoiceDTO.setIssued( source.getIssued() );
        invoiceDTO.setDueDate( source.getDueDate() );
        invoiceDTO.setProduct( source.getProduct() );
        invoiceDTO.setPrice( source.getPrice() );
        invoiceDTO.setVat( source.getVat() );
        invoiceDTO.setNote( source.getNote() );
        invoiceDTO.setBuyer( personEntityToPersonDTO( source.getBuyer() ) );
        invoiceDTO.setSeller( personEntityToPersonDTO( source.getSeller() ) );

        return invoiceDTO;
    }

    @Override
    public void updateInvoiceEntity(InvoiceDTO source, InvoiceEntity target) {
        if ( source == null ) {
            return;
        }

        target.setId( source.getId() );
        target.setInvoiceNumber( source.getInvoiceNumber() );
        target.setIssued( source.getIssued() );
        target.setDueDate( source.getDueDate() );
        target.setProduct( source.getProduct() );
        if ( source.getPrice() != null ) {
            target.setPrice( source.getPrice() );
        }
        target.setVat( source.getVat() );
        target.setNote( source.getNote() );
    }

    protected PersonEntity personDTOToPersonEntity(PersonDTO personDTO) {
        if ( personDTO == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        if ( personDTO.getId() != null ) {
            personEntity.setId( personDTO.getId() );
        }
        personEntity.setName( personDTO.getName() );
        personEntity.setIdentificationNumber( personDTO.getIdentificationNumber() );
        personEntity.setTaxNumber( personDTO.getTaxNumber() );
        personEntity.setAccountNumber( personDTO.getAccountNumber() );
        personEntity.setBankCode( personDTO.getBankCode() );
        personEntity.setIban( personDTO.getIban() );
        personEntity.setTelephone( personDTO.getTelephone() );
        personEntity.setMail( personDTO.getMail() );
        personEntity.setStreet( personDTO.getStreet() );
        personEntity.setZip( personDTO.getZip() );
        personEntity.setCity( personDTO.getCity() );
        personEntity.setCountry( personDTO.getCountry() );
        personEntity.setNote( personDTO.getNote() );

        return personEntity;
    }

    protected PersonDTO personEntityToPersonDTO(PersonEntity personEntity) {
        if ( personEntity == null ) {
            return null;
        }

        PersonDTO personDTO = new PersonDTO();

        personDTO.setId( personEntity.getId() );
        personDTO.setName( personEntity.getName() );
        personDTO.setIdentificationNumber( personEntity.getIdentificationNumber() );
        personDTO.setTaxNumber( personEntity.getTaxNumber() );
        personDTO.setAccountNumber( personEntity.getAccountNumber() );
        personDTO.setBankCode( personEntity.getBankCode() );
        personDTO.setIban( personEntity.getIban() );
        personDTO.setTelephone( personEntity.getTelephone() );
        personDTO.setMail( personEntity.getMail() );
        personDTO.setStreet( personEntity.getStreet() );
        personDTO.setZip( personEntity.getZip() );
        personDTO.setCity( personEntity.getCity() );
        personDTO.setCountry( personEntity.getCountry() );
        personDTO.setNote( personEntity.getNote() );

        return personDTO;
    }
}
