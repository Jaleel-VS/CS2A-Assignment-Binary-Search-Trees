import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Utility class that has various class methods for facilitating command line commands.
 */
public class FileHandler {
    /**
     * Gets number of lines of a given file located at the file path.
     *
     * @param filePath the file path
     * @return the number of lines
     * @throws IOException the io exception
     */
    public static int getNumberOfLines(String filePath) throws IOException {
        Process shellCommand = Runtime.getRuntime().exec("wc -l < " + filePath);
        InputStream input = shellCommand.getInputStream();
        Scanner scanner = new Scanner(input);

        if (scanner.hasNext()) {
            return Integer.parseInt(scanner.next());
        }

        return -1;
    }
}
