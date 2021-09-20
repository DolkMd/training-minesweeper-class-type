package usecase;

import domain.Crawler;

import java.util.List;

public final class UseCase {
    private final Crawler crawler = new Crawler();

    public List<Crawler.Counter> getReversiblePoints(final int x, final int y) {
        return this.crawler.eatPanels(x, y);
    }
}
