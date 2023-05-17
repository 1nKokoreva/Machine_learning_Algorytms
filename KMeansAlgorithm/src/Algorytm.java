import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Algorytm {

    static boolean changed = false;

    static double wyliczD(ClassWrapper wektor1, List<Double> centroida) {
        double res = 0;
        for(int i=0;i<wektor1.wektor.length;i++) {
            res+=(wektor1.wektor[i]- centroida.get(i))*(wektor1.wektor[i]- centroida.get(i));
        }
        //System.out.println("Dystans pomiędzy wektorem  " + wektor1 + " a centroidą " + wektor2 + ": " + res);
        return Math.sqrt(res);
    }


    static void przypiszWektoryDoCentroidow() {
        System.out.println("--------------");
        changed = false;
        List<Map<String, Double>> countery = stworzMapeCounterowEtykiet(Main.centroidy.size());
//        double sumaD = 0.0;
        for(int i = 0; i<Main.wektory.size(); i++) {
            ClassWrapper wektor = Main.wektory.get(i); //
            double minD = Algorytm.wyliczD(wektor, Main.centroidy.get(0));
            int centroid_ind = 0;
            for(int j = 0; j < Main.centroidy.size(); j++) {
                double d = Algorytm.wyliczD(wektor, Main.centroidy.get(j));
//                sumaD+=d;
                if(d<minD) {
                    minD = d;
                    centroid_ind = j;
                }
            }
//            System.out.println("Zaklasyfikowano wektor z etykieta " + Main.wektoryNiezmienne.get(i).etykieta + " do centroidu " + centroid_ind);
            // zapisywac wektory dla kazdego centroidu
            String staraEtykieta = wektor.etykieta;
            wektor.etykieta = centroid_ind + "";
            if(!staraEtykieta.equals(wektor.etykieta))
                changed = true;
            // dla wyliczenia % dodajemy counter
            String etykietaPrawdziwa = Main.wektoryNiezmienne.get(i).etykieta;
            Map<String, Double> newCounter = countery.get(centroid_ind);
            newCounter.put(etykietaPrawdziwa, newCounter.get(etykietaPrawdziwa)+1);
            countery.set(centroid_ind, newCounter);
        }
        for(int i = 1; i<countery.size();i++) {
            System.out.print("Do centroidy " + i + " zaklasyfikowano \n" /*+ countery.get(i)*100/Main.wektory.size() + "%"*/);
            double sum = 0;
            for(String etykieta : countery.get(i).keySet()) {
                sum+=countery.get(i).get(etykieta);
            }
            for(String etykieta : countery.get(i).keySet()) {
                if(sum!=0)
                    System.out.print(etykieta + " "  + countery.get(i).get(etykieta)*100/sum + "% \n");
                else
                    System.out.print( etykieta + " 0% \n");
            }
            System.out.println();
//            System.out.println("Suma odleglosci do centroidy " + i + " : "+ sumyD.get(i));
        }
//        System.out.println("Suma odleglosci wektorow od centroid: " +sumaD); //czystosc klastra - ile % ktorej etykiety jest d kazdym klastrze
    }
//Do c1 zak 20% wS, 40% wP, .. wO

    static List<Double> stworzNowyWektor(int length) {
        List<Double> noweWsp = new ArrayList<>();
        for(int i = 0;i<length;i++) {
            noweWsp.add(0.0);
        }
        return noweWsp;
    }

    static List<Map<String, Double>> stworzMapeCounterowEtykiet(int size) {
        List<Map<String, Double>> res = new ArrayList<>();
        for(int i=0;i<size;i++) {
            Map<String, Double> counteryCentroida = new HashMap<>();
            for (String etykieta : Main.etykiety) {
                counteryCentroida.put(etykieta, 0.0);
            }
            res.add(counteryCentroida);
        }
        return res;
    }

    static void pzeliczCentroidy() {
        for(int i = 0; i < Main.centroidy.size(); i++) {
            List<Double> noweWsp = stworzNowyWektor(Main.centroidy.get(i).size()); //w[0,0,0,0] pusty

            //część, która liczy średnią z dopomogą wektorów, które zapisaliśćmy dla karzdego centroidu
                double iloscWektorow = 0.0; // ilosc wektorow danej centoidy -> zeby na koncu rozdzielic i wyliczyc srednia

                for (ClassWrapper wektor : Main.wektory) { // patrzymy dla kazdego wektoru
                    if(Integer.parseInt(wektor.etykieta)==i) { //jesli jest przypisany do danej centroidy
                        for (int j=0;j<noweWsp.size();j++) { //dodajemy wspolrzedne wektora do nowych wspolrzednych centroidy (poki co bez rozdzielania)
                            double nowaWart = noweWsp.get(j); //c[0,0,0]->c[x1+x2,y1+y2,z1+z2] jesli x1, x2 ... sa wsp. wektora, ktory ma etykiete danego centroida
                            nowaWart+=wektor.getWektor()[j];
                            noweWsp.set(j, nowaWart);
                        }
                        iloscWektorow++; // dodajemy +1 wektor zeby rozdzielic na koncu wspolrzedne na liczbe wektorow
                    }
                }

            if(iloscWektorow>0) {
                for (int j=0;j<noweWsp.size();j++) { //c[x1+x2,y1+y2,z1+z2] -> c[(x1+x2)/2,(y1+y2)/2,(z1+z2)/2]
                    double nowaWart = noweWsp.get(j)/iloscWektorow;
                    noweWsp.set(j, nowaWart);
                }
                Main.centroidy.set(i, noweWsp);
            }
        }
        double sumaD = 0.0;
        for(int i = 0; i<Main.wektory.size(); i++) {
            ClassWrapper wektor = Main.wektory.get(i);
            for (int j = 0; j < Main.centroidy.size(); j++) {
                double d = Algorytm.wyliczD(wektor, Main.centroidy.get(j));
                sumaD += d;
            }
        }
        System.out.println("Suma odleglosci wektorow od centroid: " +sumaD); //czystosc klastra - ile % ktorej etykiety jest d kazdym klastrze
    }
}
