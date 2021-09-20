package infrastructure;

import processing.core.PApplet;
import usecase.UseCase;

public class Processing extends PApplet {
    private final UseCase  useCase = new UseCase();
    private final static int TROUT = 20;
    private final static int HW = 800;

    @Override
    public void settings() {
        size(800, 800);
    }

    @Override
    public void setup() {
        background(255);
        final int ratio = HW / TROUT;
        for (int i = 0; i < TROUT; i ++ ) {
            line(0, i * (ratio), HW, i * (ratio));
            line(i * (ratio), 0, i * (ratio), HW);
        }
    }

    @Override
    public void draw() {
        this.useCase.getReversiblePoints(1,1);

    }
}
