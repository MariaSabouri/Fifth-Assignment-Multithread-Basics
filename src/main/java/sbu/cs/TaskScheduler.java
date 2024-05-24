package sbu.cs;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskScheduler
{
    static ArrayList<Task> tasks=new ArrayList<>();
    public static class Task implements Runnable
    {
        /*
            ------------------------- You don't need to modify this part of the code -------------------------
         */
        String taskName;
        int processingTime;

        public Task(String taskName, int processingTime) {
            this.taskName = taskName;
            this.processingTime = processingTime;
        }
        /*
            ------------------------- You don't need to modify this part of the code -------------------------
         */

        @Override
        public void run() {
            /*
            TODO
                Simulate utilizing CPU by sleeping the thread for the specified processingTime
             */
            try {
                Thread.sleep(processingTime);
            } catch (InterruptedException e) {
            }



        }
    }

    public static ArrayList<String> doTasks(ArrayList<Task> tasks)
    {
        ArrayList<String> finishedTasks = new ArrayList<>();


        /*
        TODO
            Create a thread for each given task, And then start them based on which task has the highest priority
            (highest priority belongs to the tasks that take more time to be completed).
            You have to wait for each task to get done and then start the next task.
            Don't forget to add each task's name to the finishedTasks after it's completely finished.
         */
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {

                return Integer.valueOf(o2.processingTime).compareTo(o1.processingTime);
            }
        });
        for (Task task:tasks){
            finishedTasks.add(task.taskName);
        }





        return finishedTasks;
    }

    public static void main(String[] args) {
        // Test your code here
        Task task1=new Task("A",1000);
        Task task2=new Task("B",3000);
        Task task3=new Task("D",5000);
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        for (Task task:tasks){
            task.run();
            System.out.println(task.taskName);
        }
//        System.out.println(doTasks(tasks));

    }
}
