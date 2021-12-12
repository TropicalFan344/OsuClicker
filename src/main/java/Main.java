import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.util.Scanner;

public class Main implements NativeKeyListener{
    public static boolean isPressed = false;

    public static void main(String[] args) throws AWTException {
        Scanner scanner = new Scanner(System.in);
        Robot robot = new Robot();
        final boolean[] pressedA = {false};
        int bpm;
        System.out.print("Please enter the BPM\n>> ");
        bpm = scanner.nextInt();
        float spb = (60f/bpm)*1000;
        new Thread(() -> {
            while (true) {
                System.out.println("Thread Started!");
                if (isPressed) {
                    System.out.println("Pressing");
                    if (pressedA[0] = !pressedA[0]) {
                        robot.keyPress(NativeKeyEvent.VC_A);
                        robot.keyRelease(NativeKeyEvent.VC_A);
                    }else {
                        robot.keyPress(NativeKeyEvent.VC_X);
                        robot.keyRelease(NativeKeyEvent.VC_X);
                    }
                }
            }

        }).start();
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new Main());
        } catch (NativeHookException e) {
            e.printStackTrace();
            return;
        }


    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        System.out.println();
        if (NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()).equalsIgnoreCase("d")) {
            System.out.println("Pressed!");
            isPressed = true;
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        if (NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()).equalsIgnoreCase("d")) {
            System.out.println("Realeased!");
//            isPressed = false;
        }

    }
}
