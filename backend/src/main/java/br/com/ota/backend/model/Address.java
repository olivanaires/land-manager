package br.com.ota.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "addresses")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity {

    @NotBlank
    @Size(max = 50)
    private String street;

    @NotBlank
    @Size(max = 50)
    private String neighborhood;

    @Size(max = 50)
    private String complement;

    @NotNull
    private String number;

    @NotBlank
    @Size(max = 9)
    private String postcode;

    @ManyToOne
    private City city;

}
