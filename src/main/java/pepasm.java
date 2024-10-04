import java.util.HashMap;

public class pepasm {
    public static void main(String[] args) {
        HashMap<String, String> assemblerHashMap = new HashMap<>();
        addMappedValues(assemblerHashMap);
    }

    private static void addMappedValues(HashMap<String, String> assemblerHashMap) {
        assemblerHashMap.put("", "");
    }
}
