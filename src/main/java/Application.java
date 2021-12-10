import Elements.Netcable;
import Elements.PC;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Программа топологии сетей
 */

public class Application {

    public static void main(String[] args) {
        Netcable netcable1 = new Netcable(21);
        Netcable netcable2 = new Netcable(22);
        Netcable netcable3 = new Netcable(23);
        Netcable netcable4 = new Netcable(24);

        PC pc1 = new PC(11);
        PC pc2 = new PC(12);
        PC pc3 = new PC(13);
        PC pc4 = new PC(14);


        netcable1.connect(pc1, pc4);
        netcable2.connect(pc1, pc2);
        netcable3.connect(pc2, pc3);
        netcable4.connect(pc3, pc4);
        pc1.buttonPower();
        pc2.buttonPower();
        pc3.WoL();
        pc4.WoL();


        recursion(pc1, new HashSet<>());

    }

    /**
     * Рекурсия для обхода всей топологии сети
     *
     * @param current ПК с которого начинается обход сети
     * @param visited Список ПК которые уже обошли
     */
    private static void recursion(PC current, Set<PC> visited) {
        System.out.println("Current PC: " + Optional.ofNullable(current)
                .map(PC::getUuid)
                .orElse(null));
        assert current != null;

        if (visited.contains(current)) {
            return;
        }
        visited.add(current);

        ArrayList<Netcable> netcables = current.getNetcables();

        for (Netcable netcable : netcables) {
            if (netcable == null) {
                break;
            }
            PC another = netcable.getAnother(current);
            System.out.println("Current:" + current.getUuid() + ", Another: " + another.getUuid());
            recursion(another, visited);
        }
    }

}
