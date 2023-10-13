package br.demattos.iury.msproposals.dto.proposal;

import java.time.LocalDateTime;

public class ProposalDTO {
  private Long id;
  private String description;
  private LocalDateTime closeTime;

  public ProposalDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getCloseTime() {
    return closeTime;
  }

  public void setCloseTime(LocalDateTime closeTime) {
    this.closeTime = closeTime;
  }
}
