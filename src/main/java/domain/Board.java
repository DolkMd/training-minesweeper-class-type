package domain;

public final class Board {
    private static PanelStatus[][] map;
    static {
        Board.map = new PanelStatus[Config.TROUT][Config.TROUT];
        for (int i = 0; i < Config.TROUT; i++) {
            for (int j = 0; j < Config.TROUT; j++) {
                Board.map[i][j] = PanelStatus.None;
            }
        }
    }

    public static boolean isInside (final int x, final int y) {
        return x >= 0 && x < Board.map.length && y >= 0 && y < Board.map[x].length;
    }

    public static boolean setBom(final int x, final int y) {
        if (!Board.isInside(x, y)) return false;
        Board.map[x][y] = PanelStatus.Bomb;
        return true;
    }

    public static PanelStatus getValue(final int x, final int y) {
        if (!Board.isInside(x, y)) throw new RuntimeException("Outside domain.Board x:" + x + " y:" + y);
        return map[x][y];
    }

    public static PanelStatus open(final int x, final int y) {
        if (!Board.isInside(x, y)) throw new RuntimeException("Outside domain.Board x:" + x + " y:" + y);
        final PanelStatus result = Board.map[x][y];
        Board.map[x][y] = PanelStatus.Opened;
        return result;
    }
}
