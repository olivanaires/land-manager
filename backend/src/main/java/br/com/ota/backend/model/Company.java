package br.com.ota.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "companies")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Company extends BaseEntity {

    private String cnpj;

    private String name;

    @OneToOne
    private Address address;

    @OneToOne
    private BankingAccount bankingAccount;

    @OneToOne
    private Person person;
}
