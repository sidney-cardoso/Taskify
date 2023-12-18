package br.com.sidneycardoso.taskify.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sidneycardoso.taskify.core.exceptions.UserNotFoundException;
import br.com.sidneycardoso.taskify.core.model.User;
import br.com.sidneycardoso.taskify.core.repository.UserRepository;
import br.com.sidneycardoso.taskify.web.dto.UserForm;
import br.com.sidneycardoso.taskify.web.mappers.WebUserMapper;

@Service
public class WebUserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private WebUserMapper mapper;

    public List<User> listAll() {
        return repository.findAll();
    }

    public User create(UserForm form) {
        User model = mapper.toModel(form);
        return repository.save(model);
    }

    public User findUserById(Long id) {
        Optional<User> userFound = repository.findById(id);

        if (userFound.isPresent()) {
            return userFound.get();
        }

        String message = String.format("Usuário com id %d", id);
        throw new UserNotFoundException(message);
    }

    public User edit(UserForm form, Long id) {
        User userFound = findUserById(id);

        User model = mapper.toModel(form);
        model.setId(userFound.getId());

        return repository.save(model);
    }

    public void deleteUserById(Long id) {
        User userFound = findUserById(id);
        repository.delete(userFound);
    }
}
