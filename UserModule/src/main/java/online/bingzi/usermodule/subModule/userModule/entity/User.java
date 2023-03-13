package online.bingzi.usermodule.subModule.userModule.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import online.bingzi.usermodule.common.entity.validated.Insert;
import online.bingzi.usermodule.common.entity.validated.Update;
import online.bingzi.usermodule.subModule.userModule.util.Encryptor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    @NotEmpty(message = "名称不能为空", groups = {Insert.class, Update.class})
    private String name;
    @Column(name = "password", nullable = false)
    @NotEmpty(message = "密码不能为空", groups = {Insert.class, Update.class})
    @Length(min = 6, max = 16, message = "密码长度必须在6-16位之间", groups = {Insert.class, Update.class})
    @Convert(converter = Encryptor.class)  // 在存入或者取出的时候进行加密或者解密，这里是加密。（解密已被禁用）
    private String password;
    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty(message = "邮箱不能为空", groups = {Insert.class, Update.class})
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式不正确", groups = {Insert.class, Update.class})
    private String email;
    @Column(name = "phone", nullable = false, unique = true)
    @NotEmpty(message = "手机号不能为空", groups = {Insert.class, Update.class})
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式不正确", groups = {Insert.class, Update.class})
    private String phone;

}
