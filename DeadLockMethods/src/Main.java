
import java.util.logging.Level;
import java.util.logging.Logger;

/*
DeadLock olayları = 

bir çok thread birbirini bekliyor ve hiçbiri ilerleyemiyor.Birçok Thread belirli locku alıyor ancak diğer locklar kapıldığı için deadlock oluşuyor ve alttaki şlemler hiçbir zaman gerçekleşmiyor.

**4 yol üzerindeki arabaların kavşağın ortasındaki araın ilerlemesi için bi tarafın geri gitmesi bu geri gitmede zincirlemeler olması şartı var.Kavşaklarda kimsenin bir yere kıpırdayamadığı durumlar gerçek hayatta deadlock durumlarımızdır.

***Threadlerde ise iki adet thrteadimiz varsa 2 adette anahtarımızı bulunsun threadlerin işlemlerini gerçekleştirmeleri için 2 anahtarada aynı anda sahip olmaları gerekiyor.
thread1 birinci anahtarı thread2 ise 2. anahtarı alıyorsa bu yapılar birbirlerini bekledikleri için ikiside anahtarını bırakamadığı için DeadLock oluşmuş oluyor.
Deadlock her zaman oluşmuyor bunun sebebi ise threadlerin çalışma süresi jvm ve işletim sistemine bağlı olduğu için thread1 bazı zamanlar 2 anahtara sahip olabilir aynı durum thread 2 içinde geçerli.
Büyük projelerde deadlock oluşabileceğini ön görmek zordur.
21-*-*-*-*-*-*-*inputla düzgün çalışan diğer durumlarda deadlocka giren bir program :)
Deadlock'u reentrantLock ile çözebiliriz.
 */
public class Main {
    public static void main(String[] args) {
        DeadLockOrnegi dlo = new DeadLockOrnegi();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                dlo.thread1Fonksiyonu();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
               dlo.thread2Fonksiyonu();
            }
        });
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        dlo.threadOver();
    }
    
}
