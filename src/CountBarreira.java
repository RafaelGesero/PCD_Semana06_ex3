import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CountBarreira extends Thread {

    private CyclicBarrier barreira1;
    private CyclicBarrier barreira2;
    private int count = 1;
    static private int NUMBER_OF_COUNT = 1000000;

    public CountBarreira(CyclicBarrier barreira1, CyclicBarrier barreira2){
        this.barreira1 = barreira1;
        this.barreira2 = barreira2;
    }

    public CountBarreira(CyclicBarrier barreira2){
        this.barreira2 = barreira2;
        barreira1 = null;
    }


    public void run(){
        for(int i = 1; i <= NUMBER_OF_COUNT; i++){
            count++;
            if(count == 1000 && barreira1 != null){
                try {
                    barreira1.await();
                } catch (InterruptedException ex) {
                    return;
                } catch (BrokenBarrierException e) {
                    return;
                }
            }
        }

        try {
            System.out.println("Acabei a contagem, vou esperar na barreira");
            barreira2.await();
        } catch (InterruptedException ex) {
            return;
        } catch (BrokenBarrierException e) {
            return;
        }

    }
}

