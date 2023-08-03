package ICG.FormCreator.DATA;
import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity(name = "ICG.FormCreator.DATA.Transaction")
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "account", nullable = false)
    private String account;
    @Column(name = "product", nullable = false)
    private Integer product;
    @Column(name = "currency", nullable = false)
    private Integer currency;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "status", nullable = false)
    private Integer status;
    @Column(name = "reason", nullable = false)
    private String reason;
}
