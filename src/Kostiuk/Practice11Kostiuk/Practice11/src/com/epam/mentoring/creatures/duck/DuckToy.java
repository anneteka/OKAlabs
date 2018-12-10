package com.epam.mentoring.creatures.duck;

import com.epam.mentoring.location.NodeOfLabirynth;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Игрушечная утка на батарейках
 */
public class DuckToy extends DuckAbstract {

    protected static final int ENERGY_TO_FLITTER = -0;
    protected boolean isActive;

    public DuckToy(NodeOfLabirynth currentNode) {
        super(currentNode);
        logger = Logger.getLogger(this.getClass().getName());
        isActive = true;
        stepsPerNode = 2;
    }

    /**
     * Махаем крыльями
     *
     * @return
     */
    public boolean flitter() {
        boolean canSpendEnergy = changeEnergy(ENERGY_TO_FLITTER);
        if (canSpendEnergy) {
            logger.log(Level.INFO, "Duck successfully flittered");
        } else {
            logger.log(Level.INFO, "Not enough energy for flittering");
        }
        return changeEnergy(ENERGY_TO_FLITTER);
    }

    /**
     * Меняем батарейки с целью восстановления энергии
     */
    public void changeBattery() {
        changeEnergy(MAX_ENERGY);
    }

    @Override
    protected boolean restoreEnergy() {
        logger.log(Level.INFO, "Немає можливості відновити енергію");
        for (int i = 0; i < 5; i++) {
            quack();
        }
        logger.log(Level.INFO, "Duck turned off");
        isActive = false;
        return isActive;
    }

    @Override
    protected boolean canCompleteAction() {
        return isActive;
    }
}
