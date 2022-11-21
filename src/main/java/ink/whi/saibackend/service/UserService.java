package ink.whi.saibackend.service;

import ink.whi.saibackend.pojo.UserInfo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserInfo login(String username, String password);

    boolean hasUser(String username);

}
