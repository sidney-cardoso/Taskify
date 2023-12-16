package br.com.sidneycardoso.taskify.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.sidneycardoso.taskify.core.model.User;
import br.com.sidneycardoso.taskify.web.dto.UserForm;

@Mapper(componentModel = "spring")
public interface WebUserMapper {
    WebUserMapper INSTANCE = Mappers.getMapper(WebUserMapper.class);

    User toModel(UserForm form);

    UserForm toForm(User model);

}
