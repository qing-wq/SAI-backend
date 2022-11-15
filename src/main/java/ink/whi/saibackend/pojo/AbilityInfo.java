package ink.whi.saibackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "ability")
//@Validated
@Component
public class AbilityInfo implements Serializable {
    @NotNull
    Integer sid;
    @NotNull
    Integer level;
    @NotNull
    String direction;
    @NotNull
    List<String> language;
    String other;
}
