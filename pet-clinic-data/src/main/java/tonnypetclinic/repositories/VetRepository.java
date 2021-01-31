package tonnypetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import tonnypetclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
