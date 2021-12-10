package Elements;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

/**
 *
 */
@Data
public class Netcable {
    private int id;

    @Setter(value = AccessLevel.NONE)
    private PC left;

    @Setter(value = AccessLevel.NONE)
    private PC right;

    public Netcable(int id) {
        this.id = id;
    }

    public void connect(PC pc) {
        if (this.left == null) {
            this.left = pc;
        } else if (this.right == null) {
            this.right = pc;
        } else {
            throw new IllegalStateException(
                    "Провод уже подключен с двух сторон!" +
                            "\n PC: left" + this.left +
                            ";\n right = " + this.right);
        }
        pc.plugIn(this);
    }

    public void connect(PC left, PC right) {
        connect(left);
        connect(right);
    }

    public PC getAnother(PC pc) {
        if (this.left.equals(pc)) {
            return this.right;
        } else if (this.right.equals(pc)) {
            return this.left;
        } else {
            throw new IllegalArgumentException("Данный кабель не подключен к PC: id = " + pc.getUuid());
        }
    }

    @Override
    public String toString() {
        return "Netcable{" +
                "id=" + id +
                ", connectleft=" + Optional.ofNullable(left)
                .map(PC::getUuid)
                .orElse(-1) +
                ", connectright=" + Optional.ofNullable(right)
                .map(PC::getUuid)
                .orElse(-1) +
                '}';
    }

}
