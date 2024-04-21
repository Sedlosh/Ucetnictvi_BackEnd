package cz.itnetwork.entity.filter;

import lombok.Data;

@Data
public class InvoiceFilter {
    private Long sellerID;
    private Long buyerID;
    private String product;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer limit = 10;
}