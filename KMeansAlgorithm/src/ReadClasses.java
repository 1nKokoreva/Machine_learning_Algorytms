import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ReadClasses {
    private String testPath;

    public ReadClasses(String testPath) {
        this.testPath = testPath;
    }
    public List<String> readLines(String path) {
        List<String> newLista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                newLista.add(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return newLista;
    }

    private List<ClassWrapper> zwrotClassWrapper(String path) {
        List<String> l = readLines(path);
        List<ClassWrapper> listWrapper = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            String x = l.get(i);
            ClassWrapper wrapper = ClassWrapper.creatNewFromString(x);
            listWrapper.add(wrapper);
        }
        System.out.println(listWrapper);
        return listWrapper;
    }

    public List<ClassWrapper> getClassWrapperTest() {
        return zwrotClassWrapper(testPath);
    }

}
