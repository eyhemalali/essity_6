package at.spengergasse.essity.domain;


import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
//unbenen
@Table(name = "suppliers")

public class Lieferfirma extends AbstractPersistable<Long> {



    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "supplier_name", nullable = false)
    private String name;

    @NonNull
    @Embedded
    private Adresse adresse;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "phone_number", nullable = false)
    private String telefonNummer;


    /*

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lieferfirma")
    private List<Lieferschein> lieferscheins = new ArrayList<>();

    public Lieferfirma addLieferschein(Lieferschein... lieferschein)
    {
        Arrays.stream(lieferschein).filter(l -> l!= null).forEach(this::addLieferschein);
        return this;

    }
    public Lieferfirma addLieferschein(Lieferschein lieferschein)
    {
        Objects.requireNonNull(lieferschein, "liferschein must be provided");
        lieferschein.setLieferfirma(this);
        lieferschein.add(lieferschein);
        return this;

    }

     */

}
