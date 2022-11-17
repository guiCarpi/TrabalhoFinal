package br.com.gcarpi.trabalhoFinal.service;

import br.com.gcarpi.trabalhoFinal.model.DepModel;
import br.com.gcarpi.trabalhoFinal.repository.DepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepService {

    @Autowired
    private DepRepository repository;

    public List<DepModel> findAll(){
        return repository.findAll();
    }

    public Optional<DepModel> findById(long id){
        return repository.findById(id);
    }

}
