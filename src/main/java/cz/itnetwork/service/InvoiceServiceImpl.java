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

    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity entity = invoiceMapper.toEntity(invoiceDTO);
        PersonEntity buyer = personRepository.findById(invoiceDTO.getBuyer().getId()).orElseThrow();
        PersonEntity seller = personRepository.findById(invoiceDTO.getSeller().getId()).orElseThrow();
        entity.setBuyer(buyer);
        entity.setSeller(seller);
        entity = invoiceRepository.save(entity);

        return invoiceMapper.toDTO(entity);

        /**
         * Převod na entitu: Nejprve se převede vstupní objekt InvoiceDTO na entitu InvoiceEntity pomocí mapperu invoiceMapper.toEntity(invoiceDTO). Tím se vytvoří nová instance InvoiceEntity s daty z InvoiceDTO.
         *
         * Hledání kupce: Poté se získá identifikátor kupce (buyerId) z invoiceDTO.getBuyer().getId(). Pomocí tohoto identifikátoru se provede dotaz do databáze pomocí personRepository.findById(buyerId), aby se získala instance PersonEntity kupce. Pokud kupce není nalezen, metoda orElseThrow() vyvolá výjimku.
         *
         * Nastavení kupce v objektu faktury: Nyní, když je kupce nalezen, je tento objekt PersonEntity nastaven jako kupce v entitě InvoiceEntity pomocí entity.setBuyer(buyer).
         *
         * Uložení faktury do databáze: Entita InvoiceEntity, která nyní obsahuje správného kupce, je uložena do databáze pomocí invoiceRepository.save(entity). Tím se provede vytvoření nové faktury v databázi nebo aktualizace stávající faktury, pokud už existuje.
         *
         * Převod na DTO: Nakonec je uložená faktura znovu převedena zpět na InvoiceDTO pomocí mapperu invoiceMapper.toDTO(entity). Tím se vytvoří instance InvoiceDTO, která obsahuje aktualizované nebo nově vytvořené údaje faktury.
         */
    }
    @Override
    public ResponseEntity<Void> removeInvoice(long invoiceId) {
            InvoiceEntity entity = fetchInvoiceById(invoiceId);
            invoiceRepository.delete(entity);
            return ResponseEntity.noContent().build();
        /**
         * Metoda slouží k odstranění faktury podle zadaného identifikátoru.
         *
         * @param invoiceId Identifikátor faktury, která má být odstraněna
         * @return ResponseEntity s odpovídajícím stavovým kódem a tělem odpovědi
         */
    }

    @Override
    public List<InvoiceDTO> getAll(InvoiceFilter invoiceFilter) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);
        return invoiceRepository.findAll(invoiceSpecification, PageRequest.of(0, invoiceFilter.getLimit()))
                .stream()
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
        /**
         * Metoda slouží k získání seznamu faktur podle zadaných filtračních kritérií.
         *
         * @param invoiceFilter Filtrační kritéria pro výběr faktur
         * @return Seznam faktur odpovídající zadaným kritériím, převedený na DTO objekty
         */
    }

    @Override
    public InvoiceDTO getInvoiceById(long invoiceId) {
        InvoiceEntity invoiceEntity = fetchInvoiceById(invoiceId);
        return invoiceMapper.toDTO(invoiceEntity);
    }

    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }

    @Override
    public InvoiceDTO editInvoice(Long invoiceId, InvoiceDTO invoiceDTO) {
        InvoiceEntity invoiceEntity = fetchInvoiceById(invoiceId);
        invoiceDTO.setId(invoiceId);

        invoiceEntity.setSeller(personRepository.findById(invoiceDTO.getSeller().getId()).orElseThrow());
        invoiceEntity.setBuyer(personServiceImpl.fetchPersonById(invoiceDTO.getBuyer().getId()));


        invoiceMapper.updateInvoiceEntity(invoiceDTO, invoiceEntity);

        invoiceRepository.save(invoiceEntity);
        return invoiceMapper.toDTO(invoiceEntity);

        /**
         * Metoda slouží k úpravě existující faktury na základě zadaného ID faktury a nových údajů o faktuře.
         *
         * @param invoiceId    ID faktury, která má být upravena
         * @param invoiceDTO   Nové údaje o faktuře ve formátu DTO (Data Transfer Object)
         * @return             DTO reprezentace upravené faktury
         * @throws NotFoundException Pokud nebyla nalezena faktura s daným ID
         */
    }

    @Override
    public InvoiceStatisticDTO getInvoiceStatistics() {
        return invoiceRepository.getInvoiceStatistics();
    }
}
