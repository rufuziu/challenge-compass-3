package br.demattos.iury.msvoting.controller;

import br.demattos.iury.msvoting.proxies.EmployeeResourceProxy;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@Validated
public class VotingController {
  private EmployeeResourceProxy employeeResourceProxy;

  public VotingController(EmployeeResourceProxy employeeResourceProxy) {
    this.employeeResourceProxy = employeeResourceProxy;
  }

  @GetMapping("v1/voting/employees/{cpf}")
  public ResponseEntity<Boolean> canVote(@PathVariable(name = "cpf")
                                         @NotBlank(message = "CPF can't be blank")
                                         @CPF String cpf) {
    return employeeResourceProxy.canVote(cpf);
  }

}
