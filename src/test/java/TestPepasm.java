import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TestPepasm {

    @Test
    public void TestAssembleCodeOneIsFetched() throws IOException {
        String sampleProgram1 ="LDBA 0x0048, i STBA 0xFC16, d LDBA 0x0069, i STBA 0xFC16, d STOP .END";
        FileInputStream inputStream = new FileInputStream("C:\\Users\\timot\\Desktop\\CS\\CS230 (Architecture)\\Architecture Code SRC\\pepasm\\src\\test\\resources\\program1.pep");
        Scanner scanner = new Scanner(inputStream);
        StringBuilder inputStreamStringBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            inputStreamStringBuilder.append
                    (scanner.next())
                    .append(" ");
        }
        String fileContents = inputStreamStringBuilder.toString();
        fileContents = fileContents.strip();
        Assertions.assertEquals(sampleProgram1, fileContents);
    }

    @Test
    public void TestAssembleCodeTwoIsFetched() throws IOException {
        String sampleProgram2 ="LDWA 0xFC16, i ; Load 0xFFFF in the A reg. STWA 0x7000, d LDWA 0x0041, i ASLA ASRA STBA 0xFC16, d STOP .END";
        FileInputStream inputStream = new FileInputStream("C:\\Users\\timot\\Desktop\\CS\\CS230 (Architecture)\\Architecture Code SRC\\pepasm\\src\\test\\resources\\program2.pep");
        Scanner scanner = new Scanner(inputStream);
        StringBuilder inputStreamStringBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            inputStreamStringBuilder.append
                            (scanner.next())
                    .append(" ");
        }
        String fileContents = inputStreamStringBuilder.toString();
        fileContents = fileContents.strip();
        Assertions.assertEquals(sampleProgram2, fileContents);
    }

    @Test
    public void TestAssembleCodeThreeIsFetched() throws IOException {
        String sampleProgram3 ="LDWA 0xFFFF, i ADDA 0x01, i ADDA 0x4D, i STBA 0xFC16, d LDBA 0x06F, i STBA 0xFC16, d STBA 0xFC16, d LDBA 0x062, i STBA 0xFC16, d ; Are you? STOP .END";
        FileInputStream inputStream = new FileInputStream("C:\\Users\\timot\\Desktop\\CS\\CS230 (Architecture)\\Architecture Code SRC\\pepasm\\src\\test\\resources\\program3.pep");
        Scanner scanner = new Scanner(inputStream);
        StringBuilder inputStreamStringBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            inputStreamStringBuilder.append
                            (scanner.next())
                    .append(" ");
        }
        String fileContents = inputStreamStringBuilder.toString();
        fileContents = fileContents.strip();
        Assertions.assertEquals(sampleProgram3, fileContents);
    }


    @Test
    public void TestAssembleCodeFourIsFetched() throws IOException {
        String sampleProgram4 ="LDBA 0x0041, i next_let: STBA 0xFC16, d ADDA 0x0001, i CPBA 0x005B, i BRNE next_let, i STOP .END";
        FileInputStream inputStream = new FileInputStream("C:\\Users\\timot\\Desktop\\CS\\CS230 (Architecture)\\Architecture Code SRC\\pepasm\\src\\test\\resources\\program4.pep");
        Scanner scanner = new Scanner(inputStream);
        StringBuilder inputStreamStringBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            inputStreamStringBuilder.append
                            (scanner.next())
                    .append(" ");
        }
        String fileContents = inputStreamStringBuilder.toString();
        fileContents = fileContents.strip();
        Assertions.assertEquals(sampleProgram4, fileContents);
    }

    @Test
    public void testRunMainProgram1() throws FileNotFoundException {
        String[] args = {"C:\\Users\\timot\\Desktop\\CS\\CS230 (Architecture)\\Architecture Code SRC\\pepasm\\src\\main\\java\\program4.pep"};
        pepasm.main(args);
    }

}
