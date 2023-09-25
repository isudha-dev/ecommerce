package dev.sudha.productcatalog.repositories;

public interface CustomQueries {
    String FIND_ALL_BY_PRICE_CURRENCY = "select * from Product where currency = :currency";
}
