package at.spengergasse.essity.persistence;

import at.spengergasse.essity.domain.Lieferfirma;
import at.spengergasse.essity.domain.Lieferschein;
import at.spengergasse.essity.persistence.Projections.KennzeichenStatistik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LieferscheinRepository extends JpaRepository<Lieferschein, Long> {
        // strg+shift+T bara kelase test

    List<Lieferschein> findByKennzeichenLike(String kennzeichen);
    List<Lieferschein> findByZweitwiegungGewichtIsNull();
    Optional<Lieferfirma>  findByTelefonNummer(String telefonNummer);


      @Query("""
        select new at.spengergasse.essity.persistence.Projections.KennzeichenStatistik(ls.kennzeichen, count(*))
        from  Lieferschein As ls
        where ls.erstwiegungTS between :startTS And :endTS
        group by ls.kennzeichen
""")
    List<KennzeichenStatistik> readKennzeichenStatik(LocalDateTime startTs, LocalDateTime endTs);















//    List<Lieferschein> findByErstwiegungTSGreaterThan(LocalDate startErstwiegungTS, LocalDate endErstwiegungTS);
//    List<Lieferschein> findByErstwiegungGewichtGreaterThanEqual(int erstwiegungGewicht, int zweitGewicht);
//    List<Lieferschein> findByZweitwiegungTSGreaterThan(LocalDate startZweitwiegungTS ,LocalDate endZweitwiegungTS );
//    List<Lieferschein> findAllByOrderByErstwiegungTSDesc();
    //Lieferschein findById(int lieferscheinId);
    //List<Lieferschein> findByZweitwiegungGewichtGreaterThanEqual(int zweitwiegungGewicht);
    //List<Lieferschein> findByErstwiegungTSZeitGreatThan(LocalDate erstwiegungTS);


}
