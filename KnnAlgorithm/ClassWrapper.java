import java.util.Arrays;
// w ClassWrapper przechowujemy pojedynczy rekord z pliku testowego
// mamy dwa pola: className i vector
public class ClassWrapper {
    private String className;
    private double[] vector;

    private double lenght;

    public ClassWrapper(String className, double[] vector) {
        this.className = className;
        this.vector = vector;
    }

    // ponizsza funkcja zmapuje takiego stringa na obiekt klasy ClassWrapper
    //dopoki nie zostanie wywolana, zaden obiekt nie istnieje
    public static ClassWrapper createFromString(String fileLine){
        String[] parts = fileLine.split(",");

        // pierwsze cztery znaki w stringu reprezentuja wektor
        int vectorParts = 4;
        double[] vector = new double[vectorParts];
        for (int i = 0; i < vectorParts; i++) {
            vector[i] = Double.parseDouble(parts[i]);
        }
        // nazwa naszej klasy znajduje sie na ostatnim miejscu wiec pobieramy stringa
        String className = "";
        if( parts.length > 4) {
            className = parts[parts.length - 1];
        }
        return new ClassWrapper(className, vector);
    }

    public static double odlegloscDwoch(ClassWrapper c1, ClassWrapper c2 ){
        double[] vector1 = c1.getVector();
        double[] vector2 = c2.getVector();
        double result = 0;
        if (vector1.length == vector2.length){
            for (int i = 0; i < vector1.length; i++) {
                result += Math.pow((vector1[i] - vector2[i]), 2);
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "ClassWrapper{" +
                "className='" + className + '\'' +
                ", vector=" + Arrays.toString(vector) + '\n' + "len: " + lenght +
                '}';
    }

    public double getLenght() {
        return lenght;
    }
    public void setLenght(double lenght) {
        this.lenght = lenght;
    }

    public String getClassName() {
        return className;
    }

    public double[] getVector() {
        return vector;
    }
}
