package com.example.jonaschrtest_002.Models

data class Student(val firstName: String, val lastName: String, val userName: String, val email:String,
                   val phoneNr: String, val citizenship:String, val last5yCountry: String, val birthCountry: String,
                   val motherTongue: String)

//2019-04-30: Data classes for use with the database call:
//  /api/findQuestionsByExerciseId/:exerciseId'
//  Generated at: https://www.json2kotlin.com/

data class Exercise (
    val id : Int,
    val subExamId : Int,
    val media : String,
    val headline : String,
    val mediaType : String,
    val examType : String,
    val focus : String,
    val examinatorInstructions : String,
    val studentInstructions : String,
    val time : String,
    val minutes : Int,
    val assessment : String,
    val passed : Int,
    val moduleNr : Double,
    val use : String,
    val access : String,
    val school : String,
    val createdAt : String,
    val updatedAt : String,
    val questions : List<Questions>
)

data class Questions (

    val id : Int,
    val exerciseId : Int,
    val question : String,
    val minPoints : Int,
    val maxPoints : Int,
    val guideLines : String,
    val media : String,
    val mediaType : String,
    val correctAnswer : String,
    val type : String,
    val createdAt : String,
    val updatedAt : String,
    val possibleAnswers : List<PossibleAnswers>
)

data class PossibleAnswers (

    val answerText : String
)


data class User (
    val name: String,
    val address: String
)