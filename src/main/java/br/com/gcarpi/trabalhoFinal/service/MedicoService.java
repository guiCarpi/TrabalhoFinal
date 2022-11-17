package br.com.gcarpi.trabalhoFinal.service;

import br.com.gcarpi.trabalhoFinal.model.MedicoModel;
import br.com.gcarpi.trabalhoFinal.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public Optional<MedicoModel> findById(long id){
        return repository.findById(id);
    }

    public List<MedicoModel> findAll(){
        return repository.findAll();
    }

    public MedicoModel save(MedicoModel model){
        return repository.save(model);
    }

    public MedicoModel update(MedicoModel model){
        var found = repository.findById(model.getId());
        if(found.isPresent()){
            found.get().setName(model.getName());
            found.get().setGender(model.getGender());
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
