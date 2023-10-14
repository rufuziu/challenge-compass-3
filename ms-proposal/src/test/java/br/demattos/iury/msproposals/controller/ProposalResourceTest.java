package br.demattos.iury.msproposals.controller;

import br.demattos.iury.msproposals.dto.proposal.ProposalDTO;
import br.demattos.iury.msproposals.exceptions.proposal_exce.ProposalAlreadyExistsException;
import br.demattos.iury.msproposals.services.PoolService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static br.demattos.iury.msproposals.common.ProposalConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProposalResource.class)
class ProposalResourceTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private PoolService service;

  @Test
  void createProposal_withValidData_ReturnsCreatedProposalDTO() throws
          Exception {
    ProposalDTO createProp = VALID_PROPOSALDTO1;
    createProp.setId(1L);
    when(service.createProposal(any())).thenReturn(createProp);
    mockMvc.perform(post("/api/v1/proposals")
                    .content(objectMapper.writeValueAsString(VALID_PROPOSALDTO1))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(createProp.getId()))
            .andExpect(jsonPath("$.description").value(createProp.getDescription()))
            .andExpect(jsonPath("$.closeTime").isNotEmpty());
  }

  @Test
  void createProposal_withRepeatedData_ReturnsConflict() throws
          Exception {
    when(service.createProposal(any())).thenThrow(ProposalAlreadyExistsException.class);
    mockMvc.perform(post("/api/v1/proposals")
                    .content(objectMapper.writeValueAsString(VALID_PROPOSALDTO1))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isConflict());
  }

  @Test
  void createProposal_withInvalidData_ReturnsBadRequest() throws
          Exception {
    mockMvc.perform(post("/api/v1/proposals")
                    .content(objectMapper.writeValueAsString(INVALID_PROPOSALDTO))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
  }

  @Test
  void getAllProposalsOpened_ReturnsProposalDTOList() throws Exception {
    List<ProposalDTO> list = new ArrayList<>();
    list.add(VALID_PROPOSALDTO1);
    list.add(VALID_PROPOSALDTO2);
    list.add(VALID_PROPOSALDTO3);
    list.add(VALID_PROPOSALDTO4);
    when(service.getAllOpenedProposals()).thenReturn(list);
    mockMvc.perform(get("/api/v1/proposals"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isNotEmpty());
  }
}