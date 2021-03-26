package at.spengergasse.essity.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


import static org.assertj.core.api.Assertions.assertThat;


//@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Sql({"/data.sql"})
class LieferscheinServiceIntTest {
    @Autowired
    private LieferscheinService lieferscheinService;





    @Test
    public void ensureCreateLieferscheinForValidValuesWorksProperly()
    {
        //given
        String kennzeichen = "w-4234";
        Long erstwiegungsGewicht = 1244L;
        String bezeichnung = "Altpapier";
        Long lieferfirmaId = 100L;
        Long speditionsId = 200L;


        //when

        var createdLieferschein = lieferscheinService.createLieferschein(kennzeichen,
                        erstwiegungsGewicht, bezeichnung, lieferfirmaId, speditionsId);

        //then
        assertThat(createdLieferschein).isNotNull();
        assertThat(createdLieferschein.getId()).isNotNull();
        assertThat(createdLieferschein.getLieferfirma()).isNotNull();
        assertThat(createdLieferschein.getSpedition()).isNotNull();


    }



}