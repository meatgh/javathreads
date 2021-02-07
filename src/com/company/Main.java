package com.company;

import static com.company.ThreadColor.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(ANSI_PURPLE + "Hello from the main thread.");

        Thread anotherThread = new AnotherThread();
        anotherThread.setName("===Another Thread===");
        anotherThread.start();

        new Thread(){
            public void run(){
                System.out.println(ANSI_GREEN + "Hello from the anonyous thread");
            }
        }.start();

        Thread myRunnableThread = new Thread(new MyRunnable()){
            @Override
            public void run() {
                System.out.println(ANSI_GREEN + "Hello from the anonymous class's implementation of the run");
                try{
                    anotherThread.join(2000);
                    System.out.println(ANSI_RED + "anotherThread terminated or timedout,  so I'm running again");
                }catch (InterruptedException e){
                    System.out.println(ANSI_RED + "I couldn't wait after all, I was interrupted");
                }
            }
        };
        myRunnableThread.start();
//        anotherThread.interrupt();
        System.out.println(ANSI_PURPLE + "Hello again from the main thread");

    }
}
