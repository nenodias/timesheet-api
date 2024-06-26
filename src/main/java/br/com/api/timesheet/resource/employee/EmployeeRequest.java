package br.com.api.timesheet.resource.employee;

import br.com.api.timesheet.dto.IdHolder;
import br.com.api.timesheet.enumeration.OfficeHoursEnum;
import br.com.api.timesheet.enumeration.StatusEnum;
import java.util.Optional;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeRequest {

  private Integer page;

  private Integer size;

  private Long id;

  @NotBlank(message = "error-employee-1")
  @Size(min = 5, max = 50, message = "error-employee-2")
  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @NotBlank(message = "error-employee-3")
  private String recordNumber;

  private IdHolder<Long> company;

  private String costCenter;

  private Double costHour;

  private OfficeHoursEnum officeHour;

  private StatusEnum status;

  private IdHolder<Long> position;

  public Optional<Integer> getPage() {
    return Optional.ofNullable(page);
  }

  public Optional<Integer> getSize() {
    return Optional.ofNullable(size);
  }

  public Optional<String> getName() {
    return Optional.ofNullable(name);
  }

  public Optional<String> getRecordNumber() {
    return Optional.ofNullable(recordNumber);
  }

  public Optional<Long> getCompanyId() {
    return Optional.ofNullable(company).map(IdHolder::getId);
  }

  public Optional<StatusEnum> getStatus() {
    return Optional.ofNullable(status);
  }

  public Optional<String> getCostCenter() {
    return Optional.ofNullable(costCenter);
  }

  public Optional<Double> getCostHour() {
    return Optional.ofNullable(costHour);
  }

  public Optional<OfficeHoursEnum> getOfficeHour() {
    return Optional.ofNullable(officeHour);
  }

  public Optional<Long> getPositionId() {
    return Optional.ofNullable(position).map(IdHolder::getId);
  }
}
