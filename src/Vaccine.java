/**
 * The type Vaccine.
 */
public class Vaccine implements Comparable<Vaccine>{
    /**
     * The Country name.
     */
    final String countryName;
    /**
     * The Vaccination date.
     */
    final String vaccinationDate;
    /**
     * The Number of vaccines.
     */
    final String numberOfVaccines;

    /**
     * Class constructor that a creates a new Vaccine object.
     *
     * @param countryRawData a single line of data that has to processed into an array
     *                       and assigned to each instance variable.
     */
    public Vaccine(String countryRawData) {
        String[] vaccineData = parseData(countryRawData);
        this.countryName = vaccineData[0];
        this.vaccinationDate = vaccineData[1];
        this.numberOfVaccines = vaccineData[2];
    }

    /**
     * Parses a single of csv-style data and exports to a String array
     *
     * @param rawData The csv data
     * @return The String array
     */
    public String[] parseData(String rawData) {
        String[] parsedData = rawData.split(",");

        return new String[]{parsedData[0].replace("-", ""),
                            parsedData[1],
                            parsedData.length == 2 ? "0": parsedData[2]};
    }

    /**
     * Compare tht current Vaccine object to the queried one.
     *
     * @param vaccineData the queried Vaccine object.
     * @return a value of 0 is equal to the object, less than 0 is lexicographically less than,
     * and greater than 0 is greater than.
     */
    public int compareTo(Vaccine vaccineData) {
        return getKey().compareTo(vaccineData.countryName + vaccineData.vaccinationDate);
    }

    /**
     * Gets the unique key of the current object.
     *
     * @return the key
     */
    public String getKey() {
        return this.countryName + this.vaccinationDate;
    }

    /**
     * A string representation of the current object. Example format: Namibia2022-02-0269240
     *
     * @return Returns the string object
     */
    public String toString() {
        return this.countryName + this.vaccinationDate + this.numberOfVaccines;
    }



}
