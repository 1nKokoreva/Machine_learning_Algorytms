import java.util.*;


public class KnnAlgorytm {
    int k;

    public KnnAlgorytm(int k) {
        this.k = k;
    }

    public String predictClass(ClassWrapper testPoint, List<ClassWrapper> classWrappersTrain) {

        for (int i = 0; i < classWrappersTrain.size(); i++) {
            ClassWrapper trainRecord = classWrappersTrain.get(i); //liniejka w train
            double lenght = ClassWrapper.odlegloscDwoch(testPoint, trainRecord); //length" to odlegosc pomidzy naszym test pointem a wierszem
            trainRecord.setLenght(lenght);
        }

        Collections.sort(classWrappersTrain, new Comparator<ClassWrapper>() {
            @Override
            public int compare(ClassWrapper o1, ClassWrapper o2) {
                double x = o1.getLenght() - o2.getLenght();
                if (x == 0) {
                    return 0;
                } else if (o1.getLenght() > o2.getLenght()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        List<ClassWrapper> kElements = classWrappersTrain.subList(0, k); //zwraca k pierwszych elementow z listy classWraper
        LinkedHashMap<String, Integer> classCountMap = new LinkedHashMap<>();

        for (ClassWrapper w : kElements) {  //liczy, ilosc wystapien dla karzdej etykieta
            Integer classCount = classCountMap.get(w.getClassName());
            if (classCount != null) {
                classCount += 1;
                classCountMap.put(w.getClassName(), classCount);
            } else {
                classCountMap.put(w.getClassName(), 1);
            }

        }

        String className = classCountMap.entrySet() //pobieramy etyk., ktora wystapila najwiecej razy
                .stream()
                .max(Map.Entry.comparingByValue())  // max szuka najwiekszej wartosci w strumieniu ale musi wiedziec na jakiej podsatwie okreslamy najwieksza wartosc gdy mamy elementy {key:value}
                .map(Map.Entry::getKey)// gdy znajdziemy najwieksza wartosc to pobiermy jej etykieta ktora jest kluczem
                .orElse(""); // jesli nie zanleziono niczego to zwracamy pusty string
/*
        Po posortowaniu wierszy bazujc na ich odlegoci od naszego test pointa otrzymalimy:
            7.7,2.6,6.9,2.3,Iris-virginica length: 3
            7.9,3.8,6.4,2.0,Iris-virginica length: 4
            5.1,3.3,1.7,0.5,Iris-setosa length: 5
            5.5,4.2,1.4,0.2,Iris-setosa length: 6
            4.4,2.9,1.4,0.2,Iris-setosa length: 7
            4.6,3.6,1.0,0.2,Iris-setosa length: 9
            nastepnie wybieramy 'K' pierwszych (czyli inaczej najbliszych) wierszy do test pointa

            zakadajac ze K = 3
            pobierzemy sobie te wiersze:
            7.7,2.6,6.9,2.3,Iris-virginica length: 3
            7.9,3.8,6.4,2.0,Iris-virginica length: 4
            5.1,3.3,1.7,0.5,Iris-setosa length: 5

            a nastepnie sprawdzamy ktra etykieta wystpuje najczesciej
            Iris-setosa => wystpuje 1 raz
            Iris-virginica => wystepuje 2 razy
            wic przypuszczamy ze szukana etykieta dla naszego test pointa to 'Iris-virginica'
         */
        return className;
    }

    public void nazwaTestPoint() {
        ReadClasses readClasses = new ReadClasses("train.txt", "test.txt");

        List<ClassWrapper> testPoints = readClasses.getClassWrapperTest();
        List<ClassWrapper> trainPoints = readClasses.getClassWrapperTrain();
        double testPointsCount = testPoints.size();
        double correctCount = 0;
        for (int i = 0; i < testPointsCount; i++) { //zwraca etykiety dla dannego testpointa
            String predictedClass = predictClass(testPoints.get(i), trainPoints);
            if (predictedClass.equals(testPoints.get(i).getClassName())) {
                correctCount += 1;
            }
            ClassWrapper testPoint = testPoints.get(i);
            System.out.println(Arrays.toString(testPoint.getVector()) + " " + testPoint.getClassName() + " predicted: " + predictedClass);
        }
        System.out.println("Dokladnosc: " + (int) ((correctCount / testPointsCount) * 100) + "%");

    }

}

