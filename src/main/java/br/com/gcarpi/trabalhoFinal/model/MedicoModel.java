package br.com.gcarpi.trabalhoFinal.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@Table(name = "medico")
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
    @ApiModelProperty(notes = "The client's  full name")
    private String name;

    @Column(nullable = false, length = 1)
    @Getter @Setter
    @ApiModelProperty(notes = "Gender - just one character (M or F)")
    private String gender;

    //..relationship with ProfessionModel
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    @Setter @Getter
    private DepModel departamento;
}
