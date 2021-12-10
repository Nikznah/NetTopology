package Elements;

import lombok.Data;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Компуктер
 */

@Data
public class PC {
    private int uuid;
    private ArrayList<Netcable> netcables;
    private boolean power;

    public PC(int uuid) {
        this.uuid = uuid;
        this.netcables = new ArrayList<>();
        this.power = false;
    }

    public void plugIn(Netcable netcable) {
        this.netcables.add(netcable);
    }

    @Override
    public String toString() {
        return "PC{" +
                "uuid=" + uuid +
                ", netcables=" + netcables.toString() +
                ", power=" + power +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PC)) return false;
        PC pc = (PC) o;
        return uuid == pc.uuid && power == pc.power && Objects.equals(netcables, pc.netcables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid,
                netcables.stream()
                        .map(Netcable::getId)
                        .collect(Collectors.toUnmodifiableList())
                        .hashCode(), power);
    }
}

