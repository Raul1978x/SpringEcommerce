package com.my.ecommerce.spring.servicio;

import com.my.ecommerce.spring.entidades.Orden;
import com.my.ecommerce.spring.repositorios.IOrdenRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Raúl Gómez
 */
@Service
public class OrdenServicioImpl implements IOrdenServicio{

    @Autowired
    private IOrdenRepositorio iOrdenRepositorio;
    
    @Override
    public Orden save(Orden orden) {
        return iOrdenRepositorio.save(orden);
    }

    @Override
    public List<Orden> findAll() {
       return iOrdenRepositorio.findAll();
    }

    @Override
    public String generarNumeroOrden(){
        int numero=0;
        String numeroConcatenado="";
        
        List<Orden> ordenes = findAll();
        List<Integer> numeros = new ArrayList<>();
        ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));
        
        if(ordenes.isEmpty()){
            numero = 1;
        }else {
            numero=numeros.stream().max(Integer::compare).get();
            numero++;
        }
        
        if(numero<10){
            numeroConcatenado="000000000"+numero;
        }else if(numero<100){
            numeroConcatenado="00000000"+numero;
        }else if(numero<1000){
            numeroConcatenado="0000000"+numero;
        }else if(numero<10000){
            numeroConcatenado="000000"+numero;
        }else if(numero<100000){
            numeroConcatenado="00000"+numero;
        }else if(numero<1000000){
            numeroConcatenado="0000"+numero;
        }else if(numero<1000000){
            numeroConcatenado="000"+numero;
        }else if(numero<10000000){
            numeroConcatenado="00"+numero;
        }else if(numero<100000000){
            numeroConcatenado="0"+numero;
        }
        return numeroConcatenado;
    }
    
}
