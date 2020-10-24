package br.com.ota.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "states")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class State extends BaseEntity {

    private String acronym;

    private String name;

}
