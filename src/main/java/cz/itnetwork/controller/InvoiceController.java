package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.service.InvoiceService;
import cz.itnetwork.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private PersonService personService;

    @Autowired InvoiceStatisticDTO invoiceStatisticDTO;

    @PostMapping("/invoices")
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {return invoiceService.addInvoice(invoiceDTO); }

    @GetMapping("/invoices")
    public List<InvoiceDTO> getInvoices(InvoiceFilter invoiceFilter) {return invoiceService.getAll(invoiceFilter); }

    @DeleteMapping("/invoices/{invoiceId}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long invoiceId) {invoiceService.removeInvoice(invoiceId);
        return ResponseEntity.noContent().build();}

    @GetMapping("/invoices/{invoiceId}")
    public InvoiceDTO getInvoice(@PathVariable Long invoiceId){
        return invoiceService.getInvoiceById(invoiceId);
    }

    @GetMapping("/identification/{identificationNumber}/sales")
    public List<InvoiceDTO> getSalesByIdentificationNumber(@PathVariable String identificationNumber) {
        return personService.getSalesByIdentificationNumber(identificationNumber);
    }

    @GetMapping("/identification/{identificationNumber}/purchases")
    public List<InvoiceDTO> getPurchasesByIdentificationNumber(@PathVariable String identificationNumber) {
        return personService.getPurchasesByIdentificationNumber(identificationNumber);
    }

    @PutMapping("/invoices/{invoiceId}")
    public InvoiceDTO editInvoice(@PathVariable long invoiceId, @RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.editInvoice(invoiceId, invoiceDTO);
    }
    @GetMapping("/invoices/statistics")
    public InvoiceStatisticDTO getInvoiceStatistics() {
        return invoiceService.getInvoiceStatistics();
    }

}
