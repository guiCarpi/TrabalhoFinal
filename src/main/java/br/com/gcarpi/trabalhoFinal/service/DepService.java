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

    public DepModel save(DepModel model){
        return repository.save(model);
    }

    public DepModel update(DepModel model){
        var found = repository.findById(model.getId());
        if(found.isPresent()){
            found.get().setName(model.getName());
            return repository.save(found.get());
        } else {
            return null;
        }
    }

    public void delete(long id){
        var found = repository.findById(id);
        if(found.isPresent()){
            repository.delete(found.get());
        }
    }

}
