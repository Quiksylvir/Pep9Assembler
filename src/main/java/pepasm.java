import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class pepasm {
    static int counter = 0;
    static int funcPosition = 0;
    static String recordedFunc = "unused";
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(args[0]);
        Scanner scanner = new Scanner(inputStream);
        StringBuilder inputStreamStringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            String fullLine = scanner.nextLine();
            fullLine = removeComments(fullLine).trim() + " ";
            String[] splitLine = fullLine.split(" ");
            for (int i = 0; i < splitLine.length; i++) {
                String convertedNext = convertToObjectCode(splitLine[i]);
                if (convertedNext.equals("branchName") || convertedNext.equals("empty")) {
                    convertedNext = "";
                } else {
                    inputStreamStringBuilder.append
                                    (convertedNext)
                            .append(" ");
                }
            }
        }
        addInstructionType(inputStreamStringBuilder);
        String fileContents = inputStreamStringBuilder.toString();
        fileContents = fileContents.strip();
        System.out.println(fileContents);
    }

    private static String convertToObjectCode(String input) {

        if (input.equalsIgnoreCase("STOP")){
            input = "00";
        }
        if (input.equalsIgnoreCase(".END")) {
            input = "";
        }
        if (input.startsWith("0x")) {
            input = input.replace("0x","");
            if (input.length() <5) {
               for (int i = 0; i < 6-input.length(); i++) {
                   input = "0" + input;
               }
            }
            input = input.substring(0,2) + " " + input.substring(2, 4) ;
        }
        if (input.equalsIgnoreCase("LDBA")) {
            input = "D0";
        }
        if (input.equalsIgnoreCase("LDWA")) {
            input = "C0";
        }
        if (input.equalsIgnoreCase("STBA")) {
            input = "F0";
        }
        if (input.equalsIgnoreCase("STWA")) {
            input = "E0";
        }
        if (input.equalsIgnoreCase("ASLA")) {
            input = "0A";
        }
        if (input.equalsIgnoreCase("ASRA")) {
            input = "0C";
        }
        if (input.equalsIgnoreCase("ADDA")) {
            input = "60";
        }
        if (input.equalsIgnoreCase("CPBA")) {
            input = "B0";
        }
        if (input.equalsIgnoreCase("ADDA")) {
            input = "60";
        }
        if (input.equalsIgnoreCase("BRNE")) {
            input = "1A";
        }
        if(input.endsWith(":")){
            recordedFunc = input.substring(0, input.length()-1);
            input = "branchName";
            funcPosition = counter;
        }
        if(input.startsWith(recordedFunc)){
            input = "00 0" + funcPosition;
        }
        if(input.isEmpty()) {
            input = "empty";
        }
        counter++;
        return input;
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

    private static String removeComments(String fullLine) {
        for (int i = 0; i < fullLine.length(); i++) {
            if (fullLine.charAt(i) == ';') {
                fullLine = fullLine.substring(0, i);
            }
        }
        return fullLine;
    }
}
