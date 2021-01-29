package tonnypetclininc.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tonnypetclininc.model.Pet;
import tonnypetclininc.services.PetService;
import tonnypetclininc.services.PetTypeService;
import tonnypetclininc.services.VisitService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
    private static final Long TEST_PET_ID = 1L;

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void initNewVisitForm() throws Exception{

        when(petService.findById(TEST_PET_ID)).thenReturn(new Pet());

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/visits/new", TEST_PET_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdateVisitForm"));
    }

    @Test
    void processNewVisitForm() throws Exception {
        when(petService.findById(TEST_PET_ID)).thenReturn(new Pet());

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/*/pets/{petId}/visits/new", TEST_PET_ID)
                .param("name", "George")
                .param("description", "Visit Description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"));
    }
}