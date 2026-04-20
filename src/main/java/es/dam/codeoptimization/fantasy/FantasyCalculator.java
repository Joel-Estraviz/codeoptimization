/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.dam.codeoptimization.fantasy;
import es.dam.codeoptimization.PlayerStats;

/**
 * THE CLASS YOU HAVE TO MODIFY
 * @author Joel Estraviz Nogueiro
 */
public class FantasyCalculator {

    // Method to calculate the points
    public static int calculatePoints(PlayerStats stats) {
        int puntosTotales = 0; 
        
        int minutes = stats.minutes;
        int goals = stats.goals;
        int assists = stats.assists;
        boolean yelowCard = stats.yellowCard;
        boolean redCard = stats.redCard;
        int paradas = stats.saves;
        int goalsAgainst = stats.goalsAgainst;
        char matchResult = stats.matchResult;
        String position = stats.position;

        final int MINUTESMIN = 0;
        final int MINUTESMAX = 60;
        
        // --- GOALKEEPER LOGIC ---
        if (position.equals("PORTERO")) {
            final int POINTS_NOT_FULL_GAME = 3;
            final int POINTS_FULL_GAME = 5;
            
            if (hasPlayedNotMinutesMax(minutes, MINUTESMIN, MINUTESMAX)) {
                puntosTotales = puntosTotales + POINTS_NOT_FULL_GAME;
            } else if (hasPlayedMinutesMax(minutes, MINUTESMAX)) {
                puntosTotales = puntosTotales + POINTS_FULL_GAME;
            }

            puntosTotales = getPointsGoals(goals, puntosTotales);

            puntosTotales = puntosTotales + (assists * 6);

            // 1 point per save
            puntosTotales = puntosTotales + paradas; 
            
            puntosTotales = calculatePointsGoalsAgainst(goalsAgainst, puntosTotales);

            if (yelowCard == true) puntosTotales = puntosTotales - 3; 
            if (redCard == true) puntosTotales = puntosTotales - 5;
            
            if (matchResult == 'G') {
                puntosTotales = puntosTotales + 5;
            } else if (matchResult == 'E') {
                puntosTotales = puntosTotales + 2;
            }

        // --- DEFENDER LOGIC ---
        } else if (position.equals("DEFENSA")) {
            if (hasPlayedNotMinutesMax(minutes, MINUTESMIN, MINUTESMAX)) {
                puntosTotales = puntosTotales + 3;
            } else if (hasPlayedMinutesMax(minutes, MINUTESMAX)) {
                puntosTotales = puntosTotales + 5;
            }

            for (int i = 0; i < goals; i++) {
                puntosTotales = puntosTotales + 5;
            }

            puntosTotales = puntosTotales + (assists * 6);

            puntosTotales = calculatePointsGoalsAgainst(goalsAgainst, puntosTotales);

            if (yelowCard == true) puntosTotales = puntosTotales - 3;
            if (redCard == true) puntosTotales = puntosTotales - 5;
            
            if (matchResult == 'G') {
                puntosTotales = puntosTotales + 5;
            } else if (matchResult == 'E') {
                puntosTotales = puntosTotales + 2;
            }

        // --- MIDFIELDER LOGIC ---
        } else if (position.equals("MEDIO")) {
            if (hasPlayedNotMinutesMax(minutes, MINUTESMIN, MINUTESMAX)) {
                puntosTotales = puntosTotales + 3;
            } else if (hasPlayedMinutesMax(minutes, MINUTESMAX)) {
                puntosTotales = puntosTotales + 5;
            }

            for (int i = 0; i < goals; i++) {
                puntosTotales = puntosTotales + 5;
            }

            puntosTotales = puntosTotales + (assists * 6);

            if (yelowCard == true) puntosTotales = puntosTotales - 3;
            if (redCard == true) puntosTotales = puntosTotales - 5;
            
            if (matchResult == 'G') {
                puntosTotales = puntosTotales + 5;
            } else if (matchResult == 'E') {
                puntosTotales = puntosTotales + 2;
            }

        // --- FORWARD LOGIC ---
        } else if (position.equals("DELANTERO")) {
            if (hasPlayedNotMinutesMax(minutes, MINUTESMIN, MINUTESMAX)) {
                puntosTotales = puntosTotales + 3;
            } else if (hasPlayedMinutesMax(minutes, MINUTESMAX)) {
                puntosTotales = puntosTotales + 5;
            }

            for (int i = 0; i < goals; i++) {
                puntosTotales = puntosTotales + 6;
            }

            puntosTotales = puntosTotales + (assists * 5);

            if (yelowCard == true) puntosTotales = puntosTotales - 3;
            if (redCard == true) puntosTotales = puntosTotales - 5;
            
            if (matchResult == 'G') {
                puntosTotales = puntosTotales + 5;
            } else if (matchResult == 'E') {
                puntosTotales = puntosTotales + 2;
            }
        }

        return puntosTotales;
    }

    private static int getPointsGoals(int goals, int puntosTotales) {
        final int POINTSEACHGOAL = 5;
        return puntosTotales + goals * POINTSEACHGOAL;
    }

    private static int calculatePointsGoalsAgainst(int goalsAgainst, int puntosTotales) {
        if (goalsAgainst == 0) {
            puntosTotales = puntosTotales + 5;
        } else if (goalsAgainst == 1) {
            puntosTotales = puntosTotales + 3;
        } else if (goalsAgainst == 2) {
            puntosTotales = puntosTotales + 1;
        }
        return puntosTotales;
    }

    private static boolean hasPlayedNotMinutesMax(int minutes, final int MINUTESMIN, final int MINUTESMAX) {
        return minutes > MINUTESMIN && minutes < MINUTESMAX;
    }

    private static boolean hasPlayedMinutesMax(int minutes, final int MINUTESMAX) {
        return minutes >= MINUTESMAX;
    }
}
