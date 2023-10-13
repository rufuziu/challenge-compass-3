package br.demattos.iury.msproposals.dto.pool;

import br.demattos.iury.msproposals.enums.EResult;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDateTime;

public class PoolDTO {
  public PoolDTO() {
  }

  private Long id;
  private String description;
  private Long approve;
  private Long reject;
  private LocalDateTime initTime;
  private LocalDateTime closeTime;
  private EResult result;
  private Long sumResult;

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

  public Long getSumResult() {
    return sumResult;
  }

  public void setSumResult() {
    this.sumResult = this.approve - this.reject;
  }
}
