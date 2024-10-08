import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class pepasm {
    static int counter = 0;
    static int funcPosition;
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(args[0]);
        Scanner scanner = new Scanner(inputStream);
        StringBuilder inputStreamStringBuilder = new StringBuilder();


        while (scanner.hasNext()){
            String convertedNext = convertToObjectCode(scanner);
            if (convertedNext.equalsIgnoreCase("branchName")) {
                convertedNext = "";
            } else {
                inputStreamStringBuilder.append
                                (convertedNext)
                        .append(" ");
            }
        }
        addInstructionType(inputStreamStringBuilder);
        removeComments(inputStreamStringBuilder);
        String fileContents = inputStreamStringBuilder.toString();
        fileContents = fileContents.strip();
        System.out.println(fileContents);
    }

    private static String convertToObjectCode(Scanner input) {
        String objectCode = input.next();

                if (objectCode.equalsIgnoreCase("STOP")){
            objectCode = "00";
        }
        if (objectCode.equalsIgnoreCase(".END")) {
            objectCode = "";
        }
        if (objectCode.startsWith("0x")) {
            objectCode = objectCode.replace("0x","");
            if (objectCode.length() <5) {
               for (int i = 0; i < 6-objectCode.length(); i++) {
                   objectCode = "0" + objectCode;
               }
            }
        }
        if (objectCode.equalsIgnoreCase("LDBA")) {
            objectCode = "D0";
        }
        if (objectCode.equalsIgnoreCase("LDWA")) {
            objectCode = "C0";
        }
        if (objectCode.equalsIgnoreCase("STBA")) {
            objectCode = "F0";
        }
        if (objectCode.equalsIgnoreCase("STWA")) {
            objectCode = "E0";
        }
        if (objectCode.equalsIgnoreCase("ASLA")) {
            objectCode = "0A";
        }
        if (objectCode.equalsIgnoreCase("ASRA")) {
            objectCode = "0C";
        }
        if (objectCode.equalsIgnoreCase("ADDA")) {
            objectCode = "60";
        }
        if (objectCode.equalsIgnoreCase("CPBA")) {
            objectCode = "B0";
        }
        if (objectCode.equalsIgnoreCase("ADDA")) {
            objectCode = "60";
        }
        if (objectCode.equalsIgnoreCase("BRNE")) {
            objectCode = "1A";
        }
        if(objectCode.length() == 5){
            objectCode = objectCode.substring(0,2) + " " + objectCode.substring(2, 4) ;
        }
        if(objectCode.endsWith(":")){
            objectCode = "branchName";
            funcPosition = counter;
        }
        if(objectCode.equalsIgnoreCase("next_let,")){
            objectCode = "00 0" + funcPosition;
        }
        counter++;
        return objectCode;
    }

    private static void addInstructionType(StringBuilder inputStreamStringBuilder) {
        for (int i = 0; i < inputStreamStringBuilder.length(); i++) {
            if (inputStreamStringBuilder.charAt(i) == 'i') {
                inputStreamStringBuilder.deleteCharAt(i);
                inputStreamStringBuilder.deleteCharAt(i-1);
                if (inputStreamStringBuilder.charAt(i - 8) != 'A') {
                    inputStreamStringBuilder.setCharAt(i - 8, '0');
                }
            }
            if (inputStreamStringBuilder.charAt(i) == 'd') {
                inputStreamStringBuilder.deleteCharAt(i);
                inputStreamStringBuilder.deleteCharAt(i-1);
                if (inputStreamStringBuilder.charAt(i - 8) != 'A') {
                    inputStreamStringBuilder.setCharAt(i - 8, '1');
                }
            }
        }
    }

    private static void removeComments(StringBuilder inputStreamStringBuilder) {
        for (int i = 0; i < inputStreamStringBuilder.length(); i++) {
            if (inputStreamStringBuilder.charAt(i) == ';') {
                int counter = i;
                while(inputStreamStringBuilder.charAt(counter) != '.' && inputStreamStringBuilder.charAt(counter) != '?') {
                    counter++;
                }
                inputStreamStringBuilder.replace(i-1,counter+1,"");
        }
        }
    }

}
