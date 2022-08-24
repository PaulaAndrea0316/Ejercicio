package com.udea.controller;

import com.udea.models.Empresa;
import com.udea.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import java.util.List;


@Controller
public class EmpresaController {

    //@Autowired //*
    //EmpresaService empresaService; //*

    @GetMapping({"/","/VerEmpresas"})//Cambie el GeTmapping

    public String viewEmpresas (Model model){
        //List<Empresa> listaEmpresa=empresaService.getAllEmpresas();
        //model.addAttribute("emplist",listaEmpresas);

        var empresa = new Empresa("UDEA","CALLE 72 A # 435","4849401","800106404");
        model.addAttribute("emp",empresa);

        return "verEmpresas";
    }
}




