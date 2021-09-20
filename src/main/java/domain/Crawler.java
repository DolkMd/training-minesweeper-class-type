package domain;

import java.util.*;

public final class Crawler {
    public final static class Counter {
        public int x, y, countAround;
        public PanelStatus status;

        public Counter() {}
        public Counter(final int x, final int y, final int count, final PanelStatus status) {
            this.x = x;
            this.y = y;
            this.countAround = count;
            this.status = status;
        }

        public Counter(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        public Counter(final int count, final PanelStatus status) {
            this.countAround = count;
            this.status = status;
        }
    }

    public List<Counter> eatPanels(final int x, final int y) {
        final List<Counter> counters = this.lookPanels(x, y);
        counters.forEach(c ->  Board.open(x, y));
        return counters;
    }

    private Map<String, List<Counter>> memo = new HashMap<String, List<Counter>>();
    private final String KEY_FORMAT = "%d/%d";
    private List<Counter> lookPanels(final int x, final int y) {
        final String key = String.format(this.KEY_FORMAT, x, y);

        if(memo.containsKey(key)) return memo.get(key);
        if (Board.getValue(x, y) == PanelStatus.Bomb || !Board.isInside(x, y)) return Collections.singletonList(new Counter(x, y, 0, PanelStatus.Bomb));

        final Counter counter = new Counter();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++ ) {
                if (i == 0 && j == 0) continue;
                if (Board.isInside(x + i, y + j)) {
                    if (Board.getValue(x+i, y+j).equals(PanelStatus.Bomb)) counter.countAround++;
                }
            }
        }

        final List<Counter> counters = new ArrayList<Counter>(Collections.singletonList(counter));
        if (counter.countAround == 0) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++ ) {
                    if (i == 0 && j == 0) continue;
                    if (Board.isInside(x + i, y + j)) {
                        counters.addAll(lookPanels(x+i, y+j));
                    }
                }
            }
        }

        memo.put(key, counters);
        return counters;
    }
}