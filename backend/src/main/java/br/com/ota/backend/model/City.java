package br.com.ota.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "cities")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class City extends BaseEntity {

    private String name;

    @ManyToOne
    private State state;
}
