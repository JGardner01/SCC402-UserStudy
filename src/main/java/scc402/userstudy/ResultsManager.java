package scc402.userstudy;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultsManager {
    private static long startTime;
    private static HashMap<String, Long> completionTime = new HashMap<>();
    private static HashMap<String, Integer> clickCount = new HashMap<>();
    private static int clickCounter = 0;
    private static ArrayList<String[]> clickLogs = new ArrayList<>();
    private static long lastClickTime;
    public static String[] pastTasks = {"", "", "", ""};

    public static void startRecording(){
        startTime = System.currentTimeMillis();
        lastClickTime = System.currentTimeMillis();
        clickCounter = 0;
        System.out.println("Timer and click count started:");
    }

    public static void endRecording(String taskName){
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        completionTime.put(taskName, duration);
        clickCount.put(taskName, clickCounter);
        System.out.println(clickCount.get(taskName));
        System.out.println("Task " + taskName + " completed in " + duration + " ms. Completed in " + clickCounter + " clicks.");
        startTime = 0;
        clickCounter = 0;
    }

    public static void recordClick(boolean clickError){
        clickCounter++;

        long betweenClickDuration = System.currentTimeMillis() - lastClickTime;
        lastClickTime = System.currentTimeMillis();

        //place data in and say if error occurred or not
        clickLogs.add(new String[]{TaskManager.getTaskName(), String.valueOf(clickCounter), String.valueOf(betweenClickDuration), String.valueOf(clickError)});
        System.out.println("Click " + clickCounter + ": Time since last click = " + betweenClickDuration + "ms, Error = " + clickError);
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
            e.printStackTrace();
            //print results to console
        }

        System.out.println("Click Results...");

        try (FileWriter fileWriter = new FileWriter("click_results.csv")){
            fileWriter.write("Task Name,Click Number,Time Since Last Click (ms),Error\n");
            for (String [] log : clickLogs){
                fileWriter.write(String.join(",", log) + "\n");
            }
            System.out.println("Click results exported successfully to click_results.csv");
        } catch (Exception e) {
            System.out.println("Failed to export clicks");
            e.printStackTrace();
        }
    }
}