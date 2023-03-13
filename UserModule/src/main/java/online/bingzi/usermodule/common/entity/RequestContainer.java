package online.bingzi.usermodule.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import online.bingzi.usermodule.common.entity.validated.Select;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RequestContainer<T extends Collection> {
    @NotEmpty(message = "数据不能为空", groups = {Select.class})
    private T data;
}
