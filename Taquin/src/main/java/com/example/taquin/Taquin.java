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
 * La classe Taquin fournit toutes les méthodes nécessaires pour gérer une partie du jeu
 * du Taquin.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public class Taquin {

    /**
     * La grille sur laquelle le jeu se déroule.
     */
    private Grid grid;

    /**
     * Le nombre de déplacements réalisés sur la grille.
     */
    private final IntegerProperty nbMoves;

    /**
     * Le contrôleur de l'application, permettant de maintenir l'affichage à jour.
     */
    private TaquinController controller;

    /**
     * Construit une nouvelle instance du jeu du Taquin.
     *
     * @param size La taille de la grille sur laquelle le jeu se déroule.
     */
    public Taquin(int size) {
        this.nbMoves = new SimpleIntegerProperty(0);
        this.grid = new Grid(4);
    }

    /**
     * Donne la taille de la grille sur laquelle le jeu se déroule.
     *
     * @return La taille de la grille, en nombre de cases par côté.
     */
    public int size() {
        return grid.size();
    }

    /**
     * Spécifie le contrôleur de l'application, permettant de maintenir l'affichage à
     * jour.
     *
     * @param controller Le contrôleur avec lequel interagir.
     */
    public void setController(TaquinController controller) {
        this.controller = controller;
        controller.initGrid(grid);
    }

    /**
     * Démarre une nouvelle partie.
     */
    public void startGame() {
        grid.shuffle();
        controller.updateMoves(nbMoves);
        controller.startGame();
    }

    /**
     * Pousse la case à la position donnée dans l'emplacement vide.
     *
     * @param row La ligne de la case à pousser.
     * @param column La colonne de la case à pousser.
     */
    public void push(int row, int column) {
        if (grid.push(row, column)) {
            acceptMove();
        }
    }

    /**
     * Pousse la case située sous l'emplacement vide dans cet emplacement.
     */
    public void pushUp() {
        if (grid.pushUp()) {
            acceptMove();
        }
    }

    /**
     * Pousse la case située à gauche de l'emplacement vide dans cet emplacement.
     */
    public void pushRight() {
        if (grid.pushRight()) {
            acceptMove();
        }
    }

    /**
     * Pousse la case située au dessus de l'emplacement vide dans cet emplacement.
     */
    public void pushDown() {
        if (grid.pushDown()) {
            acceptMove();
        }
    }

    /**
     * Pousse la case située à droite de l'emplacement vide dans cet emplacement.
     */
    public void pushLeft() {
        if (grid.pushLeft()) {
            acceptMove();
        }
    }

    /**
     * Valide le dernier déplacement demandé par l'utilisateur.
     */
    private void acceptMove() {
        nbMoves.set(nbMoves.get() + 1);

        controller.updateMoves(nbMoves);

        if (grid.isOrdered()) {
            controller.endGame();
        }
    }

    /**
     * Redémarre une nouvelle partie.
     */
    public void restartGame() {
        grid.reset();
        nbMoves.set(0);
        startGame();
    }
}
