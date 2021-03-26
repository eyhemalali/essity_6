package at.spengergasse.essity.domain;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
@Table(name = "delivery_notes")
public class Lieferschein extends AbstractPersistable<Long> {
   // @Version
   // private Integer version;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "license_plate", nullable = false)
    private String kennzeichen;

    @Column(name = "start_Ts")
    private LocalDateTime erstwiegungTS;

    @Min(0)
    @Max(38000)
    @Column(name = "start_weight")
    private Long erstwiegungGewicht;
    /*
    Gewicht in Kg
     */
    @Column(name = "end_ts")
    private LocalDateTime zweitwiegungTS;

    @Min(0)
    @Max(38000)
    @Column(name = "end_weigt")
    private Long zweitwiegungGewicht;

    @Min(0)
    @Max(38000)
    @Column(name = "tara_waight")
    private Long taraGewicht;

    @Enumerated(EnumType.STRING)
    private Qualitaet qualitaet;

    @NotBlank
    @Size(max = 12)
    @Column(name = "description")
    private String bezeichnung;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id", foreignKey = @ForeignKey(name = "Fk_delivery_notes_2_suppliers"))
    private Lieferfirma lieferfirma;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carrier_id", foreignKey = @ForeignKey(name = "Fk_delivery_notes_2_carriers"))
    private Spedition spedition;

    public Long getDeliveryWeight()
    {
       return zweitwiegungGewicht -erstwiegungGewicht;

    }






/*
     @NotBlank
     @Size(min=2, max=50)
     private String speditionEingegeben;

    @NotBlank
    @Size(min=2, max=50)
     private String lieferfirmaEingegeben;

    public void setLieferfirma(Lieferfirma lieferfirma) {
    }

    public void add(Lieferschein lieferschein) {
    }

    @Transient
    private boolean finished;
 */


}
