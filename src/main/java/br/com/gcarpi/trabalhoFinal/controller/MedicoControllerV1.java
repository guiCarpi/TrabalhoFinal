package br.com.gcarpi.trabalhoFinal.controller;

import br.com.gcarpi.trabalhoFinal.model.MedicoModel;
import br.com.gcarpi.trabalhoFinal.service.MedicoServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/medicos/v1")
public class MedicoControllerV1 {

    @Autowired
    private MedicoServiceV1 service;

    @GetMapping("/{id}")
    public Optional<MedicoModel> findById(@PathVariable("id") long id){
        return service.findById(id);
    }

    @GetMapping
    public List<MedicoModel> findAll(){
        return  service.findAll();
    }

    @PostMapping
    public MedicoModel save(@RequestBody MedicoModel model){
        return service.save(model);
    }

    @PutMapping
    public MedicoModel update(@RequestBody MedicoModel model){
        return service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        Optional<MedicoModel> found = service.findById(id);
        if(found.isPresent()){
            service.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return null;
        }
    }
}
