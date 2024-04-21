package cz.itnetwork.entity.repository;


import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {
    List<InvoiceEntity> findBySeller(PersonEntity seller); /* Metoda k nalezení seznamu faktur podle prodejce. */
    List<InvoiceEntity> findByBuyer(PersonEntity buyer); /* Metoda k nalezení seznamu faktur podle kupce. */

    @Query(value = """
            SELECT new cz.itnetwork.dto.InvoiceStatisticDTO(
            SUM(actual.price),
            SUM(everything.price),
            COUNT(*))
            FROM invoice everything
            LEFT JOIN invoice actual
            ON everything.id = actual.id
            AND YEAR(actual.issued) = YEAR(CURRENT_DATE)""")
    InvoiceStatisticDTO getInvoiceStatistics();
}
