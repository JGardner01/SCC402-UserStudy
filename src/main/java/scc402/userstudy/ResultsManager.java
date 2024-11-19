package scc402.userstudy;

import java.io.FileWriter;
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
        System.out.println("Task " + taskName + " completed in " + duration + " ms. Completed in " + clickCounter + " clicks.");
        startTime = 0;
        clickCounter = 0;
    }

    //track specific buttons, all buttons or mouse event listener
    public static void incrementClickCount() {
        clickCounter++;
    }


    //
    //NEEDS TESTING AT THE END
    //export results to CSV be implemented later
    public static void exportResults(){
        System.out.println("Exporting Results...");

        try (FileWriter fileWriter = new FileWriter("results.csv")) {
            //write  the header
            fileWriter.write("Task Name,Completion Time (ms),Click Count\n");

            //write the data
            for (String taskName : completionTime.keySet()) {
                long duration = completionTime.get(taskName);
                int clicks = clickCount.getOrDefault(taskName, 0);
                fileWriter.write(taskName + "," + duration + "," + clicks + "\n");
            }

            System.out.println("Results exported successfully to results.csv.");

        } catch (Exception e) {
            System.err.println("Failed to export results.");
            //print results to console
        }
    }
}