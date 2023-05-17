import java.util.Arrays;

public class ClassWrapper {
    double wektor[];
    String etykieta;
    static double[] minWartosc;
    static double[] maxWartosc;
    static int iloscAtrybutow;

    public ClassWrapper(double[] wektor, String etykieta) {
        this.wektor = wektor;
        this.etykieta = etykieta;
        if(iloscAtrybutow==0)
            iloscAtrybutow = this.wektor.length;
        if(maxWartosc==null) {
            maxWartosc = wektor.clone();
        } else {
            for (int i = 0 ; i<wektor.length;i++)
                if(maxWartosc[i]<wektor[i])
                    maxWartosc[i]=wektor[i];
        }
        if(minWartosc==null) {
            minWartosc = wektor.clone();
        } else {
            for (int i = 0 ; i<wektor.length;i++)
                if(minWartosc[i]>wektor[i])
                    minWartosc[i]=wektor[i];
        }

    }
    //6.3,2.7,4.9,1.8,Iris-virginica
    //wektor: 6.3,2.7,4.9,1.8,
    //etykieta: Iris-virginica
    // new NUmbers(wektor, etykieta)
    @Override
    public String toString() {
        return "" +
                "wektor=" + Arrays.toString(wektor) +
                ", etykieta=" + etykieta;
    }

    public String getCorrectValue(){
        return this.etykieta;
    }
    public static ClassWrapper creatNewFromString(String line){
    String[] parts = line.split(",");

    // pierwsze x znaki w stringu reprezentuja wektor
    int vectorParts = parts.length;
    double[] wektor = new double[vectorParts-1];
        for (int i = 0; i < vectorParts-1; i++) {
        wektor[i] = Double.parseDouble(parts[i]);
    }
    // nazwa naszej klasy znajduje sie na ostatnim miejscu wiec pobieramy stringa
    String className =  parts[parts.length - 1];
        Main.etykiety.add(className);
//        if( parts.length > vectorParts) {
//        className = parts[parts.length - 1];
//    }
        // "1" -> Intege,praseInt -> 1
        return new ClassWrapper(wektor,className);
}

    public double[] getWektor() {
        return wektor;
    }

    public String getEtykieta() {
        return etykieta;
    }

    private static boolean isNumber(String text){
        try{
            Integer.parseInt(text);
        }catch (NumberFormatException e){
            return false;
        }
        return true; //jesli etykieta jest int
    }
}
