/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */
package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceMapper invoiceMapper;


    /*
   Vstupní data ve formátu DTO se převedou na entitu osoby.
   Data uloží do entity.
   Převede entitu na DTO.
    */
    public PersonDTO addPerson(PersonDTO personDTO) {
        PersonEntity entity = personMapper.toEntity(personDTO);
        entity = personRepository.save(entity);

        return personMapper.toDTO(entity);
    }

    /*
    Vstupní parametr určuje Id osoby, která má být odstraněna.
    Metoda volá metodu fetchPersonById, které získá entitu z databáze pomocí Id osoby.
    Nalezenou osobu uloží a změní jí aktivní stav v databázi.
    */
    @Override
    public void removePerson(long personId) {
        try {
            PersonEntity person = fetchPersonById(personId);
            person.setHidden(true);

            personRepository.save(person);
        } catch (NotFoundException ignored) {

        }
    }
    /*
    Vrací seznam všech osob, které mají aktivní status.
    Vrací se ve formátu DTO.
    */
    @Override
    public List<PersonDTO> getAll() {
        return personRepository.findByHidden(false)
                .stream()
                .map(i -> personMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    /*
    Vstupní parametr určuje Id osoby, které má být zobrazena.
    Metoda volá metodu fetchPersonById, které získá entitu z databáze pomocí Id osoby.
    Nalezenou osobu vrací ve formátu DTO.
     */
    @Override
    public PersonDTO getPersonById(long personId) {
       PersonEntity personEntity = fetchPersonById(personId);

        return personMapper.toDTO(personEntity);
    }

    /*
    Vstupní parametr určuje id osoby, která má být nalezena.
    Vrací osobu s daným id, když osoba neexistuje vrací výjimku s chybovou hláškou.
    */
    public PersonEntity fetchPersonById(long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id " + id + " wasn't found in the database."));
    }

     /*
     Vstupními parametry jsou Id osoby, která má být upravena a nová data pro úpravu ve formátu DTO.
     Metoda volá metodu fetchPersonById, které získá entitu z databáze pomocí Id osoby.
     Nastaví původní osobě neaktivní status a vytvoří nový objekt osoby za pomoci addPerson.
     */
    @Override
     public PersonDTO editPerson(long personId, PersonDTO personDTO) {
        PersonEntity person = fetchPersonById(personId);
        person.setHidden(true);
        personRepository.save(person);
        return addPerson(personDTO);

    }

    /*
    Vstupní parametr určuje identifikační číslo osoby, jejíž entitu hledáme.
    Najde entitu s odpovídajícím id a z té potom vytvoří seznam všech faktur, kde vystupuje jako Seller.
    Výstupem je formát DTO.
    */
    public List<InvoiceDTO> getSalesByIdentificationNumber(String identificationNumber) {
        PersonEntity personEntity = personRepository.findByIdentificationNumber(identificationNumber).orElseThrow();
        List<InvoiceEntity> sales = invoiceRepository.findBySeller(personEntity);
        return sales.stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());

    }
    /*
    Vstupní parametr určuje identifikační číslo osoby, jejíž entitu hledáme.
    Najde entitu s odpovídajícím id a z té potom vytvoří seznam všech faktur, kde vystupuje jako Buyer.
    Výstupem je formát DTO.
    */
    public List<InvoiceDTO> getPurchasesByIdentificationNumber(String identificationNumber){
        PersonEntity personEntity = personRepository.findByIdentificationNumber(identificationNumber).orElseThrow();
        List<InvoiceEntity> purchases = invoiceRepository.findByBuyer(personEntity);
        return purchases.stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonStatisticDTO> getPersonStatistics() {
        return personRepository.getPersonStatistics();
    }
    }

