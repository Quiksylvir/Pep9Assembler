import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class pepasm {
    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, String> assemblerHashMap = new HashMap<>();
        addMappedValues(assemblerHashMap);
        FileInputStream inputStream = new FileInputStream(args[0]);
        Scanner scanner = new Scanner(inputStream);
        StringBuilder inputStreamStringBuilder = new StringBuilder();

        while (scanner.hasNext()){
            inputStreamStringBuilder.append
                            (scanner.next())
                    .append(" ");
        }
        String fileContents = inputStreamStringBuilder.toString();
        fileContents = fileContents.strip();
        System.out.println(fileContents);
    }

    private static void addMappedValues(HashMap<String, String> assemblerHashMap) {
        assemblerHashMap.put("", "");
    }
}
