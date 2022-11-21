package ink.whi.saibackend.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserInfo {
    @NotNull
    String username;
    @NotNull
    String password;
}
