package scc402.userstudy;

import java.util.HashMap;

public class ResultsManager {
    private static long startTime;
    private static HashMap<String, Long> completionTime = new HashMap<>();
    private static HashMap<String, Integer> clickCount = new HashMap<>();
    private static int clickCounter = 0;

    public static void startRecording(){
        startTime = System.currentTimeMillis();
        clickCounter = 0;
        System.out.println("Timer and click count started:");
    }

    public static void endRecording(String taskName){
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        completionTime.put(taskName, duration);
        clickCount.put(taskName, clickCounter);
        startTime = 0;
        clickCounter = 0;
        System.out.println("Task " + taskName + " completed in " + duration + " ms.");
    }

    //track specific buttons, all buttons or mouse event listener
    public static void incrementClickCount() {
        clickCounter++;
    }

    //export results to CSV be implemented later
    public static void exportResults(){
        System.out.println("Exporting Results...");

    }
}