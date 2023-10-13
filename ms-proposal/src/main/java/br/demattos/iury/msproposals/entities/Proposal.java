package br.demattos.iury.msproposals.entities;

import br.demattos.iury.msproposals.enums.EResult;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@MappedSuperclass
public class Proposal {
  public Proposal() {
  }
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String description;
  private Long approve = 0L;
  private Long reject = 0L;
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime initTime;
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime closeTime;
  @Enumerated(EnumType.STRING)
  private EResult result = EResult.POLLING;

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

  public Long getApprove() {
    return approve;
  }

  public void setApprove(Long approve) {
    this.approve = approve;
  }

  public Long getReject() {
    return reject;
  }

  public void setReject(Long reject) {
    this.reject = reject;
  }

  public LocalDateTime getInitTime() {
    return initTime;
  }

  public void setInitTime(LocalDateTime initTime) {
    this.initTime = initTime;
  }

  public LocalDateTime getCloseTime() {
    return closeTime;
  }

  public void setCloseTime(LocalDateTime closeTime) {
    this.closeTime = closeTime;
  }

  public EResult getResult() {
    return result;
  }

  public void setResult(EResult result) {
    this.result = result;
  }
}
