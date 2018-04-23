package org.treasurehunt.treasurehunt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ClueGenerator {
    
    private List<String> mapRows = new ArrayList<>();
    
    public ClueGenerator() {

        //the map without scales shown
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

        //show a 2 x 2 grid around the treasure location
        //start the grid randomly left and below the treasure so it's not too predictable
        //but don't miss the treasure or go off grid

        int leftX = Math.max(treasureX - ThreadLocalRandom.current().nextInt(0, 2),0);
        int bottomY = Math.max(treasureY - ThreadLocalRandom.current().nextInt(0, 2),0);

        while ( (leftX+1) > Graphics.xMax){
            leftX--;
        }
        while ((bottomY+1)> Graphics.yMax){
            bottomY--;
        }

        //on each row take the subString from position leftX to (leftX+1) and factor for scale
        //take rows starting bottomY*yScale
        List<String> clueRows = new ArrayList<>();

        for( int i = (bottomY * Graphics.yScale) ; i < ((bottomY + 1) * Graphics.yScale); i++){

            clueRows.add( mapRows.get(i).substring(leftX*Graphics.xScale, (leftX+1)*Graphics.xScale) );
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
