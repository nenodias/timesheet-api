package br.com.api.timesheet.converter;

import br.com.api.timesheet.enumeration.StatusEnum;
import com.google.common.collect.ImmutableMap;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Converter(autoApply = true)
public class StatusEnumConverter implements AttributeConverter<StatusEnum, String> {

  private static final Map<String, StatusEnum> typeConversions =
          ImmutableMap.copyOf(Stream
                  .of(StatusEnum.values())
                  .collect(toMap(StatusEnum::getCode, identity())));

  @Override
  public String convertToDatabaseColumn(StatusEnum attribute) {
    return attribute != null ? attribute.getCode() : null;
  }

  @Override
  public StatusEnum convertToEntityAttribute(String dbData) {
    return dbData != null ? typeConversions.get(dbData) : null;
  }
}
