package com.epam.mentoring.creatures.duck;

import com.epam.mentoring.location.NodeOfLabirynth;
import com.epam.mentoring.utils.HelpLocation;
import com.epam.mentoring.utils.PropertiesOfTheWorld.Direction;
import com.epam.mentoring.utils.PropertiesOfTheWorld.Element;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DuckAbstract {

    protected static final int MIN_ENERGY = 0;
    protected static final int MAX_ENERGY = 1000;
    protected static final int ENERGY_TO_QUACK = -0;
    /**
     * Соответствие между стихиями и энергозатратами, необходимыми для перемещения в них
     */
    protected static final Map<Element, Integer> ENERGY_TO_MOVE = new HashMap<Element, Integer>() {{
        put(Element.Earth, -100);
        put(Element.Water, -100);
        put(Element.Wind, -100);
    }};

    protected int energy;
    protected int stepsPerNode;
    protected Element element;
    protected NodeOfLabirynth currentNode;
    protected Logger logger;

    protected abstract boolean restoreEnergy();

    protected abstract boolean canCompleteAction();

    protected DuckAbstract(NodeOfLabirynth currentNode) {
        energy = MAX_ENERGY;
        element = Element.Earth;
        this.currentNode = currentNode;
        logger = Logger.getLogger(this.getClass().getName());
    }

    public NodeOfLabirynth getCurrentNode() {
        return currentNode;
    }

    /**
     * Ходим, если это возможно
     *
     * @param direction
     * @return
     */
    public boolean walk(HelpLocation helpLocation, Direction direction) {
        if (canCompleteAction()) {
            if (!Element.Earth.equals(element)) {
                logger.log(Level.INFO, "Walking requires the Earth element");
                return false;
            }
            return move(helpLocation, direction);
        }
        return false;
    }

    /**
     * Крякаем, если это возможно
     *
     * @return
     */
    public boolean quack() {
        if (canCompleteAction()) {
            boolean hasEnoughEnergy = changeEnergy(ENERGY_TO_QUACK);
            if (hasEnoughEnergy) {
                logger.log(Level.INFO, "Кря");
            } else {
                logger.log(Level.INFO, "Не вистачає енергії для кряку");
            }
            return hasEnoughEnergy;
        }
        return false;
    }

    /**
     * Изменяем текущее значение энергии, если это возможно
     *
     * @param value значение, на которое энергия должна быть изменена
     *              отрицательные значения означают трату энергии
     * @return
     */
    protected boolean changeEnergy(int value) {
        if (value >= 0) {
            if (MAX_ENERGY - energy >= value) {
                energy += value;
            } else {
                energy = MAX_ENERGY;
            }
        } else {
            if (MIN_ENERGY - energy <= value) {
                energy += value;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Перемещаемся, если это возможно
     *
     * @param direction
     * @return
     */
    protected boolean move(HelpLocation helpLocation, Direction direction) {
        if (helpLocation.isDirectionMovable(currentNode, direction)) {
            if (!spendEnergyOnMoving(helpLocation, direction)) {
                logger.log(Level.INFO, "Не вистачає енергії для руху");
                if (restoreEnergy()) {
                    return spendEnergyOnMoving(helpLocation, direction);
                }
            }
            return true;
        } else {
            logger.log(Level.INFO, "Цей напрямок недоступний для переміщення");
        }
        return false;
    }

    /**
     * Тратим энергию на перемещение, если это возможно
     *
     * @param direction
     * @return
     */
    private boolean spendEnergyOnMoving(HelpLocation helpLocation, Direction direction) {
        if (changeEnergy(ENERGY_TO_MOVE.get(element) * stepsPerNode)) {
            currentNode = helpLocation.changeLocation(currentNode, direction);
            logger.log(Level.INFO, "Качка успішно перемыстилася. Нове розташування качки: " + helpLocation.getLocationInfo(currentNode));
            return true;
        }
        return false;
    }

}
