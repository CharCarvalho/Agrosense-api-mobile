package com.mobileapi.fiap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mobileapi.fiap.assembler.VegetaisModelAssembler;
import com.mobileapi.fiap.model.VegetaisModel;
import com.mobileapi.fiap.service.VegetaisService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/vegetais")
public class VegetaisController {

    @Autowired
    private VegetaisService vegetaisService;

    @Autowired
    private VegetaisModelAssembler assembler;

    @PostMapping
    public CollectionModel<EntityModel<VegetaisModel>> createVegetais(@Validated @RequestBody List<VegetaisModel> vegetais) {
        List<EntityModel<VegetaisModel>> vegetaisModels = vegetaisService.createVegetais(vegetais).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(vegetaisModels,
                linkTo(methodOn(VegetaisController.class).getAllVegetais()).withSelfRel());
    }

    @GetMapping
    public CollectionModel<EntityModel<VegetaisModel>> getAllVegetais() {
        List<EntityModel<VegetaisModel>> vegetais = vegetaisService.getAllVegetais().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(vegetais,
                linkTo(methodOn(VegetaisController.class).getAllVegetais()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<VegetaisModel>> getVegetaisById(@PathVariable Long id) {
        return vegetaisService.getVegetaisById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{idCliente}/cliente")
    public CollectionModel<EntityModel<VegetaisModel>> getVegetaisByClienteId(@PathVariable Long idCliente) {
        List<EntityModel<VegetaisModel>> vegetais = vegetaisService.getVegetaisByClienteId(idCliente).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(vegetais,
                linkTo(methodOn(VegetaisController.class).getVegetaisByClienteId(idCliente)).withSelfRel());
    }


    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<VegetaisModel>> updateVegetais(@PathVariable Long id, @RequestBody VegetaisModel vegetaisDetails) {
        return vegetaisService.updateVegetais(id, vegetaisDetails)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<VegetaisModel>> patchVegetais(@PathVariable Long id, @RequestBody VegetaisModel vegetaisDetails) {
        return vegetaisService.patchVegetais(id, vegetaisDetails)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVegetais(@PathVariable Long id) {
        if (vegetaisService.deleteVegetais(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
