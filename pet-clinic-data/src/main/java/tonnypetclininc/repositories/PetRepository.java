package tonnypetclininc.repositories;

import org.springframework.data.repository.CrudRepository;
import tonnypetclininc.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
