package de.tum.in.ase.eist;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Base64;

/*
 * TODO: Implement the AntiCovidApp registration strategy.
 *  Make sure to utilize the provided generateToken method for generating the registration token.
 */
public class AntiCovidApp implements RegistrationClient{

    //Im not sure if the push worked

    public GuestEntry registerGuest(Guest guest) {
        String guestModelNumber = guest.getPhone().getModelNumber();
        String guestBrand = guest.getPhone().getBrand();
        String guestPhoneNumber = guest.getPhone().getPhoneNumber();

        return new GuestEntry(generateToken(guestModelNumber, guestBrand, guestPhoneNumber));
    }


    /**
     * Generates a token using the model number, brand and phone number.
     *
     * @param modelNumber of the phone used for registration
     * @param brand of the phone used for registration
     * @param phoneNumber of the phone used for registration
     * @return the token as String
     */
    public static String generateToken(String modelNumber, String brand, String phoneNumber) {
        return Base64.getEncoder().encodeToString((modelNumber + brand + phoneNumber).getBytes(StandardCharsets.UTF_8));
    }

}
