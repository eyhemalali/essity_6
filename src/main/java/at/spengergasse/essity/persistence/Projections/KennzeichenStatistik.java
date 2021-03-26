package at.spengergasse.essity.persistence.Projections;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public class KennzeichenStatistik  {
    private final String kennzeichen;
    private final Long anzahl;

}