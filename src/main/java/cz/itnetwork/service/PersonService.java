package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticDTO;

import java.util.List;

public interface PersonService {

    PersonDTO addPerson(PersonDTO personDTO); /* Přidá novou osobu */
    void removePerson(long id); /* Odstraní existující osobu podle zadaného id */
    List<PersonDTO> getAll(); /* Vrací seznam všech osob ve formátu DTO */
    PersonDTO getPersonById(long id); /* Vrací osobu podle zadaného id */
    PersonDTO editPerson(long id, PersonDTO personDTO); /* Upravuje existující osobu podle zadaného id novými daty, které přijdou jako DTO */
    List<InvoiceDTO> getSalesByIdentificationNumber(String identificationNumber); /* Vrací seznam všech vystavných faktur podle id osoby ve formátu DTO */
    List<InvoiceDTO> getPurchasesByIdentificationNumber(String identificationNumber); /* Vrací seznam všech přijatých faktur podle id osoby ve formátu DTO */
    List<PersonStatisticDTO> getPersonStatistics(); /* Vrací seznam statistik k jedntlivým osobám ve formátu DTO */








}
