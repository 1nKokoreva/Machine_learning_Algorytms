import java.util.*;

public class Main {
    static List<ClassWrapper> wektory; //wektory, do ktorych zapisujemy etykiety centroidow
    static List<List<Double>> centroidy;

    public static void main(String[] args) {

        ReadClasses readClasses = new ReadClasses("src/test1.csv");
        wektory = readClasses.getClassWrapperTest();


        Scanner scanner = new Scanner(System.in);

        System.out.print("Wprowadź liczbę klastrów: ");
        int centroidNum = Integer.parseInt(scanner.nextLine());
        centroidy = new ArrayList<>(); //klastry

        //generacja centroid poczatkowych
        for(int i =0; i<centroidNum;i++) {
            List<Double> wektorCentroida;
            wektorCentroida = new ArrayList<Double>();
            for(int j=0; j<ClassWrapper.iloscAtrybutow;j++) {
                double min = ClassWrapper.minWartosc[j];
                double max = ClassWrapper.maxWartosc[j];
                double randomValue = min + (max - min) * Math.random();
                wektorCentroida.add(randomValue);
            }
            System.out.println("\nCentroid: ");
            for (double x : wektorCentroida) {
                System.out.print(x + ", ");
            }
            centroidy.add(wektorCentroida);
        }
        System.out.println();

//        int counter = 0;
//        while (true) {
//            counter++;
//      System.out.println((counter)+ ". iteracja algorytmu:");
        System.out.println("=============Losowo wygenerowane centroidy=============");
        Algorytm.przypiszWektoryDoCentroidow();
        Algorytm.pzeliczCentroidy();
        System.out.println("=============Przypisanie wektorow po 1 iteracji algorytmu=============");
        Algorytm.przypiszWektoryDoCentroidow();
    }

}
