/**
 * The type Vaccine array.
 */
public class VaccineArray {
    /**
     * An array of Vaccine objects.
     */
    Vaccine[] vaccineDataArray;
    /**
     * The index of the current position in the array.
     */
    int index = 0;
    /**
    * A variable that counts the number of operations per query.
    */
    private int operationCounter = 0;

    /**
     * Instantiates a new Vaccine array.
     *
     * @param records the number of items in the array
     */
    public VaccineArray(int records) {
        vaccineDataArray = new Vaccine[records];
    }

    /**
     * Instantiates a new Vaccine array.
     */
    public VaccineArray() { }


    /**
     * Add a new object to the array
     *
     * @param vaccineData the Vaccine object
     */
    public void add(Vaccine vaccineData) {
        vaccineDataArray[index] = vaccineData;
        incrementOperationCounter();
        index++;
    }


    /**
     * Find the queried Vaccine object and return the found object.
     *
     * @param inputData the queried Vaccine object.
     * @return the found Vaccine object.
     */
    public Vaccine find(Vaccine inputData) {
        for (Vaccine vaccineData : vaccineDataArray) {
            incrementOperationCounter();
            if (vaccineData.compareTo(inputData) == 0) {
                return vaccineData;
            }
        }

        return null;
    }


    /**
     * Increment operation counter.
     */
    public void incrementOperationCounter() {
        this.operationCounter++;
    }

    /**
     * Store and return the current operationCounter, reset the counter.
     *
     * @return the total number of operations of the current query.
     */
    public int getOperationCounter() {
        int temp_operator_value = this.operationCounter;
        this.operationCounter = 0;
        return temp_operator_value;
    }
}
