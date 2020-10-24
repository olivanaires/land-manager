package br.com.ota.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "landlocations")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LandLocation extends BaseEntity {

    private String name;

    @OneToOne
    private Address address;

}
