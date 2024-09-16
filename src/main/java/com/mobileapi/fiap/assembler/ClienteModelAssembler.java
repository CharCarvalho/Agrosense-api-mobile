package com.mobileapi.fiap.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.mobileapi.fiap.controller.ClienteController;
import com.mobileapi.fiap.model.ClienteModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<ClienteModel, EntityModel<ClienteModel>> {
    
    @Override
    public EntityModel<ClienteModel> toModel(ClienteModel cliente) {
        return EntityModel.of(cliente,
                linkTo(methodOn(ClienteController.class).getClienteById(cliente.getId_cliente())).withSelfRel(),
                linkTo(methodOn(ClienteController.class).getAllClientes()).withRel("clientes"));
    }
}
