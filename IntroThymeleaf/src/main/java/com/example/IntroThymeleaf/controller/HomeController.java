package com.example.IntroThymeleaf.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(@RequestParam(name="nombre", required = false, defaultValue = "invitado") String nombre, Model model){
        model.addAttribute("mensajeBienvenida", "Hola Mundo! Desde Spring Boot");
        model.addAttribute("nombreUsuario", nombre);

        return "index";
    }

    @GetMapping("/saludo")
    public String saludo(Model model, HttpSession session){
        LocalTime ahora = LocalTime.now();
        int hora = ahora.getHour();

        String saludo;
        String claseCss;

        if(hora >= 6 && hora <= 12){
            saludo = "Buenos días!!! Hora de chambear";
            claseCss = "color: #198754";
        } else if( hora >= 12 && hora <= 17){
            saludo = "Buenas tardes! No t duermas Emy";
            claseCss = "color: #0d6efd";
        } else {
            saludo = "Wenas nochissss";
            claseCss = "#color: #dc3545";
        }

        model.addAttribute("saludo", saludo);
        model.addAttribute("claseCss", claseCss);

        session.setAttribute("operador", "Admin");
        return "saludo";
    }

    @GetMapping("/catalogo")
    public String catalogo(Model model){
        List<Map<String, Object>> juegos = new ArrayList<>();

        juegos.add(crearJuego("Detroit Become Human", "RPG", "PLaystation", 1200, true));
        juegos.add(crearJuego("Minecraft", "Aventurero", "Xbox", 800, false));
        juegos.add(crearJuego("Cyberpunk 2077", "RPG", "Playstation", 1500, true));

        model.addAttribute("juegos", juegos);
        return "catalogo";
    }

    private Map<String, Object> crearJuego(String titulo, String genero, String plataforma, double precio, boolean disponible){
        Map<String, Object> juego = new HashMap<>();
        juego.put("titulo", titulo);
        juego.put("genero", genero);
        juego.put("plataforma", plataforma);
        juego.put("precio", precio);
        juego.put("disponible", disponible);
        return juego;
    }
}
