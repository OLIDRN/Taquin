/**
 * Ce logiciel est distribué à des fins éducatives.
 * <p>
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 * <p>
 * (c) 2022-2023 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package com.example.taquin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.beans.property.*;

import java.net.URL;

/**
 * La classe TaquinController propose un contrôleur permettant de gérer un jeu du Taquin
 * présenté à l'utilisateur sous la forme d'une interface graphique JavaFX.
 *
 * @author Romain Wallon
 * @version 0.1.0
 */
public class TaquinController {

    /**
     * Le label affichant le nombre de déplacements réalisés par l'utilisateur.
     */
    @FXML
    private Label nbMoves;

    /**
     * La grille affichant les boutons permettant de jouer au Taquin.
     */
    @FXML
    private GridPane gridPane;

    /**
     * Les boutons représentant les tuiles du jeu du Taquin.
     */
    private Button[][] buttons;


    /**
     * Le modèle du Taquin avec lequel ce contrôleur interagit.
     */
    private Taquin taquin;

    private javafx.scene.Scene scene;

    public void setScene(javafx.scene.Scene scene) {
        this.scene = scene;
        scene.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, e ->{
            KeyCode code = e.getCode();
            if(code == KeyCode.UP){
                taquin.pushUp();
            }
            else if(code == KeyCode.DOWN){
                taquin.pushDown();
            }
            else if(code == KeyCode.LEFT){
                taquin.pushLeft();
            }
            else if(code == KeyCode.RIGHT){
                taquin.pushRight();
            }
            e.consume();
        });
    }

    /**
     * Associe à ce contrôleur la partie du jeu du Taquin en cours.
     *
     * @param taquin La partie du Taquin avec laquelle interagir.
     */
    public void setModel(Taquin taquin) {
        this.taquin = taquin;
    }

    /**
     * Initialise la grille du Taquin affichée par ce contrôleur.
     *
     * @param grid La grille du jeu.
     */

    public void initGrid(Grid grid) {

        int gridSize = grid.size();
        buttons = new Button[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                extracted(grid, i, j);
            }
        }
    }

    private void extracted(Grid grid, int i, int j) {
        javafx.scene.control.Button button = new javafx.scene.control.Button();
        int tileValue = grid.getTile(i, j);
        button.setText(String.valueOf(tileValue));
        buttons[i][j] = button;
        gridPane.add(button, j, i);
        buttons[i][j].textProperty().bind(grid.get(i,j).getProperty().asString());
        buttons[i][j].visibleProperty().bind(grid.get(i,j).getProperty().isNotEqualTo(0));
        grid.get(i, j).getProperty().addListener(
                (p, o, n) -> buttons[i][j].setBackground(createBackground(n)));
        buttons[i][j].setPrefHeight(100);
        buttons[i][j].setPrefWidth(100);
        buttons[i][j].setOnAction(event -> {
            taquin.push(i, j);
        });
    }


    /**
     * Met à jour l'affichage du nombre de déplacements.
     *
     * @param nb Le nombre de déplacements.
     */
    public void updateMoves(IntegerProperty nb) {
        this.nbMoves.textProperty().bind(nb.asString());
    }

    /**
     * Prépare une nouvelle partie sur la vue.
     */
    public void startGame() {
        int size = buttons.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j].setDisable(false);
            }
        }
    }

    /**
     * Redémarre le jeu affiché sur la vue.
     */
    @FXML
    public void restart() {
        taquin.restartGame();
    }

    /**
     * Termine la partie en cours sur la vue.
     */
    public void endGame() {
        int size = buttons.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j].setDisable(true);
            }
        }
    }

    /**
     * Détermine l'arrière-plan de la tuile ayant l'indice donné.
     *
     * @param index L'indice de la tuile.
     *
     * @return L'arrière-plan associé à la tuile.
     */
    private Background createBackground(Number index) {
        String imageName = "iut-" + index;
        String imagePath = "/com/example/taquin/images/" + imageName + ".jpg";
        java.io.InputStream imageStream = getClass().getResourceAsStream(imagePath);

        if (imageStream != null) {
            Image image = new Image(imageStream, 100, 100, false, true);
            BackgroundImage backgroundImage = new BackgroundImage(
                    image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT
            );
            return new Background(backgroundImage);
        } else {
            System.err.println("Image not found: " + imageName);
            Image defaultImage = new Image(getClass().getResourceAsStream("/com/example/taquin/images/default.jpg"), 100, 100, false, true);
            BackgroundImage defaultBackgroundImage = new BackgroundImage(
                    defaultImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT
            );
            return new Background(defaultBackgroundImage);
        }
    }




}
