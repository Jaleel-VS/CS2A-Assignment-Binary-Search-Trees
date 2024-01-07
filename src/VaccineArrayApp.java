import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class that handles queries for Vaccine data.
 */
public class VaccineArrayApp {
    private VaccineArray fullVaccineArray; // for the csv file countries
    /**
     * The csv file that's processed and queried against.
     */
    File vaccineDataSet;
    /**
     * The Scanner.
     */
    final BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));


    /**
     * Create the File object.
     *
     * @param filePath    relative path to the CSV file
     */
    public void readInFile(String filePath) {
        vaccineDataSet = new File(filePath);
    }

    /**
     * Get the number of operations string for part 1 of the assignment
     *
     * @param vaccine the Vaccine object.
     * @return the concatenated string
     */
    public StringBuilder getResult(Vaccine vaccine) {
        return new StringBuilder()
                .append(vaccine.countryName)
                .append(" = ")
                .append(vaccine.numberOfVaccines)
                .append("\n")
                .append(fullVaccineArray.getOperationCounter())
                .append(" operations\n");
    }


    /**
     * Create an array of Vaccine objects from the dataset.
     *
     * @throws IOException the io exception
     */
    void createVaccineArray() throws IOException {
        fullVaccineArray = new VaccineArray(FileHandler.getNumberOfLines(vaccineDataSet.getPath()));

        String output_file = "output/part_2/operations/" + "array_insertion_results_"+ getFileName(vaccineDataSet) + ".txt";


        if (Files.exists(Paths.get(output_file))){
            new FileWriter(output_file, false).close();
        }

        try {
            Scanner scanner = new Scanner(vaccineDataSet);

            while (scanner.hasNext()) {
                fullVaccineArray.add(new Vaccine(scanner.nextLine()));
                String insertions = fullVaccineArray.getOperationCounter() + " insert operation(s)\n";
                Files.write(Paths.get(output_file),
                        insertions.getBytes(),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            }

        } catch (FileNotFoundException exception) {
            System.out.println("Specified file not found, please check path");
        }

    }

    /**
     * User interface that responsible for querying the data from user input.
     *
     * @throws IOException the io exception
     */
    public void userInterface() throws IOException {
        String DATE_PROMPT = "Enter the date";
        String COUNTRY_PROMPT = "Enter the list of countries (end with an empty line):";
        StringBuilder outputString = new StringBuilder("Results:\n");


        // Prompt input
        System.out.println(DATE_PROMPT);
        String date = scanner.readLine();

        System.out.println(COUNTRY_PROMPT);
        while (true) {
//            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.readLine();


            if (userInput.isEmpty()) {
                outputString.deleteCharAt(outputString.length() - 1); // delete last line break
                break;
            }

            Vaccine newVaccineData = fullVaccineArray.find(new Vaccine(userInput + "," + date + ",0"));

            if (newVaccineData == null) {
                outputString.append(userInput).append(" = <Not Found>\n");
            } else {
                outputString
                        .append(newVaccineData.countryName)
                        .append(" = ")
                        .append(newVaccineData.numberOfVaccines)
                        .append("\n");
            }
            outputString.append(fullVaccineArray.getOperationCounter()).append(" lookup operations\n");
        }


        System.out.print(outputString);

    }

    String getFileName(File file) throws IOException {
        String filename = file.getCanonicalPath();
        String filenameWithoutExtension;
        if (filename.contains("."))
            filenameWithoutExtension = filename.substring(filename.lastIndexOf(System.getProperty("file.separator"))+1, filename.lastIndexOf('.'));
        else
            filenameWithoutExtension = filename.substring(filename.lastIndexOf(System.getProperty("file.separator"))+1);

        return filenameWithoutExtension;
    }

    /**
     * Run part 2 of the assignment.
     *
     * @throws IOException the io exception
     */
    public void runExperiment() throws IOException {
        while (true) {
            String new_data = scanner.readLine();

            if (new_data.isEmpty()) {
                break;
            }

            Vaccine newVaccineData = fullVaccineArray.find(new Vaccine(new_data));
            System.out.println(getResult(newVaccineData));
        }
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        VaccineArrayApp vaccineArrayApp = new VaccineArrayApp();

        if (args.length == 0) {
            System.out.println("Please specify the dataset you want to run;");
        } else if (!args[0].endsWith(".csv")) {
            System.out.println("Please enter a valid csv file");
        } else if (args[0].contains("subset")) { // for part 2 of the assignment
            vaccineArrayApp.readInFile(args[0]);
            vaccineArrayApp.createVaccineArray();
            vaccineArrayApp.runExperiment();
        } else { // for part 1 of the assignment
            vaccineArrayApp.readInFile(args[0]);
            vaccineArrayApp.createVaccineArray();
            vaccineArrayApp.userInterface();
        }

    }
}
