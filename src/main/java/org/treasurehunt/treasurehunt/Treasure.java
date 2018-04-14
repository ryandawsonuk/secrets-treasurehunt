package org.treasurehunt.treasurehunt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Treasure {

    @Value("${treasure.location.x:0}")
    private Integer x;

    @Value("${treasure.location.y:0}")
    private Integer y;

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
