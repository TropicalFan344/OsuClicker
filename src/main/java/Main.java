import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.awt.event.KeyEvent;
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
        float spb = (15f/bpm)*1000;
        System.out.println(spb);
        new Thread(() -> {
            long lastTime = 0;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(lastTime);
            while (true) {
                System.out.print("");
                if (isPressed) {
                    if (System.currentTimeMillis() - lastTime > spb-1){
                        lastTime = System.currentTimeMillis();
                        if (pressedA[0] = !pressedA[0]) {
                            System.out.println("pressed A!");
                            robot.keyPress(KeyEvent.VK_A);
                            try {
                                Thread.sleep(5);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            robot.keyRelease(KeyEvent.VK_A);

                        }else {
                            System.out.println("pressed X!");
                            robot.keyPress(KeyEvent.VK_X);
                            try {
                                Thread.sleep(5);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            robot.keyRelease(KeyEvent.VK_X);
                        }
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
        if (NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()).equalsIgnoreCase("d")) {
            isPressed = true;
            System.out.println("Pressed!" + isPressed);
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        if (NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()).equalsIgnoreCase("d")) {
            isPressed = false;
            System.out.println("Realeased!" + isPressed);
        }

    }
}
