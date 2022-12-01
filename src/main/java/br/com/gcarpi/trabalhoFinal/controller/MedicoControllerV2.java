package br.com.gcarpi.trabalhoFinal.controller;

import br.com.gcarpi.trabalhoFinal.model.MedicoModel;
import br.com.gcarpi.trabalhoFinal.service.MedicoServiceV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
@Api(value = "Medico Endpoint Version V2")
public class MedicoControllerV2 {


    @Autowired
    private MedicoServiceV2 service;

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value = "Returns a Medico by ID")
    public MedicoModel findById(
            @ApiParam(name = "id", value = "A valid integer value", required = true)
            @PathVariable("id") long id){
        var medicoModel = service.findById(id);
        if(medicoModel.isPresent()){
            buildEntityLink(medicoModel.get());
            return medicoModel.get();
        } else {
            return null;
        }

    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PagedModel<MedicoModel>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler<MedicoModel> assembler
    ){
        var sortDirection = "desc".equals(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        Page<MedicoModel> medicos = service.findAll(pageable);

        for(MedicoModel medico : medicos){
            buildEntityLink(medico);
        }

        return new ResponseEntity(assembler.toModel(medicos), HttpStatus.OK);

    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public MedicoModel save(@RequestBody MedicoModel model){
        return service.save(model);
    }

    @PutMapping( produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
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


    private void buildEntityLink(MedicoModel model){
        model.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                this.getClass()).findById(model.getId())
                ).withSelfRel()
        );

        if(!model.getDep().hasLinks()) {
            Link depLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(
                            DepController.class).findById(model.getDep().getId())
            ).withSelfRel();
            model.getDep().add(depLink);
        }
    }
}
