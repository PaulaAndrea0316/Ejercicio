package com.udea.controller;

import com.udea.models.Empresa;
import com.udea.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @GetMapping("/enterprises") // VER JSON TODAS LAS EMPRESAS
    public List<Empresa> verEmpresa (){
        return empresaService.getAllEmpresas();
    }

    @PostMapping("/enterprises") // GUARDAR EL JSON DEL BODY COMO UNA NUEVA EMPRESA O REGISTRO EN NUESTRA BD
    public Empresa guardarEmpresa(@RequestBody Empresa emp){
        return this.empresaService.saveOrUpdateEmpresa(emp);
    }

    @GetMapping(path = "enterprises/{id}")
    public Empresa empresaPorID(@PathVariable("id") Integer id){
        return this.empresaService.getEmpresaById(id);
    }

    @PatchMapping("/enterprises/{id}")
    public Empresa actualizarEmpresa(@PathVariable("id") Integer id, @RequestBody Empresa empresa) {
        Empresa emp = empresaService.getEmpresaById(id);
        emp.setNombre(empresa.getNombre());
        emp.setDireccion(empresa.getDireccion());
        emp.setTelefono(empresa.getTelefono());
        emp.setNit(empresa.getNit());
        return empresaService.saveOrUpdateEmpresa(emp);
    }

    @DeleteMapping (path= "enterprises/{id}") //Eliminar registro de la bd
    public String DeleteEmpresa(@PathVariable("id") Integer id){
        boolean respuesta= this.empresaService.deleteEmpresa(id);
        if (respuesta){  //Si respuesta es true?
            return "Se elimino la empresa con id" +id;
        }
        else{
            return "No se pudo eliminar la empresa con id"+id;
        }
    }







}





