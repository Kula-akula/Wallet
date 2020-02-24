package kg.wallet.model;


import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@XmlRootElement (name="wallet")
public class Wallet {
    private int id;
    private String name;
    private User user;
    private int userId;
    private boolean isActive;
    private Date createdDate;

}
