package cz.itnetwork.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InvoiceEntity.class)
public abstract class InvoiceEntity_ {

	public static volatile SingularAttribute<InvoiceEntity, PersonEntity> seller;
	public static volatile SingularAttribute<InvoiceEntity, String> note;
	public static volatile SingularAttribute<InvoiceEntity, String> product;
	public static volatile SingularAttribute<InvoiceEntity, Long> price;
	public static volatile SingularAttribute<InvoiceEntity, Date> dueDate;
	public static volatile SingularAttribute<InvoiceEntity, Integer> invoiceNumber;
	public static volatile SingularAttribute<InvoiceEntity, Integer> vat;
	public static volatile SingularAttribute<InvoiceEntity, Long> id;
	public static volatile SingularAttribute<InvoiceEntity, Date> issued;
	public static volatile SingularAttribute<InvoiceEntity, PersonEntity> buyer;

	public static final String SELLER = "seller";
	public static final String NOTE = "note";
	public static final String PRODUCT = "product";
	public static final String PRICE = "price";
	public static final String DUE_DATE = "dueDate";
	public static final String INVOICE_NUMBER = "invoiceNumber";
	public static final String VAT = "vat";
	public static final String ID = "id";
	public static final String ISSUED = "issued";
	public static final String BUYER = "buyer";

}

