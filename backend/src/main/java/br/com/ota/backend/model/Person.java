package br.com.ota.backend.model;

import br.com.ota.backend.model.enums.MaritalstatusEnum;
import br.com.ota.backend.model.enums.PersonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "people")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Person extends BaseEntity {

    private LocalDate birthday;

    private String cic;

    private String cpf;

    private String identity;

    @Enumerated(EnumType.STRING)
    private MaritalstatusEnum maritalstatus;

    private String name;

    private String profession;

    @Enumerated(EnumType.STRING)
    private PersonType type;

    @OneToOne
    private Address address;

}
