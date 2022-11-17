package br.com.gcarpi.trabalhoFinal.repository;

import br.com.gcarpi.trabalhoFinal.model.DepModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepRepository extends JpaRepository<DepModel, Long> {

    Optional<DepModel> findById(long id);

}
