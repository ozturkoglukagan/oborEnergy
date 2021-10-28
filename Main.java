
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import java.util.List;

public class Main {
    // taking the file destination from our user
    public static String fileDestination = "empty";
    // holding the data which we are going to read from our dataset
    static List<String> dataHolder = new ArrayList<String>();
    // keeping track of missing data
    public static String[] missingDataPositions = new String[1000];
    // counting the line in main dataset
    public static int lineCount = 0;
    // keeping the row count momentarily to compare to rowCount
    public static int momentaryRowCount = 0;
    // counting the rows
    public static int rowCount = 0;
    public static int insideDataCount = 0;
    public static int borderDataCount = 0;
    public static int emptyDataCount = 0;

    public static void main(String[] args) throws FileNotFoundException {

        try {
            // taking the file destination from user
            fileDestination = args[0];

            // checking if there has been a problem about taking the fileDestination
            if (fileDestination.equals("empty")) {
                // printing an error message to our user
                System.out.println("File Destination unknown, please try again.");

            }
        } catch (Exception e) {
            // printing an error message to our user
            System.out.println("Couldn't detect the file, please try again.");

        }

        readCSVFile(fileDestination);
        // stating the data count
        int yearlyDataCount = 8760 * 2;
        System.out.println("started");
        printData(yearlyDataCount);
        System.out.println("finished");

    }

    // deleting the used data from our arrayList
    public static void deleteUsedData(int yearlyDataCount) {
        for (int i = 0; i < yearlyDataCount; i++) {
            dataHolder.remove(0);
        }
    }

    public static void printData(int yearlyDataCount) {

        // to arrange the filenames
        int fileCount = 2021;

        /*
         * // to try if our file reading algorithm works int count = 0;
         */

        // assigning the fileName variable
        String fileName;

        // removing the date and energy price rows
        dataHolder.remove(0);
        dataHolder.remove(0);

        // using try catch to create the output file if not exist
        try {

            // while there is data remaining in our arraylist do this
            while (dataHolder.size() != 0) {

                // creating different files with filecount changing
                fileName = "enPrice" + fileCount + ".csv";

                // to write our data to CSV files defining it in while loop to avoid creating
                // multiple writers
                BufferedWriter bf = new BufferedWriter(new FileWriter(new File(fileName)));

                // using for loop the control how many variables we are going to print
                int index = 0;
                for (; index < yearlyDataCount;) {

                    // printing in indexes
                    bf.write(dataHolder.get(index));

                    // using append ',' to write into the next cell
                    bf.append(',');
                    index++;
                    bf.write(dataHolder.get(index));

                    // using append '\n' to write cells in new line
                    bf.append('\n');
                    index++;

                }

                // deleting used data from our list
                deleteUsedData(yearlyDataCount);
                fileCount++;
                bf.close();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        System.out.println("Done");

    }

    // to read our file and parsing its values to an arraylist
    public static void readCSVFile(String directory) throws FileNotFoundException {

        // to know the index of missing data\s
        int missingDataIndex = 0;

        // using try catch to prevent the program from crashing
        try {

            // buffered reader reads the input from our file
            BufferedReader br = new BufferedReader(new FileReader(new File(directory)));
            String line;
            // creating a while loop to continue when there is a next element to read
            while ((line = br.readLine()) != null) {

                // taking the linecount to use after to print into a new file

                // splitting data with commas
                String[] values = line.split(",");

                // looking for missing data spots
                for (String data : values) {

                    // counting the rows, counting the data
                    momentaryRowCount++;
                    insideDataCount++;

                    // checking if the data spot is empty
                    /*
                     * if (data.equals("")) {
                     * 
                     * // saving the missing inside data spots' index
                     * missingDataPositions[missingDataIndex] = lineCount + "," + momentaryRowCount;
                     * /* System.out.println(lineCount + "," + momentaryRowCount); // to debug
                     * emptyDataCount++; missingDataIndex++;
                     * 
                     * }
                     */
                }

                // counting the rows and saving them in the rowControlNumber to see if there is
                // any missing elements in our last column
                if (momentaryRowCount >= rowCount) {
                    rowCount = momentaryRowCount;
                    momentaryRowCount = 0;

                    // if the control number is smaller than the momentary row number, then there is
                    // a missing element in the last column of our dataset
                } else if (momentaryRowCount < rowCount) {

                    // finding the missing element number in our last column
                    // saving the missing border spots' index
                    missingDataPositions[missingDataIndex] = lineCount + "," + (momentaryRowCount + 1);
                    borderDataCount++;
                    missingDataIndex++;
                    momentaryRowCount = 0;
                } else {
                    momentaryRowCount = 0;
                }

                // adding read elements to our arrayList
                for (String numbers : values) {
                    dataHolder.add(numbers);
                }

            }

            // closing the bufferedReader to save space in memory
            br.close();

            // using catch to better understand if there has been a problem about our code
        } catch (Exception e) {
            System.out.println("an error has ben occured please try again.");

        }

    }

    // printing our data to console
    public static void printDatatoConsole(int index) {
        System.out.println(dataHolder.get(index));
    }
}