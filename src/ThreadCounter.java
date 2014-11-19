import java.util.ArrayList;

/**
 * Created by t.garuglieri on 19/11/2014.
 */
public class ThreadCounter extends Thread {

    private static final int CHILD_COUNT=1;

    int childNumber, threadNumber;
    ArrayList<Thread> threadArray;

    public ThreadCounter(int tn, int cn) {
        this.childNumber = cn; //Numero figli da creare
        this.threadNumber = tn; // Numero thread corrente
        this.threadArray = new ArrayList<Thread>(); // Lista figli
    }

    @Override
    public void run() {
        createThreads(); //Creo N figli
        startStopThreads(); //Avvio e interrompo i figli

        for (int i = 1; i < 11; i++) {
            try {
                System.out.println("Thread " + threadNumber + ": " + i);
                sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }

        joinThreads();

        if (threadNumber == 2)
            System.out.println("Esecuzione Completata");

    }

    private void joinThreads() {
        for (Thread thread : threadArray)
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    private void createThreads() { // Ogni thread crea un thread che a sua volta andrà a creare 1 thread
        for (int i = 0; i < childNumber; i++)
            threadArray.add(new ThreadCounter(threadNumber + 1, CHILD_COUNT));
    }

    private void startStopThreads() {
        for (Thread thread : threadArray) {
            thread.start();
            thread.interrupt();
        }
    }
}
