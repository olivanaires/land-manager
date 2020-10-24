package br.com.ota.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "phones")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Phone extends BaseEntity {

    private String number;

    @OneToOne
    private Person person;
}
