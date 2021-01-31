package tonnypetclininc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tonnypetclininc.model.Owner;
import tonnypetclininc.model.Pet;
import tonnypetclininc.model.PetType;
import tonnypetclininc.services.OwnerService;
import tonnypetclininc.services.PetService;
import tonnypetclininc.services.PetTypeService;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final PetTypeService petTypeService;
    private final PetService petService;
    private final OwnerService ownerService;

    public PetController(PetTypeService petTypeService, PetService petService, OwnerService ownerService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
        this.ownerService = ownerService;
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder("pet")
    public void initPetBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(String text) throws IllegalArgumentException{
                setValue(LocalDate.parse(text));
            }
        });
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes(){
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId){
        return ownerService.findById(ownerId);
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);

        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {

        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }

        // owner.getPets().add(pet);

        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }
        else {
            owner.getPets().add(pet);
            pet.setOwner(owner);
            Pet savedPet = petService.save(pet);

            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable("petId") Long petId, ModelMap model) {
        model.put("pet", petService.findById(petId));

        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, Model model) {

        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);

            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }
        else {
            pet.setOwner(owner);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

}
