package tonnypetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import tonnypetclinic.model.Speciality;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
