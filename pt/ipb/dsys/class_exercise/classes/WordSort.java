package pt.ipb.dsys.class_exercise.classes;

import java.io.Serializable;
import pt.ipb.dsys.class_exercise.interfaces.Task;
import java.util.Arrays;

public class WordSort implements Task<String>, Serializable {
    private String str;

    public WordSort(String str) {
        this.str = str;
    }

    @Override
    public String process() {
        String[] strArg = str.toLowerCase().split(" ");
        Arrays.sort(strArg);
        return Arrays.toString(strArg);
    }
}
