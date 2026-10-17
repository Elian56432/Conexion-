package com.projecto1.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SaludoController {

    @GetMapping("/saludo")
    public String saludar() {
        return "¡Holiss que hace!";
    }

    private List<Map<String, Object>> crearUsuarios() {
        List<Map<String, Object>> usuarios = new ArrayList<>();

        usuarios.add(crearUsuario(1, "Liam", true));
        usuarios.add(crearUsuario(2, "Angel", true));
        usuarios.add(crearUsuario(3, "Carlos", true));
        usuarios.add(crearUsuario(4, "Sofia", true));
        usuarios.add(crearUsuario(5, "Daniel", false));

        return usuarios;
    }

    private Map<String, Object> crearUsuario(
            Integer id, String nombre, boolean activo) {

        Map<String, Object> usuario = new HashMap<>();

        usuario.put("id", id);
        usuario.put("nombre", nombre);
        usuario.put("mensaje", "API jalando al 100");
        usuario.put("activo", activo);

        return usuario;
    }

    @GetMapping("/usuarios")
    public Object obtenerUsuarios(
             @RequestParam(name = "id", required = false) Integer id) {

        List<Map<String, Object>> usuarios = crearUsuarios();

        if (id == null) {
            return usuarios;
        }

        for (Map<String, Object> usuario : usuarios) {
            // Comparamos usando equals para que coincida correctamente el Integer
            if (id.equals(usuario.get("id"))) {
                return usuario;
            }
        }

        Map<String, Object> error = new HashMap<>();
        error.put("mensaje", "Usuario no encontrado");
        error.put("id", id);

        return error;
    }
}