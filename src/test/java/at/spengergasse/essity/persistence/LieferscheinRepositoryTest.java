package at.spengergasse.essity.persistence;

import at.spengergasse.essity.TestFixtures;
import at.spengergasse.essity.domain.Lieferschein;
import at.spengergasse.essity.persistence.Projections.KennzeichenStatistik;
import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.defaultanswers.ReturnsSmartNulls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static at.spengergasse.essity.TestFixtures.defaultLieferschein;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LieferscheinRepositoryTest {

    @Autowired
    private LieferscheinRepository lieferscheinRepository;

    @Test
    void ensureSavingWorksProperly() {
        //given/arrange
        Lieferschein ls = defaultLieferschein();

        //wenn / act

        var saved = lieferscheinRepository.saveAndFlush(ls);

        // then / assert
        assertThat(saved).isNotNull().isSameAs(ls);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getSpedition()).isNotNull();
        assertThat(saved.getSpedition().getId()).isNotNull();
        assertThat(saved.getLieferfirma()).isNotNull();
        assertThat(saved.getLieferfirma().getId()).isNotNull();
    }



    @Test
    void ensureReadKennzeichenStatistikWorksProperly() {
        //given
        LocalDateTime erstwiegeTS = LocalDateTime.now();
        Lieferschein ls = defaultLieferschein();
        ls.setErstwiegungTS(erstwiegeTS);
        lieferscheinRepository.save(ls);

        // wenn

        List<KennzeichenStatistik> statik = lieferscheinRepository.readKennzeichenStatik(erstwiegeTS.minusDays(1), erstwiegeTS.plusDays(1));

        // then
        assertThat(statik).isNotNull();
        assertThat(statik).hasSize(1);

    }




/*
    @Autowired
    private LieferscheinRepository lieferscheinRepository;

    @Test
    void ensureSavingWorksProperly() {
        //given / arrange
        Lieferschein ls = TestFixtures.defaultLieferschein();

        //when / act
        var saved = lieferscheinRepository.saveAndFlush(ls);

        //then/ assert

        assertThat(saved).isNotNull().isSameAs(ls);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getSpedition()).isNotNull();
        assertThat(saved.getSpedition().getId()).isNotNull();
        assertThat(saved.getLieferfirma()).isNotNull();
        assertThat(saved.getLieferfirma().getId()).isNotNull();
    }


    @Test
    void ensureReadKennzeichenStatistikWorksProperly() {
        //given
        LocalDateTime erstwiegeTS = LocalDateTime.now();
        Lieferschein ls = TestFixtures.defaultLieferschein();
        ls.setErstwiegungTS(erstwiegeTS);
        lieferscheinRepository.save(ls);

        //when / act
        List<KennzeichenStatistik>statistik = lieferscheinRepository.readKennzeichenStatik(erstwiegeTS.minusDays(1), erstwiegeTS.plusDays(1));
        //then
        assertThat(statistik).isNotNull();
        assertThat(statistik).hasSize(1);
    }


 */

}

