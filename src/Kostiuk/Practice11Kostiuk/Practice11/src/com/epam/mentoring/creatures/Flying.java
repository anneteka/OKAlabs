package com.epam.mentoring.creatures;

import com.epam.mentoring.utils.HelpLocation;
import com.epam.mentoring.utils.PropertiesOfTheWorld.Direction;

public interface Flying {

    public boolean fly(HelpLocation helpLocation, Direction direction);

}
