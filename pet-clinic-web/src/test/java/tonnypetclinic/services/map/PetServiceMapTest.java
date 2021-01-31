package tonnypetclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tonnypetclinic.model.Pet;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetServiceMapTest {
    PetServiceMap petServiceMap;

    final Long petId = 1L;

    @BeforeEach
    void setUp() {
        petServiceMap = new PetServiceMap();
        petServiceMap.save(Pet.builder().id(petId).build());
    }

    @Test
    void findAll() {
       Set<Pet> pets =  petServiceMap.findAll();
       assertEquals(1, pets.size());
    }

    @Test
    void findById() {
        Pet pet = petServiceMap.findById(petId);
        assertEquals(petId, pet.getId());
    }

    @Test
    void save() {
        petServiceMap.save(Pet.builder().id(2L).build());
        assertEquals(2, petServiceMap.findAll().size());
    }

    @Test
    void delete() {
        petServiceMap.delete(petServiceMap.findById(petId));
        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        petServiceMap.deleteById(petId);
        assertEquals(0, petServiceMap.findAll().size());
    }
}