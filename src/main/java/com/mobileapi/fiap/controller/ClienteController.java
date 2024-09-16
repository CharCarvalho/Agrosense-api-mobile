package com.mobileapi.fiap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mobileapi.fiap.assembler.ClienteModelAssembler;
import com.mobileapi.fiap.model.ClienteModel;
import com.mobileapi.fiap.service.ClienteService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteModelAssembler assembler;

    @PostMapping
    public CollectionModel<EntityModel<ClienteModel>> createClientes(@Validated @RequestBody List<ClienteModel> clientes) {
        List<EntityModel<ClienteModel>> clienteModels = clienteService.createClientes(clientes).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(clienteModels,
                linkTo(methodOn(ClienteController.class).getAllClientes()).withSelfRel());
    }

    @GetMapping
    public CollectionModel<EntityModel<ClienteModel>> getAllClientes() {
        List<EntityModel<ClienteModel>> clientes = clienteService.getAllClientes().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(clientes,
                linkTo(methodOn(ClienteController.class).getAllClientes()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ClienteModel>> getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ClienteModel>> updateCliente(@PathVariable Long id, @RequestBody ClienteModel clienteDetails) {
        return clienteService.updateCliente(id, clienteDetails)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<ClienteModel>> patchCliente(@PathVariable Long id, @RequestBody ClienteModel clienteDetails) {
        return clienteService.patchCliente(id, clienteDetails)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (clienteService.deleteCliente(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
