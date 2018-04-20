package org.treasurehunt.treasurehunt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Treasure {

    @Value("${treasure.location.x:1}")
    private Integer x;

    @Value("${treasure.location.y:0}")
    private Integer y;

    // if we wanted boot to randomise if not supplied then we could do
    // ${treasure.location.y:${random.int(5)}} for y and would be random.int(4) for x

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
