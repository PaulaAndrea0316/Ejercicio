package com.udea.service;

import com.udea.models.Empresa;
import com.udea.repo.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

//Le decimos a Spring que esta clase es de servicios
@Service
public class EmpresaService {
    @Autowired //Conectamos esta clase con el repository de Empresa
    EmpresaRepository empresaRepository;

    public List<Empresa> getAllEmpresas() {
        List<Empresa> empresasList = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> empresasList.add(empresa));
        return empresasList;
    }
    //Metodo que me trae un objeto de tipo Empresa cuando cuento con el id de la misma
    public Empresa getEmpresaById(Integer id) {
        return empresaRepository.findById(id).get();
    }


    //Metodo para guardar o actualizar objetos de tipo Empresa
    public boolean saveOrUpdateEmpresa(Empresa empresa) {
        Empresa emp = empresaRepository.save(empresa);
        if (empresaRepository.findById(emp.getId()) != null) {

            return true;
        }
        return false;
    }
    //Metodo para eliminar empresas registradas teniendo el id
    public boolean deleteEmpresa(Integer id) {
        empresaRepository.deleteById(id);
        if (getEmpresaById(id)!=null){
        return false;
        }
        return true;
    }


}
