package tonnypetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tonnypetclinic.model.Speciality;
import tonnypetclinic.model.Vet;
import tonnypetclinic.services.VetService;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetRestControllerTest {

    private Set<Vet> vets = new HashSet<>();

    private MockMvc mockMvc;

    @Mock
    private VetService vetService;

    @InjectMocks
    VetRestController controller;

    @BeforeEach
    void setUp() {
        Vet james = new Vet();
        james.setFirstName("James");
        james.setLastName("Carter");
        james.setId(1L);

        Vet helen = new Vet();
        helen.setFirstName("Helen");
        helen.setLastName("Leary");
        helen.setId(2L);

        Speciality radiology = new Speciality();
        radiology.setId(1L);
        radiology.setDescription("radiology");

        helen.getSpecialities().add(radiology);

        vets.add(james);
        vets.add(helen);

        given(vetService.findAll()).willReturn(vets);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("GET /widgets success")
    void testGetWidgetsSuccess() throws Exception {

        // Execute the GET request
        mockMvc.perform(get("/api/vets"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate headers
                // .andExpect(header().string(HttpHeaders.LOCATION, "/api/vets"))

                // Validate the returned fields
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("James")))
                .andExpect(jsonPath("$[0].lastName", is("Carter")));

    }
}