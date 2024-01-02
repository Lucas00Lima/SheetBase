package org.example;
import javafx.application.Platform;
public class LogTex {
    private static HelloController frontendController;
    public static void setFrontendController(HelloController controller) {
        LogTex.frontendController = controller;
    }
    public static void textInfo(String message) {
        System.out.println("[INFO] " + message);
        sendLogToFrontend("[INFO] " + message, false);
    }
    public static void textError(String message) {
        System.err.println("[ERRO] " + message);
        sendLogToFrontend("[ERRO] " + message, true);
    }
//    public static void textError(Exception e) {
//        e.printStackTrace();
//        String errorMessage = e.getClass().getSimpleName() + ": " + e.getMessage();
//        textError(errorMessage);
//    }
    private static void sendLogToFrontend(String message, boolean isError) {
        if (frontendController != null) {
            Platform.runLater(() -> frontendController.appendLog(message, isError));
        }
    }
}
