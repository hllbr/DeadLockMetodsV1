
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class DeadLockOrnegi {
    private Hesap hesap1 = new Hesap();
    private Hesap hesap2 = new Hesap();
    private Random random = new Random();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    //2 lock oluşturmamızın sebebi şuan aktif olarak 2 hesap üzerinden işlem yapıyor olmamız ne kadar çok hesap olursa o kadar çok lock oluşturmamız gerekecekti
    //thread1 t1 tarafından thread2 ise t2 tarafından çalıştırılacak.thread1 in çalışması için iki lockuda alması gerekiyor
    public void thread1Fonksiyonu(){
        lock1.lock();
        lock2.lock();//burada iki anahtarıda almaya çalışıyoruz
        for(int i = 0;i<5000;i++){
            
            Hesap.paratransteri(hesap1, hesap2, random.nextInt(250));
            //buradaki yapı sayesinde hesap1den hesap2 ye 5000 defa para thransferi yapıcaz
        }
        lock1.unlock();
        lock2.unlock();
    }
    
    public void thread2Fonksiyonu(){
        lock2.lock();
        lock1.lock();//anahtarların sırasını değiştirdiğimizde oluşma ihtimali doğuyor (DeadLocklar)
        for(int i =0;i<5000;i++){
            
        Hesap.paratransteri(hesap2, hesap1, random.nextInt(250));
        }
        lock2.unlock();
        lock1.unlock();//bu şekilde para transferi kısmımıza sadece bir threadimiz girecek diğeri beklemede olacak
    }//burası deadlock için uygun durumda
    public void threadOver(){
        System.out.println("Hesap1 bakiye: "+hesap1.getBakiye()+"\nhesap2 bakiye : "+hesap2.getBakiye());
        System.out.println("Toplam Bakiye : "+(hesap1.getBakiye()+hesap2.getBakiye()));
    }
}
