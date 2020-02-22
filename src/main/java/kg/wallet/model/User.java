package kg.wallet.model;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@XmlRootElement(name = "user")
public class User {
    private int id;
    private String name;
    private String password;
    private Date createdDate;
}
