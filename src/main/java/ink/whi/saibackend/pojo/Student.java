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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "student")
public class Student implements Serializable {
    @NotNull
    String sname;
    @NotNull
    String id;
    @Email
    String email;
    @NotNull
    String qq;
    @NotNull
    boolean management;
    String introduce;
}
