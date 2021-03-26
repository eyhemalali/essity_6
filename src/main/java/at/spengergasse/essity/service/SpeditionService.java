package at.spengergasse.essity.service;

import at.spengergasse.essity.domain.Spedition;
import at.spengergasse.essity.persistence.SpeditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
//@Transactional
public class SpeditionService {
    private final SpeditionRepository speditionRepository;

    public List<Spedition> findAll() {
        return speditionRepository.findAll();
    }


    public void save(Spedition spedition) {
        speditionRepository.save(spedition);
    }

    public List<Spedition> findByName(String name) {
        return speditionRepository.findByName(name);
    }

  public Spedition findById(Long id)
    {
        //return speditionRepository.findById(id);
        return null;
    }

}