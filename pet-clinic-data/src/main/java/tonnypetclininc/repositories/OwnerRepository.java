package tonnypetclininc.repositories;

import org.springframework.data.repository.CrudRepository;
import tonnypetclininc.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);
}
