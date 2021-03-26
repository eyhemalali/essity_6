package at.spengergasse.essity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Adresse {
    @NotBlank
    @Size(min = 2, max = 75)
    @Column(name = "street_number", nullable = false)
    private String strasseNummer;



    @NotBlank
    @Size(min = 2, max = 10)
    @Column(name = "zip_code", nullable = false)
    private String plz;

    @NotBlank
    @Size(min = 2, max = 10)
    @Column(name = "city", nullable = false)
    private String ort;

    @Column(name = "country", nullable = false)
    private String land;


}
