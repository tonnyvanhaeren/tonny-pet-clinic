package tonnypetclininc.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tonnypetclininc.model.Owner;
import tonnypetclininc.model.PetType;
import tonnypetclininc.model.Vet;
import tonnypetclininc.services.OwnerService;
import tonnypetclininc.services.PetTypeService;
import tonnypetclininc.services.VetService;


@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType =  petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType =  petTypeService.save(cat);

        System.out.println("Loaded PetTypes...");

        Owner owner1 = new Owner();
        owner1.setFirstName("Antonius");
        owner1.setLastName("Vanhaeren");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Bonny");
        owner2.setLastName("Vanhaeren");

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("Loaded veterinarians...");
    }
}
