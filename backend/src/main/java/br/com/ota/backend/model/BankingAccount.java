package br.com.ota.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "bankingaccounts")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BankingAccount extends BaseEntity {

    private int accountdigit;

    private long accountnumber;

    private int agencydigit;

    private long agency;

    private String bankcode;

    private int wallet;
}
