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
@Table(name = "payments")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Payment extends BaseEntity {

    private String boletocloudtoken;

    private String description;

    private BigDecimal discount;

    private String document;

    private Boolean executed;

    private LocalDate expirationdate;

    private String instruction;

    private BigDecimal interest;

    private Integer latedays;

    private BigDecimal latepenalty;

    private Integer number;

    private BigDecimal paidvalue;

    private LocalDate paydate;

    private String title;

    private BigDecimal value;

    private String observation;

    private BigDecimal valuebeforecurrentigpm;

    @ManyToOne
    private Contract contract;

    @ManyToOne
    private Igpm igpm;

}
