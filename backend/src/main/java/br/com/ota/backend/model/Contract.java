package br.com.ota.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "contracts")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Contract extends BaseEntity {

    private BigDecimal breakage;

    private LocalDate contractdate;

    private LocalDate entrancepaymentdate;

    private Integer expirebillsday;

    private LocalDate firstpaymentdate;

    private Integer intercalatedqtd;

    private BigDecimal intercalatedvalue;

    private BigDecimal latepenalty;

    private BigDecimal monetarycorrection;

    private BigDecimal mora;

    private String number;

    private Integer parcelqtd;

    private BigDecimal parcelvalue;

    private Integer qtddaysforbreakage;

    private BigDecimal totalvalue;

    @ManyToOne
    private Company company;

    @OneToOne
    private Land land;

    private Integer entranceparcelqtd;

    private BigDecimal entrancepaymentvalue;

    private Integer intercalatedparcelqtd;

    private Integer monthsqtdbetweeninter;

    private String observation;

    private BigDecimal firstentrancepaymentvalue;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "people_contracts",
            joinColumns = @JoinColumn(name = "contract_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private List<Person> people;

    @OneToMany(mappedBy = "contract", fetch = FetchType.EAGER)
    private Set<Payment> payments;

}
