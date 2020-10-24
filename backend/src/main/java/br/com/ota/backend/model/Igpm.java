package br.com.ota.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "igpms")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Igpm extends BaseEntity {

    private LocalDate date;

    private BigDecimal latepenalty;

    private int year;

    @ManyToOne
    private Contract contract;

}
