package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import cz.itnetwork.entity.repository.specification.InvoiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonServiceImpl personServiceImpl;

    /*
    Vstupní data ve formátu DTO se převedou na entitu faktury.
    Metoda najde objekt buyera a sellera z databáze podle zadaného ID, ty pak  společně s daty z DTO uloží do entity faktury.
    Převede entitu na DTO.
     */
    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity entity = invoiceMapper.toEntity(invoiceDTO);
        PersonEntity buyer = personRepository.findById(invoiceDTO.getBuyer().getId()).orElseThrow();
        PersonEntity seller = personRepository.findById(invoiceDTO.getSeller().getId()).orElseThrow();
        entity.setBuyer(buyer);
        entity.setSeller(seller);
        entity = invoiceRepository.save(entity);

        return invoiceMapper.toDTO(entity);

    }
    /*
    Vstupní parametr určuje Id faktury, která má být odstraněna.
    Metoda volá metodu fetchInvoiceById, které získá entitu z databáze pomocí Id faktury.
    Nalezenou fakturu odstraní z databáze.
    Vytváří HTTP odpověď 204 (dle api dokumentace)
     */
    @Override
    public ResponseEntity<Void> removeInvoice(long invoiceId) {
            InvoiceEntity entity = fetchInvoiceById(invoiceId);
            invoiceRepository.delete(entity);
            return ResponseEntity.noContent().build();
    }

    /*
    Parametr určuje filtrovací podmínky zadané uživatelem.
    Vytváří specifikaci pomocí zadaných filtrů.
    Vyhledá všechny filtrované faktury pomocí metody findAll
    Vrací seznam faktur ve formátu DTO
     */
    @Override
    public List<InvoiceDTO> getAll(InvoiceFilter invoiceFilter) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);
        return invoiceRepository.findAll(invoiceSpecification, PageRequest.of(0, invoiceFilter.getLimit()))
                .stream()
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    /*
    Vstupní parametr určuje Id faktury, které má být zobrazena.
    Metoda volá metodu fetchInvoiceById, které získá entitu z databáze pomocí Id faktury.
    Nalezenou fakturu vrací ve formátu DTO.
     */
    @Override
    public InvoiceDTO getInvoiceById(long invoiceId) {
        InvoiceEntity invoiceEntity = fetchInvoiceById(invoiceId);
        return invoiceMapper.toDTO(invoiceEntity);
    }

    /*
    Vstupní parametr určuje id faktury, která má být nalezena.
    Vrací Fakturu s daným Id, když faktura neexistuje vrací výjimku s chybovou hláškou.
     */
    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }

    /*
    Vstupními parametry jsou Id faktury, která má být upravena a nová data pro úpravu ve formátu DTO.
    Metoda volá metodu fetchInvoiceById, které získá entitu z databáze pomocí Id faktury.
    Přepíše hodnotu Id faktury na Id, která byla zadána v DTO.
    Buyer a seller jsou nalezeni díky jejich id a aktualizování pomocí .setSeller / .setBuyer
    Nová data jsou uložena do entity faktury a převedena na DTO.
     */
    @Override
    public InvoiceDTO editInvoice(Long invoiceId, InvoiceDTO invoiceDTO) {
        InvoiceEntity invoiceEntity = fetchInvoiceById(invoiceId);
        invoiceDTO.setId(invoiceId);

        invoiceEntity.setSeller(personRepository.findById(invoiceDTO.getSeller().getId()).orElseThrow());
        invoiceEntity.setBuyer(personServiceImpl.fetchPersonById(invoiceDTO.getBuyer().getId()));

        invoiceMapper.updateInvoiceEntity(invoiceDTO, invoiceEntity);

        invoiceRepository.save(invoiceEntity);
        return invoiceMapper.toDTO(invoiceEntity);

    }
    @Override
    public InvoiceStatisticDTO getInvoiceStatistics() {
        return invoiceRepository.getInvoiceStatistics();
    }
}
