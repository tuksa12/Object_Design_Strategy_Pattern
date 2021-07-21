package de.tum.in.ase.eist;

public class BiergartenPolicy {
    private Phone phone;
    RegistrationClient registrationClient;

    public BiergartenPolicy(Phone phone) {
        this.phone = phone;
    }

    public void configure() {
        if (phone.isAntiCovidAppInstalled()) {
            setRegistrationClient(new AntiCovidApp());
        } else {
            setRegistrationClient(new BiergartenRegistrationForm());
        }
    }

    public void setRegistrationClient(RegistrationClient registrationClient) {
        this.registrationClient = registrationClient;
    }
}
