/**
 * Created by t.garuglieri on 19/11/2014.
 */
public class Test {

    public static void main(String[] args)
    {
        new ThreadCounter(0,0).start(); // Thread figlio 2 non avrà figli
        new ThreadCounter(0,1).start();   // Thread figlio 1 creerà un figlio
    }
}
