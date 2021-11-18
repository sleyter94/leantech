package com.lean.tech.mapper;

import com.lean.tech.dto.PersonDto;
import com.lean.tech.model.Person;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {

    PersonDto toDto(Person person);
    Person toModel(PersonDto person);
}
