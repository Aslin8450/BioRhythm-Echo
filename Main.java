package com.biorhythm.echo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("==========================================");
        System.out.println("      BIO RHYTHM ECHO - HRI ANALYZER");
        System.out.println("==========================================");
        
        // Take user input with validation
        double heartRate = getValidInput(sc, "Enter Heart Rate (bpm): ", 30, 200);
        double breathRate = getValidInput(sc, "Enter Breath Rate (breaths/min): ", 5, 50);
        double noiseLevel = getValidInput(sc, "Enter Noise Level (dB): ", 0, 120);
        double lightLevel = getValidInput(sc, "Enter Light Level (lux): ", 0, 1000);

        // Show what values we're using (for debugging)
        System.out.println("\n🔍 USING THESE VALUES:");
        System.out.println("==========================================");
        System.out.printf("Heart Rate: %.2f bpm%n", heartRate);
        System.out.printf("Breath Rate: %.2f breaths/min%n", breathRate);
        System.out.printf("Noise Level: %.2f dB%n", noiseLevel);
        System.out.printf("Light Level: %.2f lux%n", lightLevel);

        // Calculate HRI
        double hri = HRIAlgorithm.calculateHRI(heartRate, breathRate, noiseLevel, lightLevel);

        // Show intermediate calculations (for debugging)
        System.out.println("\n🧮 CALCULATION DETAILS:");
        System.out.println("==========================================");
        showCalculationDetails(heartRate, breathRate, noiseLevel, lightLevel);

        // Show results with visual indicators
        System.out.println("\n📊 RESULTS:");
        System.out.println("==========================================");
        System.out.printf("Health Resonance Index (HRI): %.2f", hri);
        
        // Visual indicator
        System.out.print(" [");
        int bars = (int) (hri / 5);
        for (int i = 0; i < 20; i++) {
            if (i < bars) {
                if (hri >= 80) System.out.print("█");
                else if (hri >= 50) System.out.print("▓");
                else System.out.print("░");
            } else {
                System.out.print(" ");
            }
        }
        System.out.println("]");
        
        if (hri >= 80) System.out.println("Status: ✅ EXCELLENT RESONANCE");
        else if (hri >= 50) System.out.println("Status: ⚠️ MODERATE RESONANCE");
        else System.out.println("Status: ❌ LOW RESONANCE");
        
        System.out.println("\n💡 SUGGESTIONS:");
        System.out.println("==========================================");
        System.out.println(SuggestionsEngine.getSuggestion(hri));
        
        sc.close();
    }
    
    private static double getValidInput(Scanner sc, String prompt, double min, double max) {
        double value;
        while (true) {
            System.out.print(prompt);
            try {
                value = sc.nextDouble();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.printf("Please enter a value between %.1f and %.1f%n", min, max);
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear the invalid input
            }
        }
    }
    
    private static void showCalculationDetails(double heartRate, double breathRate, double noiseLevel, double lightLevel) {
        // Show how each component is calculated
        double heartScore = Math.max(0, Math.min(100, (70.0 / heartRate) * 100));
        double breathScore = Math.max(0, Math.min(100, (12.0 / breathRate) * 100));
        double noiseScore = Math.max(0, 100 - (noiseLevel / 100 * 100));
        
        double lightScore;
        if (lightLevel >= 300 && lightLevel <= 700) {
            lightScore = 100;
        } else if (lightLevel < 300) {
            lightScore = (lightLevel / 300) * 100;
        } else {
            lightScore = Math.max(0, 100 - ((lightLevel - 700) / 3));
        }
        
        System.out.printf("Heart Score: %.2f (from %.2f bpm)%n", heartScore, heartRate);
        System.out.printf("Breath Score: %.2f (from %.2f breaths/min)%n", breathScore, breathRate);
        System.out.printf("Noise Score: %.2f (from %.2f dB)%n", noiseScore, noiseLevel);
        System.out.printf("Light Score: %.2f (from %.2f lux)%n", lightScore, lightLevel);
        
        double weightedHRI = (heartScore * 0.3) + (breathScore * 0.3) + (noiseScore * 0.2) + (lightScore * 0.2);
        System.out.printf("Weighted HRI: %.2f%n", weightedHRI);
    }
}