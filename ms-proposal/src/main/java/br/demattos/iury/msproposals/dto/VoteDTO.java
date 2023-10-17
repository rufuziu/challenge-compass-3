package br.demattos.iury.msproposals.dto;

import jakarta.validation.constraints.*;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.NumberFormat;

public class VoteDTO {
  public VoteDTO() { }
  private Long id;
  @NotNull
  @Min(value = 0L,message = "ProposalId must exists.")
  private Long proposalId;
  @CPF
  private String employeeCpf;
  @NotNull(message = "Must be a boolean value(true/false).")
  private Boolean vote;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getProposalId() {
    return proposalId;
  }

  public void setProposalId(Long proposalId) {
    this.proposalId = proposalId;
  }

  public String getEmployeeCpf() {
    return employeeCpf;
  }

  public void setEmployeeCpf(String employeeCpf) {
    this.employeeCpf = employeeCpf;
  }

  public Boolean getVote() {
    return vote;
  }

  public void setVote(Boolean vote) {
    this.vote = vote;
  }
}
