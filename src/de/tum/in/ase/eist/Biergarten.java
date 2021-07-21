package de.tum.in.ase.eist;

import java.util.ArrayList;
import java.util.List;

public class Biergarten {

    private final List<GuestEntry> guests = new ArrayList<>();

    public static void main(String[] args) {
        Biergarten biergarten = new Biergarten();

        Guest karlaKolumna = new Guest(new Phone("IA6ASD", "karlaPhone", "089/3534853948", true));
        Guest fredFrankenstein = new Guest(new Phone("UDF78s6", "eistPhone", "089/394580347", false));
        Guest numerobis = new Guest(null);

        biergarten.visit(karlaKolumna);
        biergarten.visit(fredFrankenstein);
        biergarten.visit(numerobis);

        System.out.println("The following guests have been registered in the biergarten: ");
        for (GuestEntry guestEntry : biergarten.guests) {
            System.out.println(guestEntry);
        }
    }

    /**
     * Adds the guest's registration information to the list of GuestEntries of this biergarten.
     *
     * @param guest who wants to visit the biergarten
     */
    public void visit(Guest guest) {
        BiergartenPolicy biergartenPolicy = new BiergartenPolicy(guest.getPhone());
        biergartenPolicy.configure();
        biergartenPolicy.registrationClient.registerGuest(guest);

        // TODO: Enforce that the guest uses the appropriate registration option



        guests.add(guest.fillOutForm());
    }

}
