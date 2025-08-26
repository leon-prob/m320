package ch.tbz.leon_michel.inter_face;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        PrinterQueue printerQueue = new PrinterQueue();

        printerQueue.addJob();
        printerQueue.addJob();
        printerQueue.addJob();
        printerQueue.addJob();
        System.out.println(printerQueue.nextJob());
        System.out.println(printerQueue.nextJob());
        System.out.println(printerQueue.nextJob());
        System.out.println(printerQueue.nextJob());
        System.out.println(printerQueue.nextJob());

    }
}
