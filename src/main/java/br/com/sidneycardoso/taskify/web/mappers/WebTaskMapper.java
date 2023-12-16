package br.com.sidneycardoso.taskify.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.sidneycardoso.taskify.core.model.Task;
import br.com.sidneycardoso.taskify.web.dto.TaskForm;

@Mapper(componentModel = "spring")
public interface WebTaskMapper {
    WebTaskMapper INSTANCE = Mappers.getMapper(WebTaskMapper.class);

    Task toModel(TaskForm form);

    TaskForm toForm(Task model);
}
