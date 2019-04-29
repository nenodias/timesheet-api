package br.com.api.timesheet.resource.employee;

import java.util.Optional;

public class EmployeeRequest {

    private Integer page;
    private Integer size;
    private String name;

    public Optional<Integer> getPage() {
        return Optional.ofNullable(page);
    }

    public Optional<Integer> getSize() {
        return Optional.ofNullable(size);
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public static final class Builder {
        private Integer page;
        private Integer size;
        private String name;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder withPage(Integer page) {
            this.page = page;
            return this;
        }

        public Builder withSize(Integer size) {
            this.size = size;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public EmployeeRequest build() {
            EmployeeRequest positionRequest = new EmployeeRequest();
            positionRequest.page = this.page;
            positionRequest.size = this.size;
            positionRequest.name = this.name;
            return positionRequest;
        }
    }
}
