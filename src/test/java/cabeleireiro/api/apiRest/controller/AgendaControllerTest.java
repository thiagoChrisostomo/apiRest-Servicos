package cabeleireiro.api.apiRest.controller;

import cabeleireiro.api.apiRest.domain.agenda.Agenda;
import cabeleireiro.api.apiRest.domain.agenda.DadosAgendamento;
import cabeleireiro.api.apiRest.domain.agenda.DadosDetalhamentoAgendamento;
import cabeleireiro.api.apiRest.domain.usuario.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AgendaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    JacksonTester<DadosAgendamento> dadosAgendamentoJson;

    @Autowired
    JacksonTester<DadosDetalhamentoAgendamento> dadosDetalhamentoAgendamentoJson;

    @MockBean
    private Agenda agenda;

    @MockBean
    private Usuario usuario;

    @Test
    @DisplayName("Deveria devolver código http 400, quando informações estão inválidas")
    @WithMockUser
    void agendar_cenario2() throws Exception {

        var response = mvc.perform(MockMvcRequestBuilders.post("/agendas")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 200, quando informações estão válidas")
    @WithMockUser
    void agendar_cenario1() throws Exception {

        var data = LocalDateTime.now().plusHours(1);

        var dadosDetalhamento = new DadosDetalhamentoAgendamento(null, 3l, 2l, 1l, 1l, data, data.plusMinutes(30));

        var usuario = new Usuario(1l, "thiago", "123456");

        when(agenda.agendar(any(), ArgumentMatchers.eq(usuario))).thenReturn(dadosDetalhamento);
//        when(agenda.agendar(any()).thenReturn(dadosDetalhamento);

        var response = mvc.perform(MockMvcRequestBuilders.post("/agendas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosAgendamentoJson.write(
                        new DadosAgendamento(3l, 2l, 1l, data)
                ).getJson())
        )
        .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoAgendamentoJson.write(
                dadosDetalhamento
        ).getJson();
        System.out.println("Response: " + response);
        System.out.println("Esperado: " + jsonEsperado);
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


}