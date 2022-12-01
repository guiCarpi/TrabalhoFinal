package br.com.gcarpi.trabalhoFinal.repository;

import br.com.gcarpi.trabalhoFinal.model.MedicoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoModel, Long> {

    Optional<MedicoModel> findById(long id);

    public Page<MedicoModel> findAll(Pageable pageable);

    List<MedicoModel> findByNameContainsIgnoreCaseOrderByName(String name);

}
