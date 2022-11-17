package br.com.gcarpi.trabalhoFinal.repository;

import br.com.gcarpi.trabalhoFinal.model.MedicoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<MedicoModel, Long> {

    Optional<MedicoModel> findById(long id);

    List<MedicoModel> findByNameContainsIgnoreCaseOrderByName(String name);

}
