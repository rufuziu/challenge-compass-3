package br.demattos.iury.msproposals.services;

import br.demattos.iury.msproposals.dto.draw.DrawDTO;
import br.demattos.iury.msproposals.entities.Draw;
import br.demattos.iury.msproposals.repositories.DrawRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static br.demattos.iury.msproposals.common.ProposalConstants.DRAW_POOLDTO;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DrawServiceTest {
  @InjectMocks
  private DrawService service;
  @Spy
  private ModelMapper mapper;
  @Mock
  private DrawRepository repository;

  @Test
  void createDraw_withDrawDTO() {
    //given
    DrawDTO dto = mapper.map(DRAW_POOLDTO, DrawDTO.class);
    Draw entity = mapper.map(dto, Draw.class);
    entity.setId(1L);
    when(repository.save(any())).thenReturn(entity);
    //then
    service.createDraw(dto);
    //assert
    assertAll("DrawService create draw",
            () -> assertEquals(1L, entity.getId()),
            () -> assertEquals(dto.getResult(), entity.getResult()));
  }
}