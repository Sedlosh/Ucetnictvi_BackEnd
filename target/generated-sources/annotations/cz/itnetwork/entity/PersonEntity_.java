package cz.itnetwork.entity;

import cz.itnetwork.constant.Countries;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PersonEntity.class)
public abstract class PersonEntity_ {

	public static volatile SingularAttribute<PersonEntity, String> zip;
	public static volatile SingularAttribute<PersonEntity, String> bankCode;
	public static volatile SingularAttribute<PersonEntity, Countries> country;
	public static volatile SingularAttribute<PersonEntity, String> note;
	public static volatile SingularAttribute<PersonEntity, String> mail;
	public static volatile SingularAttribute<PersonEntity, Boolean> hidden;
	public static volatile SingularAttribute<PersonEntity, String> city;
	public static volatile ListAttribute<PersonEntity, InvoiceEntity> purchases;
	public static volatile SingularAttribute<PersonEntity, String> taxNumber;
	public static volatile SingularAttribute<PersonEntity, String> telephone;
	public static volatile SingularAttribute<PersonEntity, String> accountNumber;
	public static volatile ListAttribute<PersonEntity, InvoiceEntity> sales;
	public static volatile SingularAttribute<PersonEntity, String> street;
	public static volatile SingularAttribute<PersonEntity, String> iban;
	public static volatile SingularAttribute<PersonEntity, String> name;
	public static volatile SingularAttribute<PersonEntity, String> identificationNumber;
	public static volatile SingularAttribute<PersonEntity, Long> id;

	public static final String ZIP = "zip";
	public static final String BANK_CODE = "bankCode";
	public static final String COUNTRY = "country";
	public static final String NOTE = "note";
	public static final String MAIL = "mail";
	public static final String HIDDEN = "hidden";
	public static final String CITY = "city";
	public static final String PURCHASES = "purchases";
	public static final String TAX_NUMBER = "taxNumber";
	public static final String TELEPHONE = "telephone";
	public static final String ACCOUNT_NUMBER = "accountNumber";
	public static final String SALES = "sales";
	public static final String STREET = "street";
	public static final String IBAN = "iban";
	public static final String NAME = "name";
	public static final String IDENTIFICATION_NUMBER = "identificationNumber";
	public static final String ID = "id";

}

