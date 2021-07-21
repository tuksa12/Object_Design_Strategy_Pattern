package de.tum.in.ase.eist;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BiergartenRegistrationForm implements RegistrationClient{

    /**
     * Handles the input of the guest's name, address and time of arrival.
     *
     * @param guest that enters the biergarten
     * @return GuestEntry containing information about the guest
     */
    public GuestEntry registerGuest(Guest guest) {
        String name = enterStringInForm("Please enter your name:");
        String address = enterStringInForm("Please enter your address:");
        LocalDateTime arrival = enterDateInForm("Please enter your arrival time in the following format: d.MM.yyyy, H:m, e.g. '21.07.2021, 07:33'");
        return new GuestEntry(arrival, name, address);
    }

    private String enterStringInForm(String message) {
        return RegistrationFormReader.readString(message);
    }

    private LocalDateTime enterDateInForm(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy, H:m");
        String dateString = enterStringInForm(message);
        try {
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException ignored) {}
        return enterDateInForm("The arrival time was not in the correct format or is invalid, please try again!");
    }

    static class RegistrationFormReader {
        private static InputStream inputStream = System.in;
        private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        public static String readString(String text) {
            if (System.in != inputStream) {
                inputStream = System.in;
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            }
            try {
                System.out.println(text);
                return bufferedReader.readLine();
            } catch (IOException e) {
                throw new UncheckedIOException("The input could not be read.", e);
            }
        }
    }

}
