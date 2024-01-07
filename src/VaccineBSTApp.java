import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

/**
 * The type Vaccine bst app.
 */
public class VaccineBSTApp extends VaccineArrayApp {

    /**
     * The Binary search tree.
     */
    final BinarySearchTree<Vaccine> binarySearchTree = new BinarySearchTree<>();

    /**
     * Gets result.
     *
     * @param countryName    the country name
     * @param binaryTreeNode the binary tree node
     * @return the result
     */
    public StringBuilder getResult(String countryName, BinaryTreeNode<Vaccine> binaryTreeNode) {
        StringBuilder stringBuilder = new StringBuilder();
        if (binaryTreeNode == null) {
            stringBuilder.append(countryName).append(" = <Not Found>\n");
        } else {
            stringBuilder
                    .append(binaryTreeNode.nodeValue.countryName)
                    .append(" = ")
                    .append(binaryTreeNode.nodeValue.numberOfVaccines)
                    .append("\n");
        }
        stringBuilder.append(binarySearchTree.getOperationCounter()).append(" lookup operation(s)\n");

        return stringBuilder;
    }

    /**
     * Create binary search tree.
     */
    void createBinarySearchTree() throws IOException {
        String outputFile = "output/part_2/operations/" + "bst_insertion_results_"+ getFileName(vaccineDataSet) + ".txt";

        if (Files.exists(Paths.get(outputFile))){ // checks if file exists, if it does clean its contents
            new FileWriter(outputFile, false).close();
        } 

        try {
            Scanner scanner = new Scanner(vaccineDataSet);

            while (scanner.hasNext()) {
                binarySearchTree.insertNode(new Vaccine(scanner.nextLine()));
                String insertions = binarySearchTree.getOperationCounter() + " insert operation(s)\n";
                Files.write(Paths.get(outputFile),
                        insertions.getBytes(),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            }

        } catch (IOException exception) {
            System.out.println("Specified file not found, please check path");
        }

    }

    /**
     *Read and process user input
     *
     * @throws IOException if invalid input
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

            Vaccine newVaccineData = new Vaccine(userInput + "," + date + ",0");

            BinaryTreeNode<Vaccine> binaryTreeNode = binarySearchTree.find(newVaccineData);


            outputString.append(getResult(userInput, binaryTreeNode));
        }

        System.out.print(outputString);

    }

    /**
     * Run the experiment for part 2 of the assignment.
     *
     * @throws IOException if invalid input
     */
    public void runExperiment() throws IOException {
        System.out.println("Remember to redirect the input from a file");
        while(true) {
            String new_data = scanner.readLine();

            if (new_data.isEmpty()) {
                break;
            }

            Vaccine newVaccineData = new Vaccine(new_data);
            BinaryTreeNode<Vaccine> binaryTreeNode = binarySearchTree.find(newVaccineData);
            System.out.println(getResult(binaryTreeNode.nodeValue.countryName, binaryTreeNode));


        }

    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        VaccineBSTApp vaccineBSTApp = new VaccineBSTApp();

        if (args.length == 0) {
            System.out.println("Please specify the dataset you want to run");
        } else if (!args[0].endsWith(".csv")) {
            System.out.println("Please enter a valid csv file");
        } else if (args[0].contains("subset")) { // for part 2 of the assignment
            vaccineBSTApp.readInFile(args[0]);
            vaccineBSTApp.createBinarySearchTree();
            vaccineBSTApp.runExperiment();
        } else { // for part 1 of the assignment
            vaccineBSTApp.readInFile(args[0]);
            vaccineBSTApp.createBinarySearchTree();
            vaccineBSTApp.userInterface();
        }

    }

}
