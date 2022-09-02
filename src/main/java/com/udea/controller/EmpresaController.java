package com.udea.controller;

import com.udea.models.Empleado;
import com.udea.models.Empresa;
import com.udea.models.MovimientoDinero;
import com.udea.service.EmpleadoService;
import com.udea.service.EmpresaService;
import com.udea.service.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;
    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    MovimientosService movimientosService;

    //EMPRESAS
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

    //EMPLEADOS
    @GetMapping("/empleados") //Ver json de todas los empleados
    public List<Empleado> verEmpleados(){
        return empleadoService.getAllEmpleado();
    }

    @PostMapping("/empleados") //Guardar un empleado nuevo
    public Optional<Empleado> guardarEmpleado(@RequestBody Empleado empl){
        return Optional.ofNullable(this.empleadoService.saveOrUpdateEmpleado(empl));
    }
    @GetMapping(path = "empleados/{id}")//Consultar empleado por ID
    public Optional<Empleado> empleadoPorID(@PathVariable("id") Integer id){
        return this.empleadoService.getEmpleadoById(id);
    }

    @GetMapping("/enterprises/{id}/empleados")// Consultar empleados por empresa
    public ArrayList<Empleado> EmpleadoPorEmpresa(@PathVariable("id") Integer id){
        return this.empleadoService.obtenerPorEmpresa(id);
    }

    @PatchMapping("/empleados/{id}")
    public  Empleado actualizarEmpleado(@PathVariable("id") Integer id,@RequestBody Empleado empleado){
        Empleado empl=empleadoService.getEmpleadoById(id).get();
        empl.setNombre(empleado.getNombre());
        empl.setCorreo(empleado.getCorreo());
        empl.setEmpresa(empleado.getEmpresa());
        empl.setRol(empleado.getRol());
        return empleadoService.saveOrUpdateEmpleado(empl);
    }

    @DeleteMapping("/empleados/{id}") //Metodo para eliminar empleados por id
    public String DeleteEmpleado(@PathVariable("id") Integer id){
        boolean respuesta=empleadoService.deleteEmpleado(id); //eliminamos usando el servicio de nuestro service
        if (respuesta){ //si la respuesta booleana es true, si se eliminò
            return "Se pudo eliminar correctamente el empleado con id "+id;
        }//Si la respuesta booleana es false, no se eliminó
        return "No se puedo eliminar correctamente el empleado con id "+id;
    }

    //MOVIMIENTOS
    @GetMapping("/movimientos") //Consultar todos los movimientos
    public List<MovimientoDinero> verMovimientos(){
        return movimientosService.getAllMovimientos();
    }
    @PostMapping("/movimientos")
    public MovimientoDinero guardarMovimiento(@RequestBody MovimientoDinero movimiento){
        return movimientosService.saveOrUpdateMovimiento(movimiento);
    }

    @GetMapping("/movimientos/{id}") //Consultar movimiento por id
    public MovimientoDinero movimientoPorId(@PathVariable("id") Integer id){
        return movimientosService.getMovimientoById(id);
    }
    @PatchMapping("/movimientos/{id}")//Editar o actualizar un movimiento
    public MovimientoDinero actualizarMovimiento(@PathVariable("id") Integer id, @RequestBody MovimientoDinero movimiento){
        MovimientoDinero mov=movimientosService.getMovimientoById(id);
        mov.setConcepto(movimiento.getConcepto());
        mov.setMonto(movimiento.getMonto());
        mov.setUsuario(movimiento.getUsuario());
        return movimientosService.saveOrUpdateMovimiento(mov);
    }


}





