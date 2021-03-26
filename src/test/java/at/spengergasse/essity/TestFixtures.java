package at.spengergasse.essity;


import at.spengergasse.essity.domain.Adresse;
import at.spengergasse.essity.domain.Lieferfirma;
import at.spengergasse.essity.domain.Lieferschein;
import at.spengergasse.essity.domain.Spedition;

public class TestFixtures {

    public static final String defaultKennzeichen = "w-1234LkW";

    public static final String austria = "Östreisch";
    

    public static Lieferschein defaultLieferschein() {
        return Lieferschein.builder()
                            .kennzeichen(defaultKennzeichen)
                            .bezeichnung("Altpapier")
                            .lieferfirma(defaultLieferfirma())
                            .spedition(defaultSpedition())
          //                  .erstwiegungGewicht(1224L)
                            .build();
    }

    public static Spedition defaultSpedition() {
        return Spedition.builder()
                        .name("Default Spedition")
                        .telefonNummer("+1800callcarrier")
                        .adresse(defaultAdresse())
                        .build();
    }

    public static Lieferfirma defaultLieferfirma() {
        Adresse adresse = defaultAdresse();
        adresse.setLand(austria);
        return Lieferfirma.builder()
                          .name("Default Liferfirma")
                          .telefonNummer("+1800callsupplier")
                          .adresse(adresse)
                          .build();
    }

    public static Adresse defaultAdresse() {
        return Adresse.builder()
                      .strasseNummer("Default Straße 1")
                      .plz("1050")
                      .ort("Wien")
                      .build();
    }
}
