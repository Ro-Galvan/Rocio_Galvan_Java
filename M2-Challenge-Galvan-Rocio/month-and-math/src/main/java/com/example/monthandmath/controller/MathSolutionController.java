package com.example.monthandmath.controller;

import com.example.monthandmath.model.MathSolution;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class MathSolutionController {
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution addition(@RequestBody MathSolution mathSolution) {
//        Andrew Noorishad helped me with the if statement in office hours on Friday-10/14
        if (mathSolution.getOperand1() != null && mathSolution.getOperand2() != null) {
            mathSolution.setOperation("add");
            mathSolution.setAnswer(mathSolution.getOperand1() + mathSolution.getOperand2());
        } else {
            //    null should be entered as an operand, instead of an int, in postman or insomnia to generate 422 error
            throw new IllegalArgumentException("null not allowed as an input, integers only");
        } return new MathSolution(mathSolution.getOperand1(), mathSolution.getOperand2(), "add", mathSolution.getAnswer());
    }

//  SUBTRACT
    @RequestMapping(value = "/subtract", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution subtraction(@RequestBody MathSolution mathSolution) {
        if (mathSolution.getOperand1() != null && mathSolution.getOperand2() != null) {
            mathSolution.setOperation("subtract");
            mathSolution.setAnswer(mathSolution.getOperand1() - mathSolution.getOperand2());
        } else {
            //    null should be entered as an operand, instead of an int, in postman or insomnia to generate 422 error
            throw new IllegalArgumentException("null not allowed as an input, integers only");
        } return new MathSolution(mathSolution.getOperand1(), mathSolution.getOperand2(), "subtract", mathSolution.getAnswer());
    }

//    MULTIPLY
    @RequestMapping(value = "/multiply", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution multiplication(@RequestBody MathSolution mathSolution) {
        if (mathSolution.getOperand1() != null && mathSolution.getOperand2() != null) {
            mathSolution.setOperation("multiply");
            mathSolution.setAnswer(mathSolution.getOperand1() * mathSolution.getOperand2());
        } else {
            //    null should be entered as an operand, instead of an int, in postman or insomnia to generate 422 error
            throw new IllegalArgumentException("null not allowed as an input, integers only");
        } return new MathSolution(mathSolution.getOperand1(), mathSolution.getOperand2(), "multiply", mathSolution.getAnswer());
    }

//    DIVIDE
    @RequestMapping(value = "/divide", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution division(@RequestBody MathSolution mathSolution) {
        if (mathSolution.getOperand1() != null && mathSolution.getOperand2() != null) {
            mathSolution.setOperation("divide");
            mathSolution.setAnswer(mathSolution.getOperand1() / mathSolution.getOperand2());
        } else {
            //    null should be entered as an operand, instead of an int, in postman or insomnia to generate 422 error
            throw new IllegalArgumentException("null not allowed as an input, integers only");
        } return new MathSolution(mathSolution.getOperand1(), mathSolution.getOperand2(), "divide", mathSolution.getAnswer());
    }

}
