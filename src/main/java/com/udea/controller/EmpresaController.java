package com.udea.controller;

import com.udea.models.Empresa;
import com.udea.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;


@Controller
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @GetMapping({"/","/VerEmpresas"})//Cambie el GeTmapping
    public String viewEmpresas (Model model, @ModelAttribute("mensaje") String mensaje){
        List<Empresa> listaEmpresa=empresaService.getAllEmpresas();
        model.addAttribute("emplist",listaEmpresa);
        model.addAttribute("mensaje",mensaje);
        return "verEmpresas"; // llamamos el HTML
    }

    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model, @ModelAttribute("mensaje") String mensaje){
        Empresa emp= new Empresa();
        model.addAttribute("emp", emp);
        return "agregarEmpresa";
    }

    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa emp, RedirectAttributes redirectAttributes){
        if(empresaService.saveOrUpdateEmpresa(emp)==true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
             return "redirect:/VerEmpresas";
    }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
            return "redirect:/AgregarEmpresa"; // Nos redirecciona al servicio
    }

    @GetMapping("/EditarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable Integer id){
        Empresa emp=empresaService.getEmpresaById(id);
        //Creamos un atributo para el modelo, que se llame igualmente emp y es el que ira al html para llenar o alimentar campos
        model.addAttribute("emp",emp);
        return "editarEmpresa";

    }

    @PostMapping("/ActualizarEmpresa")
    public String updateEmpresa(Empresa emp, RedirectAttributes redirectAttributes ){

        if(empresaService.saveOrUpdateEmpresa(emp)==true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveOK");
        return "redirect:/EditarEmpresa";
    }

    @GetMapping("/EliminarEmpresa/{id}")
    public String eliminarEmpresa(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        try{
            empresaService.deleteEmpresa(id);
            redirectAttributes.addFlashAttribute("mensaje","deleteOK"); // se Genera Mensaje al usuario
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("mensaje","deleteError");
            return "redirect:/VerEmpresas";
        }
            return "redirect:/VerEmpresas";
    }

}





