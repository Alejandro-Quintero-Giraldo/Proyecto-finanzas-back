package co.com.finanzas.domain.infra.controller;

import co.com.finanzas.domain.infra.repository.IBolsilloDataRepository;
import co.com.finanzas.useCase.TransformacionBolsilloUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BolsilloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBolsilloDataRepository data;

    @MockBean
    private TransformacionBolsilloUseCase transformacionBolsilloUseCase;

    @Test
    @DisplayName("POST /api/crearBolsillo Success")
    public void crearBolsilloTest() throws Exception{
        String id = "xxxx";
        String nombre = "Transporte";
        String uid = "123";
        Integer porcentajeAhorro = 10;

        mockMvc.perform(MockMvcRequestBuilders.post(
                "api/crearbolsillo/{bolsilloId}/{nombre}/{uid}/{porcentajeAhorro}",id,nombre,uid,porcentajeAhorro)
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
                /*.andExpect(ontent().string("{" + "\"BolsilloId\":"+ "\"xxxx"+"\""+","
                        +"\"Nombre\":"+"\"Transporte"+"\""+","
                        +"\"Saldo disponible\":"+"\"0"+"\""+","
                        +"\"UsuarioId\":"+"\"123"+"\""+","
                        +"\"¿Es Ahorro?\":"+"\"false"+"\""+","
                        +"\"Porcentaje de ahorro\":"+"\"10"+ "\""
                        +"}"));

        //"{\"BolsilloId\":\"xxxx\",\"Nombre\":\"
        ))*/
    }
}