//package com.bookstore_api.bookstore.converter;
//
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//import org.joda.time.DateTime;
//
//import java.sql.Timestamp;
//
//@Converter(autoApply = true)
//public class JodaDateTimeConverter implements AttributeConverter<DateTime, Timestamp> {
//
//    @Override
//    public Timestamp convertToDatabaseColumn(DateTime attribute) {
//        return attribute == null ? null : new Timestamp(attribute.getMillis());
//    }
//
//    @Override
//    public DateTime convertToEntityAttribute(Timestamp dbData) {
//        return dbData == null ? null : new DateTime(dbData.getTime());
//    }
//}
//
