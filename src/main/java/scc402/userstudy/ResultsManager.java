package scc402.userstudy;

import java.util.HashMap;

public class ResultsManager {
    private static boolean trackingClickCount;

    private static long startTime;
    private static HashMap<String, Long> completionTime = new HashMap<>();
    private static HashMap<String, Integer> clickCount = new HashMap<>();

    public static void startRecording(String taskName){
        startTime = System.currentTimeMillis();
        clickCount.put(taskName, 0);
        System.out.println("Timer and click count started:");
    }

    public static void endRecording(String taskName){
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        completionTime.put(taskName, duration);
        startTime = 0;
        System.out.println("Task " + taskName + " completed in " + duration + " ms.");
    }

    //track specific buttons, all buttons or mouse event listener
    public static void incrementClickCount(String taskName) {
        if (trackingClickCount) {
            clickCount.put(taskName, clickCount.getOrDefault(taskName, 0) + 1);
        }
    }

    public static void trackClickCount(boolean enabled){
        trackClickCount(enabled);
    }

    //export results to CSV be implemented later
    public static void exportResults(){
        System.out.println("Exporting Results...");

    }
}