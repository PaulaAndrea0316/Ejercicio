package com.udea.controller;

import com.udea.models.Empresa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmpresaController {

    @GetMapping("/")
    public String inicio(Model model){
        var empresa = new Empresa("UDEA","CALLE 72 A # 435","4849401","800106404");
        model.addAttribute("emp",empresa);
        return "verEmpresas";
    }
}




