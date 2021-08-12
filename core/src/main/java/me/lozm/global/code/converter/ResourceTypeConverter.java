package me.lozm.global.code.converter;

import lombok.extern.slf4j.Slf4j;
import me.lozm.global.code.ResourceType;
import me.lozm.global.code.UsersType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Slf4j
@Converter(autoApply = true)
public class ResourceTypeConverter implements AttributeConverter<ResourceType, String> {

    private static final String ERROR_USE_YN_DB_VALUE = "DB로 부터 알수 없는 상태값을 받았습니다. => %s";

    @Override
    public String convertToDatabaseColumn(ResourceType attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.getCode();
    }

    @Override
    public ResourceType convertToEntityAttribute(String dbData) {
        try {
            return ResourceType.findCode(dbData);
        } catch (Exception e) {
            log.warn(String.format(ERROR_USE_YN_DB_VALUE, dbData));
            return null;
        }
    }

}
