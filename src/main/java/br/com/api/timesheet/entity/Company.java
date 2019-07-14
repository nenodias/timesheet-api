package br.com.api.timesheet.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = Company.TABLE_NAME)
public class Company implements Serializable {

    private static final long serialVersionUID = 52435565579515988L;

    static final String TABLE_NAME = "companies";
    private static final String SEQUENCE_NAME = "seq_companies";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    @CNPJ(message = "error-company-4")
    @NotBlank(message = "error-company-1")
    @Size(max = 18)
    @Column(name = "document", unique = true, nullable = false, length = 18)
    private String document;

    @NotBlank(message = "error-company-2")
    @Size(min = 3, max = 50, message = "error-company-3")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    public void setName(String name) {
        this.name = name.toUpperCase();
    }
}
