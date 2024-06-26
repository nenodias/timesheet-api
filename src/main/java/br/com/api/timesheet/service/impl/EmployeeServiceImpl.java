package br.com.api.timesheet.service.impl;

import static br.com.api.timesheet.utils.Constants.DEFAULT_PAGE;
import static br.com.api.timesheet.utils.Constants.DEFAULT_SIZE;

import br.com.api.timesheet.entity.Employee;
import br.com.api.timesheet.exception.BusinessException;
import br.com.api.timesheet.repository.EmployeeRepository;
import br.com.api.timesheet.repository.specification.EmployeeRepositorySpecification;
import br.com.api.timesheet.resource.employee.EmployeeRequest;
import br.com.api.timesheet.service.CompanyService;
import br.com.api.timesheet.service.EmployeeService;
import br.com.api.timesheet.service.PositionService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private CompanyService companyService;

  @Autowired
  private PositionService positionService;

  /**
   * Find all employees.
   *
   * @param request - request
   * @return
   */
  public Page<Employee> findAll(EmployeeRequest request) {
    final Pageable pageable = PageRequest.of(
            request.getPage().orElse(DEFAULT_PAGE), request.getSize().orElse(DEFAULT_SIZE));
    return employeeRepository.findAll(
            EmployeeRepositorySpecification.criteriaSpecification(request), pageable);
  }

  /**
   * Find employee by id.
   *
   * @param id - id
   * @return
   */
  public Employee findById(Long id) {
    Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new BusinessException("error-employee-9", HttpStatus.BAD_REQUEST));
    employee.setOfficeHourDescription(employee.getOfficeHour().getDescription());
    return employee;
  }

  /**
   * Save employee.
   *
   * @param employeeRequest - request
   * @return
   */
  public Employee save(EmployeeRequest employeeRequest) {
    Employee employee = Employee.builder()
            .id(employeeRequest.getId())
            .company(companyService.findById(employeeRequest.getCompanyId()
                    .orElse(null))).costCenter(employeeRequest.getCostCenter()
                    .orElse(null)).costHour(employeeRequest.getCostHour()
                    .orElse(null)).name(employeeRequest.getName()
                    .orElse(null)).officeHour(employeeRequest.getOfficeHour()
                    .orElse(null)).recordNumber(employeeRequest.getRecordNumber()
                    .orElse(null))
            .position(positionService.findById(employeeRequest.getPositionId()
                    .orElse(null))).status(employeeRequest.getStatus()
                    .orElse(null)).build();
    verifyIfEmployeeExist(employee);
    return employeeRepository.save(employee);
  }

  public void delete(Long id) {
    employeeRepository.delete(findById(id));
  }

  private void verifyIfEmployeeExist(final Employee employee) {
    Optional<Employee> employeeDB = employeeRepository
            .findByRecordNumber(employee.getRecordNumber());
    if (employeeDB.isPresent() && !employeeDB.get().getId().equals(employee.getId())) {
      throw new BusinessException("error-employee-8", HttpStatus.BAD_REQUEST);
    }
  }
}
