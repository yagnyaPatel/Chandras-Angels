package edu.ucsb.munchease.data;

public class InvalidJsonException extends Exception {
    public InvalidJsonException() {
        // Generic error message
        super("JSON body does not contain expected elements of Yelp JSON response");
    }

    public InvalidJsonException(String missingElementName) {
        // Specific missing element error message
        super("JSON body does not contain expected element \"" + missingElementName + "\" of Yelp JSON response");
    }
}
