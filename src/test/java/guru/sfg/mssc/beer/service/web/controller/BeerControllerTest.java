//: guru.sfg.mssc.beer.service.web.controller.BeerControllerTest.java


package guru.sfg.mssc.beer.service.web.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.sfg.mssc.beer.service.web.model.BeerDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;
import java.util.function.UnaryOperator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BeerController.class)
@DisplayName("CompletableFuture Test - ")
@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BeerControllerTest {

    static final String REQUEST_MAPPING = "/api/v1/beer";

    private String uuid;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UnaryOperator<String> uriFunc = uuid ->
            String.format("%s/%s", REQUEST_MAPPING, uuid);

    @BeforeEach
    void setUp() {
        this.uuid = UUID.randomUUID().toString();
    }

    @Test
    void test_Given_An_Id_When_Get_Beer_By_Id_Then_Get_200() throws Exception {

        // Given // When & Then
        this.mockMvc.perform(get(uriFunc.apply(this.uuid))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void test_Given_An_Beer_Obj_When_Save_Then_Get_Beer_Created()
            throws Exception {

        // Given
        BeerDto beerDto = BeerDto.builder().build();
        String beerDtoJson = this.objectMapper.writeValueAsString(beerDto);

        // When & Then
        this.mockMvc.perform(
                post(REQUEST_MAPPING)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void test_Given_An_Beer_Obj_With_UUID_When_Update_Then_Get_Beer_Updated()
            throws Exception {

        // Given
        BeerDto beerDto = BeerDto.builder().build();
        String beerDtoJson = this.objectMapper.writeValueAsString(beerDto);

        // When & Then
        this.mockMvc
                .perform(put(uriFunc.apply(this.uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

}///:~