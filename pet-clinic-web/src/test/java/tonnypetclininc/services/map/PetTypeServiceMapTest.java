package tonnypetclininc.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tonnypetclininc.model.PetType;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeServiceMapTest {
    PetTypeServiceMap petTypeServiceMap;
    final Long petTypeId = 1L;

    @BeforeEach
    void setUp() {
        petTypeServiceMap = new PetTypeServiceMap();
        petTypeServiceMap.save(PetType.builder().id(petTypeId).build());
    }

    @Test
    void findAll() {
        Set<PetType> petTypes = petTypeServiceMap.findAll();
        assertEquals(1, petTypes.size());
    }

    @Test
    void deleteById() {
        petTypeServiceMap.deleteById(petTypeId);
        assertEquals(0, petTypeServiceMap.findAll().size());
    }

    @Test
    void delete() {
        petTypeServiceMap.delete(petTypeServiceMap.findById(petTypeId));
        assertEquals(0, petTypeServiceMap.findAll().size());
    }

    @Test
    void save() {
        petTypeServiceMap.save(PetType.builder().id(2L).build());
        assertEquals(2, petTypeServiceMap.findAll().size());
    }

    @Test
    void findById() {
        PetType petType = petTypeServiceMap.findById(petTypeId);
        assertEquals(1, petType.getId());
    }
}