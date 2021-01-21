package tonnypetclininc.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tonnypetclininc.model.*;
import tonnypetclininc.services.OwnerService;
import tonnypetclininc.services.PetTypeService;
import tonnypetclininc.services.VetService;
import tonnypetclininc.services.map.SpecialityMapService;
import tonnypetclininc.services.map.VisitMapService;

import java.time.LocalDate;


@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityMapService specialityMapService;
    private final VisitMapService visitMapService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityMapService specialityMapService, VisitMapService visitMapService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityMapService = specialityMapService;
        this.visitMapService = visitMapService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();

        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType =  petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType =  petTypeService.save(cat);

        System.out.println("Loaded PetTypes...");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityMapService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityMapService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityMapService.save(dentistry);

        System.out.println("Loaded Vet Specialities...");

        Owner owner1 = new Owner();
        owner1.setFirstName("Antonius");
        owner1.setLastName("Vanhaeren");
        owner1.setAddress("Gelaagstraat 43");
        owner1.setCity("Rupelmonde");
        owner1.setTelephone("123121212");

        Pet antoniusPet = new Pet();
        antoniusPet.setPetType(savedDogPetType);
        antoniusPet.setOwner(owner1);
        antoniusPet.setBirthDate(LocalDate.now());
        antoniusPet.setName("Woef");
        owner1.getPets().add(antoniusPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Bonny");
        owner2.setLastName("Vanhaeren");
        owner2.setAddress("Anthon Piekstraat");
        owner2.setCity("Schoten");
        owner2.setTelephone("123121212");

        Pet bonnyPet = new Pet();
        bonnyPet.setPetType(savedCatPetType);
        bonnyPet.setOwner(owner2);
        bonnyPet.setBirthDate(LocalDate.now());
        bonnyPet.setName("Miauw");
        owner2.getPets().add(bonnyPet);

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Visit catVisit = new Visit();
        catVisit.setPet(bonnyPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");
        visitMapService.save(catVisit);

        System.out.println("Loaded Visits...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded veterinarians...");
    }
}
