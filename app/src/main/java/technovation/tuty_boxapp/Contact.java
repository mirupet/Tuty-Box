package technovation.tuty_boxapp;

public class Contact {

    //private variables
    int id;
    String firstName, surname, description, phone;

    // Empty constructor
    public Contact(){
    }

    public Contact(String firstName, String surname, String description, String phone) {
        this.firstName = firstName;
        this.surname = surname;
        this.description = description;
        this.phone = phone;
    }

    // getting firstName
    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    // getting surname
    public String getSurname(){
        return this.surname;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }

    // getting description
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    // getting phone number
    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

}