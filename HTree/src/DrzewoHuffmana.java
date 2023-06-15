import java.util.*;

class Wezel implements Comparable<Wezel> {
    char symbol;
    int czestotliwosc;
    Wezel lewy;
    Wezel prawy;

    public Wezel(char symbol, int czestotliwosc) {
        this.symbol = symbol;
        this.czestotliwosc = czestotliwosc;
    }

    public Wezel(char symbol, int czestotliwosc, Wezel lewy, Wezel prawy) {
        this.symbol = symbol;
        this.czestotliwosc = czestotliwosc;
        this.lewy = lewy;
        this.prawy = prawy;
    }

    @Override
    public int compareTo(Wezel other) {

        if (this.czestotliwosc != other.czestotliwosc) {
            return this.czestotliwosc - other.czestotliwosc;
        } else {

            if (this.lewy != null && other.lewy != null) {
                // Jeśli oba wezły mala lewe dzieci rekurencyjnie porównuje lewe dzieci
                return this.lewy.compareTo(other.lewy);
            }
            // Sprawdza, czy oba węzły nie mają lewego dziecka
            else if (this.lewy == null && other.lewy == null) {

                return this.symbol - other.symbol;
            }
            // Jeśli tylko jeden węzeł ma lewe dziecko
            else if (this.lewy != null) {
                //  bieżący węzeł ma większą wagę
                return 1;
            }
            else {
                // Jeśli tylko drugi węzeł ma lewe dziecko/ inny węzeł ma większą wagę
                return -1;
            }
        }
    }

}

public class DrzewoHuffmana {
    private Wezel korzen;
    private Map<Character, String> symbolNaKod;

    public void budujDrzewo(String wejscie) {
        Map<Character, Integer> mapaCzestotliwosci = new HashMap<>(); // przechowuje częstości wystąpień symboli w ciągu wejściowym
        for (char symbol : wejscie.toCharArray()) {
            mapaCzestotliwosci.put(symbol, mapaCzestotliwosci.getOrDefault(symbol, 0) + 1);
        }

        // ta kolejka tworzona na podstawie częstości wezlów
        PriorityQueue<Wezel> kolejkaPriorytetowa = new PriorityQueue<>();

        for (Map.Entry<Character, Integer> wpis : mapaCzestotliwosci.entrySet()) {// Dodawanie węzłów do kolejkiPrioret na podstawie mapy częstości
            kolejkaPriorytetowa.offer(new Wezel(wpis.getKey(), wpis.getValue()));
        }


        while (kolejkaPriorytetowa.size() > 1) { //budowanie drzewa
            // Pobieranie dwóch węzłów o najniższych częstościach z kolejki
            Wezel lewy = kolejkaPriorytetowa.poll();
            Wezel prawy = kolejkaPriorytetowa.poll();

            // Tworzenie rodzica dla dwóch węzłów i dodanie go z powrotem do kolejki
            Wezel rodzic = new Wezel('\0', lewy.czestotliwosc + prawy.czestotliwosc, lewy, prawy);
            kolejkaPriorytetowa.offer(rodzic);
        }

        korzen = kolejkaPriorytetowa.poll();

        symbolNaKod = new HashMap<>();

        // Generuje kody dla symboli na podstawie drzewa
        generujKody(korzen, "");
    }

    private void generujKody(Wezel wezel, String kod) {
        if (wezel == null) {
            return;
        }

        if (wezel.lewy == null && wezel.prawy == null) {  // Jeśli węzeł jest liściem, przypisuje mu kod i kończy rekurencję
            symbolNaKod.put(wezel.symbol, kod);
            return;
        }

        generujKody(wezel.lewy, kod + "0");
        generujKody(wezel.prawy, kod + "1");
    }

    public String zakoduj(String wejscie) {
        StringBuilder zakodowane = new StringBuilder();
        for (char symbol : wejscie.toCharArray()) {
            zakodowane.append(symbolNaKod.get(symbol));// Dodawanie kodu dla danego symbolu do zakodowanego ciągu
        }
        return zakodowane.toString();
    }

    public String getKodDlaSymbolu(char symbol) {
        return symbolNaKod.get(symbol);
    }

    public void wypiszKody() {
        for (Map.Entry<Character, String> wpis : symbolNaKod.entrySet()) {
            System.out.println( wpis.getKey() + " : " + wpis.getValue());
        }
    }

    public static void main(String[] args) {
        String wejscie = "aabcd";

        DrzewoHuffmana drzewoHuffmana = new DrzewoHuffmana();
        drzewoHuffmana.budujDrzewo(wejscie);
// Wyświetlanie częstości występowania symboli
        Map<Character, Integer> mapaCzestotliwosci = new HashMap<>();
        for (char symbol : wejscie.toCharArray()) {
            mapaCzestotliwosci.put(symbol, mapaCzestotliwosci.getOrDefault(symbol, 0) + 1);
        }

        System.out.println("Czestosci symboli:");
        for (Map.Entry<Character, Integer> wpis : mapaCzestotliwosci.entrySet()) {
            System.out.println( wpis.getKey() + ", czestosc: " + wpis.getValue());
        }


        System.out.println("Kody Huffmana:");
        drzewoHuffmana.wypiszKody();

        String zakodowane = drzewoHuffmana.zakoduj(wejscie);
        System.out.println("Zakodowana sekwencja: " + zakodowane);

    }
}
