package at.spengergasse.essity.persistence;

import at.spengergasse.essity.domain.Spedition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeditionRepository extends JpaRepository<Spedition, Long> {


    List<Spedition> findByName(String name);

    List<Spedition> findByAdresse(String adresse);

    //Spedition findByTelefonNummer(String telefonNummer);

}
