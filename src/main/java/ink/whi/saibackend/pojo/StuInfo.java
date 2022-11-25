package ink.whi.saibackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "stuinfo")
public class StuInfo implements Serializable {

    @NotNull
    String name;
    @NotNull
    String id;
    @Email
    String email;
    @NotNull
    String qq;
    @NotNull
    String introduce;
    @NotNull
    boolean management;
    AbilityInfo info;
}
