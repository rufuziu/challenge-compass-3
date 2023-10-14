package br.demattos.iury.msproposals.common;

import br.demattos.iury.msproposals.dto.pool.PoolDTO;
import br.demattos.iury.msproposals.dto.proposal.ProposalDTO;
import br.demattos.iury.msproposals.enums.EResult;

import java.time.LocalDateTime;

public class ProposalConstants {
  private static LocalDateTime start = LocalDateTime.of(
          2023, 10, 13, 22, 0);
  private static LocalDateTime end = LocalDateTime.of(
          2023, 10, 13,
          22, 1);
  public static final ProposalDTO INVALID_PROPOSALDTO =
          new ProposalDTO();
  public static final ProposalDTO VALID_PROPOSALDTO1 =
          new ProposalDTO("proposal 1", end);
  public static final ProposalDTO VALID_PROPOSALDTO2 =
          new ProposalDTO("proposal 2", end);
  public static final ProposalDTO VALID_PROPOSALDTO3 =
          new ProposalDTO("proposal 3", end);
  public static final ProposalDTO VALID_PROPOSALDTO4 =
          new ProposalDTO("proposal 4", end);
  public static final ProposalDTO VALID_PROPOSALDTO_WITHOUTCLOSETIME =
          new ProposalDTO("proposal 5");
  public static final PoolDTO APPROVED_POOLDTO =
          new PoolDTO("proposal approved",
                  2L, 1L, start, end, EResult.APPROVED);
  public static final PoolDTO REJECTED_POOLDTO =
          new PoolDTO("proposal rejected",
                  1L, 2L, start, end, EResult.REJECTED);
  public static final PoolDTO DRAW_POOLDTO =
          new PoolDTO("proposal draw",
                  2L, 2L, start, end, EResult.DRAW);
}
