package com.epam.mentoring.creatures.duck;

import com.epam.mentoring.creatures.Flying;
import com.epam.mentoring.creatures.Swimming;
import com.epam.mentoring.location.NodeOfLabirynth;
import com.epam.mentoring.utils.HelpLocation;
import com.epam.mentoring.utils.PropertiesOfTheWorld.Direction;
import com.epam.mentoring.utils.PropertiesOfTheWorld.Element;

import java.util.logging.Level;

/**
 * Живая утка
 */
public class DuckLive extends DuckAbstract implements Flying, Swimming {

    protected static final int EAT_ENERGY = 1000;
    protected static final int DRINK_ENERGY = 1000;

    public DuckLive(NodeOfLabirynth currentNode) {
        super(currentNode);
        stepsPerNode = 1;
    }

    @Override
    public boolean fly(HelpLocation helpLocation, Direction direction) {
        if (!Element.Wind.equals(element)) {
            logger.log(Level.INFO, "Політ вимагає елементу \"Вітер\"");
            return false;
        }
        return move(helpLocation, direction);
    }

    @Override
    public boolean swim(HelpLocation helpLocation, Direction direction) {
        if (!Element.Water.equals(element)) {
            logger.log(Level.INFO, "Плавання вимагає елемента \"Вода\"");
            return false;
        }
        return move(helpLocation, direction);
    }

    /**
     * Едим с целью восстановления энергии
     *
     * @return
     */
    public boolean eat() {
        if (!Element.Earth.equals(element) && !Element.Wind.equals(element)) {
            logger.log(Level.INFO, "Харчування вимагає елемента Землі або Вітру");
            return false;
        }
        changeEnergy(EAT_ENERGY);
        return true;
    }

    /**
     * Пьём с целью восстановления энергии
     *
     * @return
     */
    public boolean drink() {
        if (!Element.Water.equals(element)) {
            logger.log(Level.INFO, "Пиття потребує елементу \"Вода\"");
            return false;
        }
        changeEnergy(DRINK_ENERGY);
        return true;
    }

    @Override
    protected boolean restoreEnergy() {
        boolean canRestoreEnergy = false;
        if (Element.Earth.equals(element) || Element.Wind.equals(element)) {
            canRestoreEnergy = eat();
        } else if (Element.Water.equals(element)) {
            canRestoreEnergy = drink();
        }
        if (canRestoreEnergy) {
            logger.log(Level.INFO, "Енергія успішно відновлена");
        } else {
            logger.log(Level.INFO, "Немає можливості відновити енергію");
        }
        return canRestoreEnergy;
    }

    @Override
    protected boolean canCompleteAction() {
        return true;
    }
}
