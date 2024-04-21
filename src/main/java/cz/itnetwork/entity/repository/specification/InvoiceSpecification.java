package cz.itnetwork.entity.repository.specification;

import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.InvoiceEntity_;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.PersonEntity_;
import cz.itnetwork.entity.filter.InvoiceFilter;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class InvoiceSpecification implements Specification<InvoiceEntity> {

    private final InvoiceFilter filter;

    @Override
    public Predicate toPredicate(Root<InvoiceEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getSellerID() != null) {
            Join<PersonEntity, InvoiceEntity> sellerJoin = root.join(InvoiceEntity_.SELLER);
            predicates.add(criteriaBuilder.equal(sellerJoin.get(PersonEntity_.ID), filter.getSellerID()));
        }

        if (filter.getBuyerID() != null) {
            Join<PersonEntity, InvoiceEntity> buyerJoin = root.join(InvoiceEntity_.BUYER);
            predicates.add(criteriaBuilder.equal(buyerJoin.get(PersonEntity_.ID), filter.getBuyerID()));
        }

        if (filter.getProduct() != null) {
            predicates.add(criteriaBuilder.like(root.get(InvoiceEntity_.PRODUCT),  "%" + filter.getProduct() + "%"));
        }

        if (filter.getMinPrice() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(InvoiceEntity_.PRICE), filter.getMinPrice()));
        }

        if (filter.getMaxPrice() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(InvoiceEntity_.PRICE), filter.getMaxPrice()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

/**
 * Třída InvoiceSpecification je implementací rozhraní Specification pro entitu InvoiceEntity.
 * Slouží k vytvoření specifikace pro dotazy nad entitou InvoiceEntity na základě filtru.
 * Filtr obsahuje parametry pro filtrování faktur podle prodejce, kupujícího, produktu, minimální a maximální ceny.
 * Metoda toPredicate sestavuje dotaz na základě filtru a vytváří seznam podmínek pro dotazování.
 * Vytváří se spojení s entitami PersonEntity pro získání informací o prodejci a kupujícím.
 */
}




