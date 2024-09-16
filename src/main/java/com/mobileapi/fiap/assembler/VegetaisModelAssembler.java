package com.mobileapi.fiap.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.mobileapi.fiap.controller.VegetaisController;
import com.mobileapi.fiap.model.VegetaisModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class VegetaisModelAssembler implements RepresentationModelAssembler<VegetaisModel, EntityModel<VegetaisModel>> {

    @Override
    public EntityModel<VegetaisModel> toModel(VegetaisModel vegetais) {
        return EntityModel.of(vegetais,
                linkTo(methodOn(VegetaisController.class).getVegetaisById(vegetais.getIdVegetais())).withSelfRel(),
                linkTo(methodOn(VegetaisController.class).getAllVegetais()).withRel("vegetais"),
                linkTo(methodOn(VegetaisController.class).getVegetaisByClienteId(vegetais.getIdCliente())).withRel("clienteVegetais"));
    }
}
