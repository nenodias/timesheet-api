package br.com.api.timesheet.resource.timesheetRegister;

import br.com.api.timesheet.enumeration.TimesheetTypeEnum;
import lombok.Data;

import java.util.Optional;

@Data
public class TimesheetRequest {

    private Long id;
    private TimesheetTypeEnum type;
    private String timeIn;
    private String lunchStart;
    private String lunchEnd;
    private String timeOut;

    public Optional<Long> getId() {
        return Optional.ofNullable(id);
    }

}