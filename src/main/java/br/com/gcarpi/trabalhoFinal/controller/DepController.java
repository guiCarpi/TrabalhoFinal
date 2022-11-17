package br.com.gcarpi.trabalhoFinal.controller;

import br.com.gcarpi.trabalhoFinal.model.DepModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class DepController {
    @GetMapping("/{id}")
    public Optional<DepModel> findById(@PathVariable("id") long id){
        return service.findById(id);
    }

    @GetMapping
    public List<DepModel> findAll(){
        return  service.findAll();
    }

    @PostMapping
    public DepModel save(@RequestBody DepModel model){
        return service.save(model);
    }

    @PutMapping
    public DepModel update(@RequestBody DepModel model){
        return service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        Optional<DepModel> found = service.findById(id);
        if(found.isPresent()){
            service.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return null;
        }
    }
}
