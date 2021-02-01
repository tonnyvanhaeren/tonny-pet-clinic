package tonnypetclinic.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tonnypetclinic.model.Vet;
import tonnypetclinic.services.VetService;

import java.util.Set;

@RestController
public class VetRestController {
    private final VetService vetService;


    public VetRestController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping(value = "/api/vets", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Vet>> getVetsJson(){
        return ResponseEntity.ok()
                .body(vetService.findAll());
        //return vetService.findAll() ;
    }
}
