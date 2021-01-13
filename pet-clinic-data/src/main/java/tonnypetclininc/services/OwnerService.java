package tonnypetclininc.services;

import tonnypetclininc.model.Owner;

import java.util.Set;

public interface OwnerService {

    Owner findByLastName(String lastname);
    Owner findById(long id);
    Owner save(Owner owner);
    Set<Owner> findAll();

}
