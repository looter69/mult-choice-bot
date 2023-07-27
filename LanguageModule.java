public class LanguageModule {
    public String debugReadFile1;
    public String debugReadFile2;
    public String errorReadFile;
    public String askChoice;
    public String correct;
    public String incorrect;
    public String quitting;
    public String errorSaveFile;
    public String success;
    public String infoAreaText;
    public String responseCorrect1;
    public String responseCorrect2;
    public String responseCorrect3;
    public String responseIncorrect;

    public LanguageModule(Language lang) {
        update(lang);
    }

    public void update(Language lang) {
        switch (lang) {
            case GERMAN:
                debugReadFile1 = "Frage in Zeile ";
                debugReadFile2 = " wurde übersprungen, da sie oft genug richtig beantwortet wurde!";
                errorReadFile = "Fehler beim Laden der Datei!";
                askChoice = "Gebe bitte die korrekte Antwort (1|2|3|4) | '0' schleißt das Programm.";
                correct = "Korrekt! Aktuelle Reihe: ";
                incorrect = "Nicht richtig. Richtige Antwort(en) ist/sind: ";
                quitting = "Beenden. Dein Highscore war: ";
                errorSaveFile = "Fehler beim Speichern der Datei";
                success = "Gratulation, alle Fragen wurden drei Mal richtig beantwortet!";
                infoAreaText = "Hier werden Infos angezeigt";
                responseCorrect1 = "✅ Korrekt ✅";
                responseCorrect2 = "Reihe: ";
                responseCorrect3 = "Highscore: ";
                responseIncorrect = "❌ Falsch. Die richtige(n) Antwort(en) ist/sind: ❌";
                break;
            default:
                debugReadFile1 = "Skipped Question in line ";
                debugReadFile2 = " because it has been answered correctly enough times already!";
                errorReadFile = "Error loading File!";
                askChoice = "Please give the correct Answer (1|2|3|4) | '0' quits the program.";
                correct = "Correct! Current streak: ";
                incorrect = "Not Correct. The correct answer(s) is/are: ";
                quitting = "Quitting. Your highscore was: ";
                errorSaveFile = "Error while saving content to file";
                success = "Concratulation, you answered each Question correctly three times!";
                infoAreaText = "Info will be displayed here";
                responseCorrect1 = "✅ Correct ✅";
                responseCorrect2 = "Streak: ";
                responseCorrect3 = "Highscore: ";
                responseIncorrect = "❌ Wrong. The correct answer(s) is/was: ❌";
                break;
        }
    }
}

enum Language {
    ENGLISH, GERMAN
}
