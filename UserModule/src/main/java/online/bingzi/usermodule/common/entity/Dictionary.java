package online.bingzi.usermodule.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import online.bingzi.usermodule.common.entity.validated.Insert;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    @NotEmpty(message = "名称不能为空", groups = {Insert.class})
    private String name;
    @Column(name = "code", nullable = false, unique = true)
    private String code;
    @Column(name = "status", nullable = false)
    private Boolean status = false;
    @Column(name = "parent", nullable = false)
    private Boolean parent = false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Dictionary parentId;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "children_id")
    private List<Dictionary> children;
}
