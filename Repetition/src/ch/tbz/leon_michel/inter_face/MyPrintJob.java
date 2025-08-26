package ch.tbz.leon_michel.inter_face;

import java.awt.*;

public class MyPrintJob extends PrintJob {
    @Override
    public Graphics getGraphics() {
        return null;
    }

    @Override
    public Dimension getPageDimension() {
        return null;
    }

    @Override
    public int getPageResolution() {
        return 0;
    }

    @Override
    public boolean lastPageFirst() {
        return false;
    }

    @Override
    public void end() {

    }
}
