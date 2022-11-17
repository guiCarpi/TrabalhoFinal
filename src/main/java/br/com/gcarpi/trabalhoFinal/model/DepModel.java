package br.com.gcarpi.trabalhoFinal.model;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "departamento")
@AllArgsConstructor
@NoArgsConstructor
public class DepModel  {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Setter @Getter
    private long id;

    @Column(length = 50, nullable = false)
    @Setter @Getter
    private String name;

}
