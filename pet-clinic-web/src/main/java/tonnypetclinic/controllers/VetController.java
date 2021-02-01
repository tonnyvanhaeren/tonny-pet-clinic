package tonnypetclinic.controllers;

import nonapi.io.github.classgraph.json.JSONSerializer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tonnypetclinic.services.VetService;
import tonnypetclinic.model.Vet;

import java.awt.*;
import java.util.Set;

@Controller
public class VetController {
    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"/vets", "/vets/index", "/vets/index.html", "vets.html"})
    public String listVets(Model model)
    {
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }
}
