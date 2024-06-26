package br.com.api.timesheet.resource.employee;

import br.com.api.timesheet.dto.IdHolder;
import br.com.api.timesheet.entity.Employee;
import br.com.api.timesheet.enumeration.StatusEnum;
import br.com.api.timesheet.service.EmployeeService;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class EmployeeResource {

  private EmployeeService employeeService;

  public EmployeeResource(@Autowired EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  /**
   * Find all employees.
   * @param page - page
   * @param size - size
   * @param name - name
   * @param recordNumber - record number
   * @param companyId - company id
   * @param status - status
   * @return
   */
  @GetMapping("/employees")
  public ResponseEntity<Page<Employee>> findAll(
          @RequestParam(value = "page", required = false) final Integer page,
          @RequestParam(value = "size", required = false) final Integer size,
          @RequestParam(value = "name", required = false) final String name,
          @RequestParam(value = "recordNumber", required = false) final String recordNumber,
          @RequestParam(value = "companyId", required = false) final Long companyId,
          @RequestParam(value = "status", required = false) final String status) {

    final EmployeeRequest employeeRequest = EmployeeRequest.builder()
            .page(page)
            .size(size)
            .name(name)
            .recordNumber(recordNumber)
            .company(new IdHolder<>(companyId))
            .status(StringUtils.isNotBlank(status) ? StatusEnum.valueOf(status) : null)
            .build();

    Page<Employee> employees = employeeService.findAll(employeeRequest);
    return ResponseEntity.ok(employees);
  }

  @GetMapping("/employees/{id}")
  public ResponseEntity<Employee> findById(@PathVariable Long id) {
    Employee employee = employeeService.findById(id);
    return ResponseEntity.ok(employee);
  }

  @PostMapping("/employees")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Employee> create(@Valid @RequestBody EmployeeRequest employeeRequest) {
    return new ResponseEntity<>(employeeService.save(employeeRequest), HttpStatus.CREATED);
  }

  @PutMapping("/employees/{id}")
  public ResponseEntity<Employee> update(@PathVariable Long id,
        @Valid @RequestBody EmployeeRequest employeeRequest) {
    employeeRequest.setId(id);
    return new ResponseEntity<>(employeeService.save(employeeRequest), HttpStatus.OK);
  }

  @DeleteMapping("/employees/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    employeeService.delete(id);
  }

}
