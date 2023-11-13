package gui_swing_events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Excel {
    private List<Double> numbers; // changed from Integer to Double

    // Constructor with ArrayList<Double> parameter
    public Excel(ArrayList<Double> numbers) {
        this.numbers = numbers;
    }

    // Constructor with String parameter
    public Excel(String numberString) {
        // Split the string into an array of strings
        String[] strNumArray = numberString.split(" ");

        // Create a list from the array
        List<String> strNumList = Arrays.asList(strNumArray);

        // Create an ArrayList<Double> from the list
        ArrayList<Double> strNumArrayList = new ArrayList<>();

        // Parse and add each number to the ArrayList<Double>
        strNumList.forEach(str -> strNumArrayList.add(Double.parseDouble(str)));

        // Assign the ArrayList to the class variable
        this.numbers = strNumArrayList;

        // Optional testing loop
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }
    }

    // Method to find the total
    public double findTotal() {
        double sum = 0;
        for (Double number : numbers) {
            sum += number;
        }
        return sum;
    }

    // Method to find the average
    public double findAverage() {
        return findTotal() / numbers.size();
    }

    // Method to find the maximum number
    public double findMax() {
        return numbers.stream().max(Double::compare).orElse(Double.NaN);
    }

    // Method to find the minimum number
    public double findMin() {
        return numbers.stream().min(Double::compare).orElse(Double.NaN);
    }
}
