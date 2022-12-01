package br.com.gcarpi.trabalhoFinal.repository;

import br.com.gcarpi.trabalhoFinal.model.DepModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepRepository extends JpaRepository<DepModel, Long> {

    Optional<DepModel> findById(long id);

    public Page<DepModel> findAll(Pageable pageable);


    List<DepModel> findByNameContainsIgnoreCaseOrderByName(String name);

}
