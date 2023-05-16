
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadz liczbe najblizszych sasiadow, ktorych program powinnen wrocic: ");

        int k = scanner.nextInt();
        KnnAlgorytm knn = new KnnAlgorytm(k);
        ReadClasses readClasses = new ReadClasses("train.txt", "test.txt");


        System.out.println("Czy chcesz wpisac wlasny wektor? \n" +
                "NIE - wpisz 0, \nTAK - wpisz 1.");
        int opcja = scanner.nextInt();
        switch (opcja) {
            case 0:
                knn.nazwaTestPoint();
                break;
            case 1:
                System.out.println("napisz swoj wektor x,y, ..., d: ");
                String vec = scanner.next();
                ClassWrapper classWrapper1 = ClassWrapper.createFromString(vec);
                String odpowiedz =  knn.predictClass(classWrapper1, readClasses.getClassWrapperTrain());
                System.out.println(odpowiedz);
                break;
            default:
                System.out.println("Nie ma takiej opcji! \n" +
                                   "Wpisz 0 lib 1.");
        }



    }
}
