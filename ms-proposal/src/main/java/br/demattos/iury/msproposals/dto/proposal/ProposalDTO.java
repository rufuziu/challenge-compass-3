package br.demattos.iury.msproposals.dto.proposal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public class ProposalDTO {
  public ProposalDTO() {
  }
  private Long id;
  @NotEmpty
  private String description;
  private LocalDateTime closeTime;

  public ProposalDTO(String description) {
    this.description = description;
  }

  public ProposalDTO(String description, LocalDateTime closeTime) {
    this.description = description;
    this.closeTime = closeTime;
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
