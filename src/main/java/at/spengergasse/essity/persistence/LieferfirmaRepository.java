package at.spengergasse.essity.persistence;

import at.spengergasse.essity.domain.Lieferfirma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LieferfirmaRepository extends JpaRepository<Lieferfirma, Long> {

    List<Lieferfirma> findByName(String name);

    List<Lieferfirma> findByAdresse(String adresse);

   Optional<Lieferfirma> findByTelefonNummer(String  telefonNummer);


    // List<Lieferfirma> findByAdressePlz(String adressePlz);
    // List<Lieferfirma> findByAdresseNr(String adresseNr);
    // List<Lieferfirma> findByAdresseOrt(String adresseOrt);
}
//name  adresse  telefonNummer