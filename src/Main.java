import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> namesF = new ArrayList<>();
        List<String> namesM = new ArrayList<>();

        Collections.addAll(namesF, "Boomquifa", "Derpina", "Yetzel",
                "Prudence", "Bertha", "Grogda");
        Collections.addAll(namesM, "Angus", "Cannon", "Matyson", "Semaj",
                "Satan", "Bogart", "Arthur", "Dick", "Gaylord", "Seaman", "Denim", "Elmo");
        String[] arr = {"1, 2, 0", "4, 5"};

        //Вивід результату до завдання 1
        System.out.println("namesF = " + namesF);
        System.out.println("namesWithOddNumbers(namesF) = " + namesWithOddNumbers(namesF));

        //Вивід результату до завдання 2
        System.out.println("namesM = " + namesM);
        System.out.println("nemesUpperAndSortedZtoA(namesM) = " + nemesUpperAndSortedZtoA(namesM));

        //Вивід результату до завдання 3
        System.out.println(Arrays.toString(arr));
        System.out.println("stringOfNumbers(arr) = " + stringOfNumbers(arr));

        //Вивід результату до завдання 4
        Stream<Long> longStream = fakeRand(25214903917l, 11l, 2 ^ 48l);
        longStream.limit(20).peek(System.out::println).collect(Collectors.toList());


        //Вивід результату до завдання 5
        System.out.println("zip(namesF.stream(), namesM.stream()).toList() = " + zip(namesF.stream(), namesM.stream()).toList());
    }

    //Завдання 1
    //Метод приймає на вхід список імен. Повертає рядок вигляду: 1. Ivan, 3. Peter...
    //лише з тими іменами, що стоять під непарним індексом (1, 3 тощо)
    static String namesWithOddNumbers(List<String> names) {
        AtomicInteger i = new AtomicInteger(1);

        return names.stream().
                map(n -> i.get() + ". " + n)
                .filter(s -> {
                    return i.getAndIncrement() % 2 == 1;
                })
                .collect(Collectors.joining(", "));
    }

    //Завдання 2
    //Метод приймає на вхід список рядків. Повертає список цих рядків у верхньому регістрі,
    //і відсортованих за спаданням (від Z до A).
    static String nemesUpperAndSortedZtoA(List<String> names) {
        return names.stream()
                .map(n -> n.toUpperCase())
                .sorted((e1, e2) -> e2.compareTo(e1))
                .collect(Collectors.joining(", "));
    }

    //Завдання 3
    //Є масив:
    //["1, 2, 0", "4, 5"]
    //Необхідно отримати з масиву всі числа, і вивести їх у відсортованому вигляді через кому, наприклад:
    //"0, 1, 2, 4, 5"
    static String stringOfNumbers(String[] arr) {
        return Arrays.stream(arr)
                .map(s -> s.split(", "))
                .flatMap(Arrays::stream)
                .sorted()
                .collect(Collectors.joining(", "));
    }

    //Завдання 4
    //Додатково див. class CongruentGenerator
    public static Stream<Long> fakeRand (long a, long c, long m) {
       CongruentGenerator cg = new CongruentGenerator(a,c,m);
        return Stream.iterate(0L, (seed)-> cg.next());
    }

    //Завдання 5
    //Метод, який "перемішує" елементи зі стрімів first та second, зупиняючись тоді,
    //коли у одного зі стрімів закінчаться елементи.
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        List<T> firstList = first.collect(Collectors.toList());
        List<T> secondList = second.collect(Collectors.toList());
        int min = Math.min(firstList.size(), secondList.size());

        Collections.shuffle(firstList);
        Collections.shuffle(secondList);

        List<T> concat = Stream
                .concat(firstList.stream().limit(min), secondList.stream().limit(min))
                .collect(Collectors.toList());

        Collections.shuffle(concat);
        return concat.stream();
    }
}