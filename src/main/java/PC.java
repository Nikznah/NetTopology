import lombok.Data;

import java.util.UUID;

/**
 *  Компуктер
 */

@Data
public class PC {
    private UUID uuid;
    private String name;
    private boolean power;
    private Netcard netCard;
}
