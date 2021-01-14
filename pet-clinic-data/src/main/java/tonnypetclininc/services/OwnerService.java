package tonnypetclininc.services;

import tonnypetclininc.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastname);
}
