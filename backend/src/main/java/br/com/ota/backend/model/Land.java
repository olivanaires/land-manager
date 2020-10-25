package br.com.ota.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "lands")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Land extends BaseEntity {

    private float area;

    private float backdimension;

    private String backintersection;

    private String block;

    private float frontdimension;

    private String frontintersection;

    private float lefdimension;

    private String leftintersection;

    private String number;

    private float rightdimension;

    private String rightintersection;

    private String situation;

    @ManyToOne
    @JoinColumn(name = "landlocation_id", referencedColumnName = "id")
    private LandLocation landLocation;

}
