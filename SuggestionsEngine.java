package com.biorhythm.echo;

public class SuggestionsEngine {
    public static String getSuggestion(double hri) {
        if (hri >= 80) {
            return "✅ Excellent! You're in perfect harmony with your environment. \n" +
                   "Your biological rhythms are well-synchronized. Maintain this balance by:\n" +
                   "- Continuing your current routine\n" +
                   "- Taking regular breaks every hour\n" +
                   "- Staying hydrated";
        } else if (hri >= 65) {
            return "⚠️ Good resonance with minor imbalances. Suggestions:\n" +
                   "- Practice deep breathing for 2-3 minutes\n" +
                   "- Adjust lighting to reduce eye strain\n" +
                   "- Consider a short walk to refresh yourself";
        } else if (hri >= 50) {
            return "⚠️ Moderate imbalance detected. Recommendations:\n" +
                   "- Find a quieter space if possible\n" +
                   "- Practice 5 minutes of mindful breathing\n" +
                   "- Adjust your posture and stretch\n" +
                   "- Check if lighting is adequate for your task";
        } else {
            return "❌ Significant imbalance detected! Immediate actions recommended:\n" +
                   "- Move to a quieter environment\n" +
                   "- Practice 7-8-7 breathing (inhale 7s, hold 8s, exhale 7s)\n" +
                   "- Adjust lighting to reduce strain\n" +
                   "- Consider taking a 10-minute break to reset\n" +
                   "- Hydrate with water";
        }
    }
}