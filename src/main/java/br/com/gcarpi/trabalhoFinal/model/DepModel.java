package br.com.gcarpi.trabalhoFinal.model;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;


@Entity
@Table(name = "dep")
@AllArgsConstructor
@NoArgsConstructor
public class DepModel extends RepresentationModel {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Setter @Getter
    private long id;

    @Column(length = 50, nullable = false)
    @Setter @Getter
    private String name;

}
