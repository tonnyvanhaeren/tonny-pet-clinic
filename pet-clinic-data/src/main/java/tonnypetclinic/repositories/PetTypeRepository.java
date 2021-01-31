package tonnypetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import tonnypetclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
