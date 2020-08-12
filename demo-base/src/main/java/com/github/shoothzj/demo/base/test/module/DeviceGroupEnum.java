package com.github.shoothzj.demo.base.test.module;

/**
 * @author hezhangjian
 */
public enum DeviceGroupEnum {

    Bleach(3),
    Fate(3),
    Naruto(1),
    OnePiece(1),
    Rezero(2);

    DeviceGroupEnum(int probability) {
        this.probability = probability;
    }

    public int probability;

}
