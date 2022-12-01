package br.com.gcarpi.trabalhoFinal.controller;

import br.com.gcarpi.trabalhoFinal.model.DepModel;
import br.com.gcarpi.trabalhoFinal.service.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dep")
public class DepController {


    @Autowired
    private DepService service;

    @GetMapping("/{id}")
    public DepModel  findById(@PathVariable("id") long id){
        var depModel = service.findById(id);
        if (depModel.isPresent()) {
            buildEndityLink(depModel.get());
            return depModel.get();
        } else {
            return null;
        }
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PagedModel<DepModel>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler<DepModel> assembler
    ){
        var sortDirection = "desc".equals(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        Page<DepModel> pessoas = service.findAll(pageable);

        for(DepModel pessoa : pessoas){
            buildEndityLink(pessoa);
        }

        return new ResponseEntity(assembler.toModel(pessoas), HttpStatus.OK);

    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE},
    consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public DepModel save(@RequestBody DepModel model) {
        return service.save(model);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public DepModel update(@RequestBody DepModel model) {
        return service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        Optional<DepModel> found = service.findById(id);
        if (found.isPresent()) {
            service.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return null;
        }
    }

    private void buildEndityLink(DepModel model) {
        model.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                this.getClass()).findById(model.getId())
                ).withSelfRel()
        );
    }
}
