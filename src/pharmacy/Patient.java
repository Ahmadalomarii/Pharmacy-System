package pharmacy;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author AHMAD
 */
public class Patient {

    int PhoneNumber;

    @Override
    public String toString() {
        return "Patient{" + "PhoneNumber=" + PhoneNumber + '}';
    }

    public Patient(int PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

}
