import java.util.concurrent.atomic.AtomicLong;

public class CongruentGenerator {
    //Завдання 4
    //Використовуючи Stream.iterate, створіть безкінечний стрім випадкових чисел (лінійний конгруентний генератор).
    //Для цього почніть з x[0] = seed, і далі кожний наступний елемент рахуйте
    //за формулою на зразок x[n + 1] = 1 (a x[n] + c) % m для коректних значень a, c, та m.
    //Необхідно імплементувати метод, що приймає на вхід параметри a, c, та m, і повертає Stream<Long>.
    //Для тесту використовуйте такі дані: a = 25214903917, c = 11, m = 2^48 (2в степені48`)

    private long a, c, m;
    private long x;

    public CongruentGenerator(long a, long c, long m) {
        this.a = a;
        this.c = c;
        this.m = m;
    }

    public long next(){
        x = (x * a + c) % m;
        return x;
    }
}
