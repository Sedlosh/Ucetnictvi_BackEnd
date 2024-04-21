package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;
import org.springframework.http.ResponseEntity;
import java.util.List;


public interface InvoiceService {



    InvoiceDTO addInvoice (InvoiceDTO invoiceDTO);

    ResponseEntity<Void> removeInvoice(long id);

    List<InvoiceDTO> getAll(InvoiceFilter invoiceFilter);

    InvoiceDTO getInvoiceById(long id);

    InvoiceDTO editInvoice(Long invoiceId, InvoiceDTO invoiceDTO);

    InvoiceStatisticDTO getInvoiceStatistics();



}






