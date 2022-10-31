import javax.sound.midi.SysexMessage;
import java.util.concurrent.CyclicBarrier;

public class Main {

    static final private int NUM_THREADS = 10;

    public static void main(String[] args) {
        CountBarreira[] tds = new CountBarreira[NUM_THREADS];
        CyclicBarrier barreira2 = new CyclicBarrier(NUM_THREADS, new Runnable() {
            @Override
            public void run() {
                System.out.println("Todos os threads acabaram de contar");
            }
        });

        CyclicBarrier barreira1 = new CyclicBarrier(NUM_THREADS/2, new Runnable() {
            @Override
            public void run() {
                    System.out.println("as 5 threads chegaram a 10^6, vou iniciar as outras");
                    for(int i = 5; i != NUM_THREADS; i++){
                        System.out.println("Nova Thread criada: " + i);
                        tds[i] = new CountBarreira(barreira2);
                        tds[i].start();
                    }
            }
        });

        for (int i = 0; i!= NUM_THREADS/2; i++){
            System.out.println("Nova Thread criada: " + i);
            tds[i] = new CountBarreira(barreira1, barreira2);
            tds[i].start();

        }

    }
}