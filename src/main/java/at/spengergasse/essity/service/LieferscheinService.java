package at.spengergasse.essity.service;

import at.spengergasse.essity.domain.Lieferfirma;
import at.spengergasse.essity.domain.Lieferschein;
import at.spengergasse.essity.domain.Qualitaet;
import at.spengergasse.essity.domain.Spedition;
import at.spengergasse.essity.foundation.DateTimeFactory;
import at.spengergasse.essity.persistence.LieferfirmaRepository;
import at.spengergasse.essity.persistence.LieferscheinRepository;
import at.spengergasse.essity.persistence.SpeditionRepository;
import at.spengergasse.essity.service.exceptions.DataQualityException;
import at.spengergasse.essity.service.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.tools.Trace;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor

@Service
@Transactional(readOnly = true)
public class LieferscheinService {

    private final LieferscheinRepository lieferscheinRepository;
    private final LieferfirmaRepository lieferfirmaRepository;
    private final SpeditionRepository speditionRepository;
    private final DateTimeFactory dateTimeFactory;


    @Transactional(readOnly = false)
    public Lieferschein createLieferschein(@NotBlank @NotNull String kennzeichen,
                                           @NotNull Long erstwiegungGewicht,
                                           @NotBlank String bezeichnung,
                                           @NotNull Long lieferfirmaId,
                                           @NotNull Long speditionId) {
        try {

            //precondition

            //Objects.requireNonNull(kennzeichen);

            //lies Lieferfirma
            //lies spedition
            //erzeuge eine erste version des Lieferscheins
            //speichers
     //       var LSchein = lieferfirmaRepository.findById(lieferfirmaId)
            return lieferfirmaRepository.findById(lieferfirmaId)
                    .map(lieferfirma -> Lieferschein.builder()
                            .kennzeichen(kennzeichen)
                            .erstwiegungGewicht(erstwiegungGewicht)
                            //.erstwiegungTS(LocalDateTime.now()) funktioniert aber Problematich
                            .erstwiegungTS(dateTimeFactory.now())
                            .bezeichnung(bezeichnung)
                            .lieferfirma(lieferfirma)
                            .spedition(speditionRepository.findById(speditionId)
                                    .orElseThrow(() -> DataQualityException.forMissingEntityForId(speditionId, Spedition.class)))
                            .build())
                    .map(lieferscheinRepository::save)
                    .orElseThrow(() -> DataQualityException.forMissingEntityForId(lieferfirmaId, Lieferfirma.class));
    //        return LSchein;
            //add variable return
        }
        catch (PersistenceException pEx)
        {
            throw ServiceException.wrap("Problems while creating Lieferschein", pEx);
        }

    }

    @Transactional(readOnly = false)
    public Lieferschein handleZweitwiegung(@NotNull Long lieferscheinId,
                                           @NotNull Long zweitwiegungsGewicht,
                                           @NotNull Qualitaet qualitaet)
    {
        return lieferscheinRepository.findById(lieferscheinId)
                                     .map(lieferschein -> {
                                         lieferschein.setZweitwiegungGewicht(zweitwiegungsGewicht);
                                         lieferschein.setZweitwiegungTS(dateTimeFactory.now());
                                         lieferschein.setQualitaet(qualitaet);
                                         return lieferschein;
                                     })
                .map(lieferscheinRepository::save)
                .orElseThrow(() -> DataQualityException.forMissingEntityForId(lieferscheinId, Lieferschein.class));

    }





}











//
//        Trace log = null;
//        log.info("creating lieferschein");
//        Lieferschein lieferschein = Lieferschein.builder()
//                .kennzeichen(kennzeichen)
//                .erstwiegungGewicht(erstwiegungGewicht)
//                .bezeichnung(bezeichnung)
//                .build();
//
//        try
//        {
//            lieferscheinRepository.save(lieferschein);
//        } catch (PersistenceException pEx)
//        {
//            throw new ServiceException("Problems while saving", pEx);
//        }
//        log.info("created lieferschein with kenzeichen{}");
//        return lieferschein;
//        }


    //
//
//    @Transactional(readOnly = false)
//    public List<Lieferschein> findAll() {
//        return lieferscheinRepository.findAll();
//    }
//
//    public List<Lieferschein> findByKennzeichen(String kennzeichen) {
//        return lieferscheinRepository.findByKennzeichenLike(kennzeichen);
//    }
//
//    public void save(Lieferschein lieferschein) {
//        lieferscheinRepository.save(lieferschein);
//
//    }


//    public Lieferschein createLieferschein(Lieferschein lieferschein) {
//        if (lieferschein.getKennzeichen() == null) {
//            lieferschein.setKennzeichen("defaultKennzeichen");//???????????????
//        }
//
//        return lieferscheinRepository.save(lieferschein);
//    }


//    public Optional<Lieferschein> findById(Long id)
//    {
//        return lieferscheinRepository.findById(id);
//
//    }



