package com.bow.login_functions

class User {

    private lateinit var firstName : String;

    private lateinit var lastName : String;

    private lateinit var email : String;

    private lateinit var password : String;

    constructor(firstName: String, lastName: String, email: String, password: String) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public fun  firstName():String{
        return this.firstName;
    }
    public fun setFirstName(firstName:String){
        this.firstName=firstName;
    }

    public fun  lastName():String{
        return this.lastName;
    }
    public fun setLastName(lastName:String){
        this.lastName=lastName;
    }

    public fun  email():String{
        return this.email;
    }
    public fun setEmail(email:String){
        this.email=email;
    }
    public fun  password():String{
        return this.password;
    }
    public fun setPassword(password:String){
        this.password=password;
    }



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (email != other.email) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }

    override fun toString(): String {
        return "Users(firstName='$firstName', lastName='$lastName', email='$email', password='$password')"
    }


}