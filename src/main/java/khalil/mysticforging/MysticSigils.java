package khalil.mysticforging;

public class MysticSigils {
    /*
     * Pattern notation:
     * X is class block
     * 0 is smithing table
     */
    // Stage 1 patterns have 5 total blocks by design1
    public static final String[] BULLWARK_1 = {
            "   ",
            "X0X",
            "XXX"
    };

    public static final String[] PATHFINDER_1 = {
            "XXX",
            " 0X",
            "X  "
    };

    // Stage 2 patterns have 14 total blocks by design
    public static final String[] BULLWARK_2 = {
            "X   X",
            "X X X",
            "X 0 X",
            "XXXXX",
            "X   X"
    };

    public static final String[] PATHFINDER_2 = {
            " XXXX",
            "   XX",
            "X 0 X",
            "XX  X",
            "XXX  "
    };
}
