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
    public static int calcP(PlayerStats s) {
        int r = 0; 
        int punts = 0;
        
        int minutes = s.minutes;
        int goals = s.goals;
        int assists = s.assists;
        boolean yeloowCard = s.yellowCard;
        boolean redCard = s.redCard;
        int paradas = s.saves;
        int goalsAgainst = s.goalsAgainst;
        char matchResult = s.matchResult;
        String position = s.position;

        // --- GOALKEEPER LOGIC ---
        if (position.equals("PORTERO")) {
            if (minutes > 0 && minutes < 60) {
                r = r + 3;
            } else if (minutes >= 60) {
                r = r + 5;
            }

            for (int i = 0; i < goals; i++) {
                r = r + 5;
            }

            r = r + (assists * 6);

            // 1 point per save
            r = r + paradas; 
            
            if (goalsAgainst == 0) {
                r = r + 5; 
            } else if (goalsAgainst == 1) {
                r = r + 3;
            } else if (goalsAgainst == 2) {
                r = r + 1;
            }

            if (yeloowCard == true) r = r - 3; 
            if (redCard == true) r = r - 5;
            
            if (matchResult == 'G') {
                r = r + 5;
            } else if (matchResult == 'E') {
                r = r + 2;
            }

        // --- DEFENDER LOGIC ---
        } else if (position.equals("DEFENSA")) {
            if (minutes > 0 && minutes < 60) {
                r = r + 3;
            } else if (minutes >= 60) {
                r = r + 5;
            }

            for (int i = 0; i < goals; i++) {
                r = r + 5;
            }

            r = r + (assists * 6);

            if (goalsAgainst == 0) {
                r = r + 5; 
            } else if (goalsAgainst == 1) {
                r = r + 3;
            } else if (goalsAgainst == 2) {
                r = r + 1;
            }

            if (yeloowCard == true) r = r - 3;
            if (redCard == true) r = r - 5;
            
            if (matchResult == 'G') {
                r = r + 5;
            } else if (matchResult == 'E') {
                r = r + 2;
            }

        // --- MIDFIELDER LOGIC ---
        } else if (position.equals("MEDIO")) {
            if (minutes > 0 && minutes < 60) {
                r = r + 3;
            } else if (minutes >= 60) {
                r = r + 5;
            }

            for (int i = 0; i < goals; i++) {
                r = r + 5;
            }

            r = r + (assists * 6);

            if (yeloowCard == true) r = r - 3;
            if (redCard == true) r = r - 5;
            
            if (matchResult == 'G') {
                r = r + 5;
            } else if (matchResult == 'E') {
                r = r + 2;
            }

        // --- FORWARD LOGIC ---
        } else if (position.equals("DELANTERO")) {
            if (minutes > 0 && minutes < 60) {
                r = r + 3;
            } else if (minutes >= 60) {
                r = r + 5;
            }

            for (int i = 0; i < goals; i++) {
                r = r + 6;
            }

            r = r + (assists * 5);

            if (yeloowCard == true) r = r - 3;
            if (redCard == true) r = r - 5;
            
            if (matchResult == 'G') {
                r = r + 5;
            } else if (matchResult == 'E') {
                r = r + 2;
            }
        }

        return r;
    }
}
