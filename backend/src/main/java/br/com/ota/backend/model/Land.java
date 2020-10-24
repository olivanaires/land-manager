package br.com.ota.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "lands")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Land extends BaseEntity {

    private byte area;

    private byte backdimension;

    private String backintersection;

    private String block;

    private byte frontdimension;

    private String frontintersection;

    private byte lefdimension;

    private String leftintersection;

    private String number;

    private byte rightdimension;

    private String rightintersection;

    private String situation;

    @ManyToOne
    private LandLocation landLocation;

}
