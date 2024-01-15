package sio.gestionpermis.Model;

import java.util.ArrayList;

public class Eleve {
    private String nomEleve;
    private ArrayList<Test> lesTests;

    public Eleve(String nomEleve) {
        this.nomEleve = nomEleve;
        this.lesTests = new ArrayList<>();

    }

    public String getNomEleve() {
        return nomEleve;
    }

    public ArrayList<Test> getLesTests() {
        return lesTests;
    }

    public void ajouterTest(Test unTest) {lesTests.add(unTest);}

    public boolean verifierSiTestTermine(String nomTest) {
        boolean trouve = false;
        Test testTrouve = null;

        // Parcourir la liste des tests de l'élève
        for (Test test : lesTests) {
            if (test.getNomTest().equals(nomTest)) {
                // Si le test est trouvé, vérifier s'il est terminé
                testTrouve = test;
                trouve = true;
                break;
            }
        }

        if (trouve && testTrouve != null) {
            // Si le test est trouvé, vérifier s'il est terminé ou non
            if (testTrouve.isEstTermine()) {
                // Le test est terminé, le supprimer de la liste des tests de l'élève
                lesTests.remove(testTrouve);
            }
        }

        return trouve;
    }
}
