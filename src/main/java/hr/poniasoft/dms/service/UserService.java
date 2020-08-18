package hr.poniasoft.dms.service;

import hr.poniasoft.dms.domain.User;
import hr.poniasoft.dms.dto.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
}
