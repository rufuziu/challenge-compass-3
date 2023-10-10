package br.demattos.iury.msemployee.service;

import br.demattos.iury.msemployee.dto.EmployeeDTO;
import br.demattos.iury.msemployee.entity.Employee;
import br.demattos.iury.msemployee.exception.employee_exce.EmployeeCpfAlreadyInUseExcepetion;
import br.demattos.iury.msemployee.exception.employee_exce.EmployeeCpfNotFound;
import br.demattos.iury.msemployee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
  private final EmployeeRepository repository;
  private final ModelMapper mapper;
  private String messageExce;

  public EmployeeService(EmployeeRepository repository,
                         ModelMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public EmployeeDTO createEmployee(EmployeeDTO employeeDto) {
    if (repository.existsByCpf(employeeDto.getCpf())) {
      messageExce = new StringBuilder()
              .append("CPF ")
              .append(employeeDto.getCpf())
              .append(" is already in use.")
              .toString();
      throw new EmployeeCpfAlreadyInUseExcepetion(messageExce);
    } else return
            mapper.map(
                    repository.save(
                            mapper.map(
                                    employeeDto,
                                    Employee.class
                            )
                    ),
                    EmployeeDTO.class
            );
  }
  public boolean employeeCanVote(String cpf){
    if(repository.existsByCpf(cpf)) return true;
    else throw new EmployeeCpfNotFound("Not found.");
  }
}
