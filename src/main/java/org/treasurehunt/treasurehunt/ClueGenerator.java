package org.treasurehunt.treasurehunt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ClueGenerator {
    
    private List<String> mapRows = new ArrayList<>();
    
    public ClueGenerator() {

       mapRows.add(" |~ ~~ ~~~ ~ ~ ~~~ ~ _____.----------._ ~~~  ~~~~ ~~   ~~  ~~~~~ ~~~~|");
       mapRows.add(" |  _   ~~ ~~ __,---'_       \"         `. ~~~ _,--.  ~~~~ __,---.  ~~|");
       mapRows.add(" | | \\___ ~~ /      ( )   \"          \"   `-.,' (') \\~~ ~ (  / _\\ \\~~ |");
       mapRows.add(" |  \\    \\__/_   __(( _)_      (    \"   \"     (_\\_) \\___~ `-.___,'  ~|");
       mapRows.add(" |~~ \\     (  )_(__)_|( ))  \"   ))          \"   |    \"  \\ ~~ ~~~ _ ~~|");
       mapRows.add(" |  ~ \\__ (( _( (  ))  ) _)    ((             \" |   \"    \\_____,' | ~|");
       mapRows.add(" |~~ ~   \\  ( ))(_)(_)_)|  \"    ))         \" __,---._  \"  \"   \"  /~~~|");
       mapRows.add(" |    ~~~ |(_ _)| | |   |   \"  (   \"      ,-'~~~ ~~~ `-.   ___  /~ ~ |");
       mapRows.add(" | ~~     |  |  |   |   _,--- ,--. _  \"  (~~  ~~~~  ~~~ ) /___\\ \\~~ ~|");
       mapRows.add(" |  ~ ~~ /   |      _,----._,'`--'\\.`-._  `._~~_~__~_,-'  |H__|  \\ ~~|");
       mapRows.add(" |~~    / \"     _,-' / `\\ ,' / _'  \\`.---.._          __        \" \\~ |");
       mapRows.add(" | ~~~ / /   .-' , / ' _,'_  -  _ '- _`._ `.`-._    _/- `--.   \" \" \\~|");
       mapRows.add(" |  ~ / / _-- `---,~.-' __   --  _,---.  `-._   _,-'- / ` \\ \\_   \" |~|");
       mapRows.add(" | ~ | | -- _    /~/  `-_- _  _,' '  \\ \\_`-._,-'  / --   \\  - \\_   / |");
       mapRows.add(" |~~ | \\ -      /~~| \"     ,-'_ /-  `_ ._`._`-...._____...._,--'  /~~|");
       mapRows.add(" | ~~\\  \\_ /   /~~/    ___  `---  ---  - - ' ,--.     ___        |~ ~|");
       mapRows.add(" |~   \\      ,'~~|  \" (o o)   \"         \" \" |~~~ \\_,-' ~ `.     ,'~~ |");
       mapRows.add(" | ~~ ~|__,-'~~~~~\\    \\\"/      \"  \"   \"    /~ ~~   O ~ ~~`-.__/~ ~~~|");
       mapRows.add(" |~~~ ~~~  ~~~~~~~~`.______________________/ ~~~    |   ~~~ ~~ ~ ~~~~|");
       mapRows.add(" |____~jrei~__~_______~~_~____~~_____~~___~_~~___~\\_|_/ ~_____~___~__|");

       //we added rows from top down so now want to invert so that index zero is bottom
        Collections.reverse(mapRows);
    }
            


    public String getClue(int treasureX, int treasureY){
        //show a random 2 x 2 grid around the treasure location
        //start the grid randomly left and below the treasure so it's not too predictable
        //but don't miss the treasure or go off grid
        int leftX = Math.max(treasureX - ThreadLocalRandom.current().nextInt(0, 1),0);
        int bottomY = Math.max(treasureY - ThreadLocalRandom.current().nextInt(0, 1),0);
        while ((leftX+1)>3){
            leftX--;
        }
        while ((bottomY+1)>4){
            bottomY--;
        }

        //on each row take the subString from position leftX*5 to (leftX+1)*5 as columns 20-spaced
        //rows are 4-spaced so take them starting bottomY*4
        List<String> clueRows = new ArrayList<>();
        for( int i = (bottomY * 4) ; i < ((bottomY + 1) * 4); i++){
            clueRows.add(mapRows.get(i).substring(leftX*20,(leftX+1)*20));
        }

        //need to reverse back again so that we print top-down
        Collections.reverse(clueRows);
        StringBuilder clueBuilder = new StringBuilder();
        clueBuilder.append("<pre>");
        for(String row:clueRows){
            clueBuilder.append(row).append("<br/>");
        }
        clueBuilder.append("</pre>");
        clueBuilder.append("which is "+leftX).append(",").append(bottomY).append(" to ").append(leftX+1).append(",").append(bottomY+1);
        return clueBuilder.toString();
    }
}
