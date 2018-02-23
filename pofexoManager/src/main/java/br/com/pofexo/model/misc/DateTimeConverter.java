package br.com.pofexo.model.misc;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;

public class DateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
		return (attribute == null ? null : Timestamp.valueOf(attribute));
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
		return (dbData == null ? null : dbData.toLocalDateTime());
	}

}
