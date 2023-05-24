/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 *
 * (c) 2022-2023 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package com.example.taquin;

import javafx.beans.property.*;

/**
 * La classe Tile représente une tuile de la grille du jeu du Taquin.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public class Tile {

    /**
     * La valeur de cette tuile.
     */
    private IntegerProperty value;

    /**
     * Construit une nouvelle instance de Tile.
     *
     * @param value La valeur initiale de la tuile.
     */
    public Tile(int value) {
        this.value = new SimpleIntegerProperty(value);
    }

    /**
     * Modifie la valeur de cette tuile.
     *
     * @param value La nouvelle valeur de la tuile.
     */
    public void setValue(int value) {
        this.value.set(value);
    }

    public IntegerProperty getProperty() {
        return this.value;
    }

    /**
     * Donne la valeur de cette tuile.
     *
     * @return La valeur de cette tuile.
     */
    public int getValue() {
        return this.value.get();
    }

    /**
     * Échange la valeur de cette tuile avec celle d'une tuile donnée.
     *
     * @param other La tuile avec laquelle échanger la valeur.
     */
    public void exchange(Tile other) {
        int tmp = other.getValue();
        other.setValue(getValue());
        this.setValue(tmp);
    }

}
