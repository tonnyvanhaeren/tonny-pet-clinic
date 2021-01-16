package tonnypetclininc.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tonnypetclininc.model.Owner;
import tonnypetclininc.model.Vet;
import tonnypetclininc.services.OwnerService;
import tonnypetclininc.services.VetService;


@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
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
