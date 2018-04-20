package org.treasurehunt.treasurehunt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TreasurehuntController {

    @Value("${treasurehunt.max.attempts:3}")
    private Integer maxAttempts;

    // use concurrent package to avoid cheating by simultaneous requests
    private AtomicInteger attemptsMade = new AtomicInteger(0);

    @Autowired
    private Treasure treasure;

    private ClueGenerator clueGenerator = new ClueGenerator();

    @GetMapping
    public String treasure(@RequestParam(required=true) Integer x, @RequestParam(required=true) Integer y){

        //hit treasure and not already dead
        if(x == treasure.getX() && y == treasure.getY() && attemptsMade.intValue() < maxAttempts ){

            return Graphics.treasure;
        }

        int attemptsLeft = maxAttempts - attemptsMade.incrementAndGet();

        if (attemptsLeft > 0) {
            return String.format(Graphics.missed, attemptsLeft) ;
        } else {
            return Graphics.died;
        }
    }


    @GetMapping(value = "")
    public String home(){
        String homePage = Graphics.map+"<br/>";

        homePage+="Your clue is:<br/><br/>"+clueGenerator.getClue(treasure.getX(),treasure.getY());

        homePage+="<br/><br/><br/>Play by going to e.g. /treasure?x=1&y=1 ";
        return homePage;
    }

    @GetMapping(value="reset")
    public String reset(){
        attemptsMade.set(0);
        return home();
    }

    @GetMapping(value="cheat")
    public String cheat(){
        return "("+treasure.getX()+","+treasure.getY()+")";
    }

}
