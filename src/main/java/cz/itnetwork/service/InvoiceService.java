package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;
import org.springframework.http.ResponseEntity;
import java.util.List;


public interface InvoiceService {

    InvoiceDTO addInvoice (InvoiceDTO invoiceDTO); /* Přidá novou fakturu */
    ResponseEntity<Void> removeInvoice(long id); /* Odstraní existující fakturu podle zadaného id */
    List<InvoiceDTO> getAll(InvoiceFilter invoiceFilter); /* Vrátí seznam všech faktur podle zadaného filtru */
    InvoiceDTO getInvoiceById(long id); /* Vrátí fakturu podle zadaného id */
    InvoiceDTO editInvoice(Long invoiceId, InvoiceDTO invoiceDTO); /*Upraví existující fakturu podle zadaného id novými daty, které přijdou jako DTO*/
    InvoiceStatisticDTO getInvoiceStatistics(); /*Vrátí statistiku faktur */



}






