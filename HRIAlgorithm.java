package com.biorhythm.echo;

public class HRIAlgorithm {
    public static double calculateHRI(double heartRate, double breathRate, double noiseLevel, double lightLevel) {
        // Normalize values with better formulas
        double heartScore = Math.max(0, Math.min(100, (70.0 / heartRate) * 100));
        double breathScore = Math.max(0, Math.min(100, (12.0 / breathRate) * 100));
        double noiseScore = Math.max(0, 100 - (noiseLevel / 100 * 100));
        double lightScore;
        
        // Ideal light is between 300-700 lux
        if (lightLevel >= 300 && lightLevel <= 700) {
            lightScore = 100;
        } else if (lightLevel < 300) {
            lightScore = (lightLevel / 300) * 100;
        } else {
            lightScore = Math.max(0, 100 - ((lightLevel - 700) / 3));
        }
        
        // Weighted average (heart and breath more important)
        return (heartScore * 0.3) + (breathScore * 0.3) + (noiseScore * 0.2) + (lightScore * 0.2);
    }
}