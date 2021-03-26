package at.spengergasse.essity.domain;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity //tabdil beshe be Tabell :in dade ha ro beriz too databace tabdil be Tabel to databank
@Table(name = "carriers")

public class Spedition extends AbstractPersistable<Long> {
   // @Version
   // private Integer version;


    @NotNull
    @Size(min = 2, max = 75)
    @Column(name = " carrier_name ", nullable = false)
    private String name;

    @NotBlank
    @Embedded
    private Adresse adresse;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "phone_number", nullable = false)
    private String telefonNummer;
}
