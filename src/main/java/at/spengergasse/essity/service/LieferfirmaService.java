package at.spengergasse.essity.service;


import at.spengergasse.essity.domain.Lieferfirma;
import at.spengergasse.essity.persistence.LieferfirmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LieferfirmaService {

    private final LieferfirmaRepository lieferfirmaRepository;

    public List<Lieferfirma> findAll() {
        return lieferfirmaRepository.findAll();
    }

    public void save(Lieferfirma lieferfirma) {
        lieferfirmaRepository.save(lieferfirma);
    }

    //??public Lieferfirma findById(Long id){ return lieferfirmaRepository.findById(id); }

}
