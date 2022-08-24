package com.udea.controller;

import com.udea.models.Empresa;
import com.udea.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;


@Controller
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @GetMapping({"/","/VerEmpresas"})//Cambie el GeTmapping

    public String viewEmpresas (Model model){
        List<Empresa> listaEmpresa=empresaService.getAllEmpresas();
        model.addAttribute("emplist",listaEmpresa);

        return "verEmpresas";
    }

    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model){
        Empresa emp= new Empresa();
        model.addAttribute("emp", emp);
        return "agregarEmpresa";
    }

    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa emp, RedirectAttributes redirectAttributes){

        if(empresaService.saveOrUpdateEmpresa(emp)==true){
        return "redirect:/VerEmpresas";
    }
        return "redirect:/AgregarEmpresa";
    }

}





