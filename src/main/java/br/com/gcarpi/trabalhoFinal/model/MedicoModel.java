package br.com.gcarpi.trabalhoFinal.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@Table(name = "medicos")
@AllArgsConstructor
@NoArgsConstructor
public class MedicoModel extends RepresentationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    @ApiModelProperty(notes = "The ID")
    private long id;

    @Column(name = "name", nullable = false, length = 50)
    @Setter @Getter
    @ApiModelProperty(notes = "O nome completo do Medico")
    private String name;

    @Column(nullable = false, length = 1)
    @Getter @Setter
    @ApiModelProperty(notes = "Gender - just one character (M or F)")
    private String gender;

    //..relationship
    @ManyToOne
    @JoinColumn(name = "dep_id")
    @Setter @Getter
    private DepModel Dep;
}
