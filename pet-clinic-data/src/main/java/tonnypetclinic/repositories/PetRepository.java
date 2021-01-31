package tonnypetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import tonnypetclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
