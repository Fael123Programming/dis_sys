package pt.ipb.dsys.class_exercise.classes;

import java.io.Serializable;
import java.util.Arrays;
import pt.ipb.dsys.class_exercise.interfaces.Task;

public class WordShuffler implements Task<String>, Serializable {
    private String str;

    public WordShuffler(String str) {
        this.str = str;
    }

    private int random(int a, int b) {
        return (int) (a + (Math.random() * (b - a + 1)));
    }
    
    private void swap(int a, int b, String[] arr) {
        String temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    @Override
    public String process() {
        String[] strArr = str.toLowerCase().split(" ");
        for (int i = 0; i < strArr.length; i++) {
            swap(i, random(0, strArr.length - 1), strArr);
        }
        return Arrays.toString(strArr);
    }
}
