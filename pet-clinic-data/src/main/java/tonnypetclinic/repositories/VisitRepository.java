package tonnypetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import tonnypetclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
