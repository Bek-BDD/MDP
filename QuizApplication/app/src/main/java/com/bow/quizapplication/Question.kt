package com.bow.quizapplication

class Question{

    private lateinit var questionString: String;
    private lateinit var choice1: String;
    private lateinit var choice2: String;
    private lateinit var choice3: String;
    private lateinit var choice4: String;

    constructor(questionString:String, choice1:String,choice2:String,choice3:String,choice4:String){
        this.questionString=questionString;
        this.choice1=choice1;
        this.choice2=choice2;
        this.choice3=choice3;
        this.choice4=choice4;
    }

    public fun questionString():String{
        return this.questionString;
    }
    public fun setQuestionString(questionString: String){
        this.questionString=questionString;
    }
    public fun choice1 (): String{
        return this.choice1
    }
    public fun setChoice1(choice1: String){
        this.choice1=choice1;
    }
    public fun choice2 (): String{
        return this.choice2;
    }
    public fun setChoice2(choice2: String){
        this.choice2=choice2;
    }
    public fun choice3 (): String{
        return this.choice3;
    }
    public fun setChoice3(choice3: String){
        this.choice3=choice3;
    }
    public fun choice4(): String{
        return this.choice4;
    }
    public fun setChoice4(choice4: String){
        this.choice4=choice4;
    }



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Question

        if (questionString != other.questionString) return false
        if (choice1 != other.choice1) return false
        if (choice2 != other.choice2) return false
        if (choice3 != other.choice3) return false
        if (choice4 != other.choice4) return false

        return true
    }

    override fun hashCode(): Int {
        var result = questionString.hashCode()
        result = 31 * result + choice1.hashCode()
        result = 31 * result + choice2.hashCode()
        result = 31 * result + choice3.hashCode()
        result = 31 * result + choice4.hashCode()
        return result
    }


}