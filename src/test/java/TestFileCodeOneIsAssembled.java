import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class TestFileCodeOneIsAssembled {

    @Test
    public void TestAssembleCodeOne() throws IOException {
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
}
