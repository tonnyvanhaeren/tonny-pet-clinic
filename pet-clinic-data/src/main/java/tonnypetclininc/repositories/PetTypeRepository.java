package tonnypetclininc.repositories;

import org.springframework.data.repository.CrudRepository;
import tonnypetclininc.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
