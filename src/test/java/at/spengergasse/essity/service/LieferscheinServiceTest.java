package at.spengergasse.essity.service;

import at.spengergasse.essity.TestFixtures;
import at.spengergasse.essity.domain.Lieferfirma;
import at.spengergasse.essity.domain.Lieferschein;
import at.spengergasse.essity.domain.Spedition;
import at.spengergasse.essity.foundation.DateTimeFactory;
import at.spengergasse.essity.persistence.LieferfirmaRepository;
import at.spengergasse.essity.persistence.LieferscheinRepository;
import at.spengergasse.essity.persistence.SpeditionRepository;
import at.spengergasse.essity.service.exceptions.DataQualityException;
import at.spengergasse.essity.service.exceptions.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import java.util.spi.LocaleNameProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LieferscheinServiceTest {

    private LieferscheinService lieferscheinService;

    @Mock private LieferscheinRepository lieferscheinRepository;
    @Mock private LieferfirmaRepository lieferfirmaRepository;
    @Mock private SpeditionRepository speditionRepository;
    private DateTimeFactory dateTimeFactory;

    @BeforeEach
    void setup()
    {
        dateTimeFactory = mock(DateTimeFactory.class);
        assumeThat(lieferscheinRepository).isNotNull();
        assumeThat(lieferfirmaRepository).isNotNull();
        assumeThat(speditionRepository).isNotNull();
        assumeThat(dateTimeFactory).isNotNull();

        lieferscheinService = new LieferscheinService(lieferscheinRepository, lieferfirmaRepository, speditionRepository, dateTimeFactory);
    }

    @Test
    void ensureCreateLieferscheinForUnknownLieferfirmaIdThrowsProperDataQualityException()
    {
        String kennzeichen = "w-1234";
        Long erstwiegungsGewicht = 1234l;
        String bezeichnung = "Altpapier";
        Long lieferfirmaId = 1l;   //unknown
        Long speditionsId =  4711l;

        when(lieferfirmaRepository.findById(lieferfirmaId)).thenReturn(Optional.empty());

        var dqEx =assertThrows(DataQualityException.class,
                () -> lieferscheinService.createLieferschein(kennzeichen,
                        erstwiegungsGewicht, bezeichnung, lieferfirmaId, speditionsId));
        assertThat(dqEx).hasMessageContaining(Lieferfirma.class.getSimpleName())
                .hasMessageContaining(lieferfirmaId.toString());

        // verify interactions between collaborators
        verify(lieferfirmaRepository).findById(lieferfirmaId);
        verify(lieferfirmaRepository, times(1)).findById(lieferfirmaId);
        verifyNoInteractions(lieferfirmaRepository);
        verifyNoMoreInteractions(speditionRepository);
        verifyNoMoreInteractions(lieferscheinRepository);
        verifyNoMoreInteractions(dateTimeFactory);

    }



    @Test
    void ensureCreateLieferscheinForPersistenceProblemsThrowsServiceException()
    {//ensureCreateLieferscheinForPersistenceProblemsThrowsServiceException
        //given
        String kennzeichen = "w-1434";
        Long erstwiegungsGewicht = 1134l;
        String bezeichnung = "Altpapier";
        Long lieferfirmaId = 4l;   //unknown
        Long speditionsId =  471l;
        //train the Mock objects

        when(lieferfirmaRepository.findById(lieferfirmaId)).thenThrow(new PersistenceException("Database connection already closed"));

        //expect

        var serviceEx =assertThrows(ServiceException.class,
                                                () -> lieferscheinService.createLieferschein(kennzeichen,
                                                       erstwiegungsGewicht, bezeichnung, lieferfirmaId, speditionsId));

        assertThat(serviceEx).hasRootCauseExactlyInstanceOf(PersistenceException.class);

        // verify interactions between collaborators
        verify(lieferfirmaRepository).findById(lieferfirmaId);
        verify(lieferfirmaRepository, times(1)).findById(lieferfirmaId);
        verifyNoInteractions(lieferfirmaRepository);
        verifyNoMoreInteractions(speditionRepository);
        verifyNoMoreInteractions(lieferscheinRepository);
        verifyNoMoreInteractions(dateTimeFactory);
    }


    @Test
    void ensureCreateLieferscheinForValidValuesWorksProperly()
    {
        //given
        String kennzeichen = "w-4234";
        Long erstwiegungsGewicht = 1244l;
        String bezeichnung = "Altpapier";
        Long lieferfirmaId = 2l;
        Long speditionsId =  311l;

        Lieferfirma lieferfirma = TestFixtures.defaultLieferfirma();
        Spedition spedition = TestFixtures.defaultSpedition();

        //train the Mock objects
        when(lieferfirmaRepository.findById(lieferfirmaId)).thenReturn(Optional.of(lieferfirma));
        when(speditionRepository.findById(speditionsId)).thenReturn(Optional.of(spedition));
        when(lieferscheinRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
        when(dateTimeFactory.now()).thenReturn(LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59));

        //when

        var createdLieferschein = lieferscheinService.createLieferschein(kennzeichen,
                        erstwiegungsGewicht, bezeichnung, lieferfirmaId, speditionsId);

        //then
        assertThat(createdLieferschein).isNotNull();
        assertThat(createdLieferschein.getLieferfirma()).isEqualTo(lieferfirma);
        assertThat(createdLieferschein.getSpedition()).isEqualTo(spedition);

        // verify interactions between collaborators
        verify(lieferfirmaRepository).findById(lieferfirmaId);
        //verify(lieferfirmaRepository, times(1)).findById(lieferfirmaId);
        verifyNoMoreInteractions(lieferfirmaRepository);
        verify(speditionRepository).findById(speditionsId);
        verifyNoMoreInteractions(speditionRepository);
        verify(lieferscheinRepository).save(createdLieferschein);
        verifyNoMoreInteractions(lieferscheinRepository);
        verify(dateTimeFactory).now();
        verifyNoMoreInteractions(dateTimeFactory);
    }



}