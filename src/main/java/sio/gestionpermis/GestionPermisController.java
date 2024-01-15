package sio.gestionpermis;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import sio.gestionpermis.Model.Eleve;
import sio.gestionpermis.Model.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestionPermisController implements Initializable
{
    private ArrayList<Eleve> lesEleves;
    private Eleve eleve;
    @FXML
    private ComboBox cboTests;
    @FXML
    private TextField txtNomEleve;
    @FXML
    private Button btnInscription;
    @FXML
    private ComboBox cboEleves;
    @FXML
    private TableView<Test> tvTests;
    @FXML
    private CheckBox chkTermine;
    @FXML
    private Button btnModifier;
    @FXML
    private TableColumn tcNomTest;
    @FXML
    private TableColumn tcNbFautes;
    @FXML
    private TableColumn tcTermine;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        lesEleves = new ArrayList<>();
        initDatas();
        cboTests.getItems().addAll("Test n°1","Test n°2","Test n°3","Test n°4","Test n°5");
        cboTests.getSelectionModel().selectFirst();

        cboEleves.getItems().addAll("Enzo","Lilou","Noa");
        cboEleves.getSelectionModel().selectFirst();

        tcNbFautes.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                Test test = (Test) event.getTableView().getItems().get(event.getTablePosition().getRow());
                test.setNbFautes((int) event.getNewValue());
            }
        });

        // A chaque changement dans la CombBox des élèves : partie MODIFICATION
        cboEleves.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                if(newValue!=null)
                {
                    //Afficher les noms des tests de l'élève
                    tcNomTest.setCellValueFactory(new PropertyValueFactory<>("nomTest"));
                    //Afficher les fautes de l'élève
                    tcNbFautes.setCellValueFactory(new PropertyValueFactory<>("nbFautes"));
                    //Afficher la termine de l'élève
                    tcTermine.setCellValueFactory(new PropertyValueFactory<>("estTermine"));
                }
            }
        });

    }
    @FXML
    public void btnInscriptionClicked(Event event)
    {
        String nomEleve = txtNomEleve.getText();
        Eleve unEleve = rechercherEleve(nomEleve);
        if(unEleve!=null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("L'élève existe déjà");
            alert.setHeaderText("");
            alert.setContentText("Veuillez choisir un autre nom pour l'élève");
            alert.showAndWait();
        }
        else
        {
            Eleve nouvelEleve = new Eleve(nomEleve);
            lesEleves.add(nouvelEleve);
            cboEleves.getItems().add(nomEleve);
            cboEleves.getSelectionModel().selectLast();
        }
    }

    public void initDatas()
    {
        Eleve eleve1 = new Eleve("Enzo");
        Eleve eleve2 = new Eleve("Noa");
        Eleve eleve3 = new Eleve("Lilou");

        Test test1 = new Test("Test n°1",0,false);
        Test test2 = new Test("Test n°2",0,false);
        Test test3 = new Test("Test n°3",0,false);
        Test test4 = new Test("Test n°4",0,false);
        Test test5 = new Test("Test n°5",0,false);

        eleve1.ajouterTest(test1);eleve1.ajouterTest(test2);
        eleve2.ajouterTest(test3);
        eleve3.ajouterTest(test4);eleve3.ajouterTest(test5);

        lesEleves.add(eleve1);lesEleves.add(eleve2);lesEleves.add(eleve3);
    }

    // NE PAS MODIFIER CE CODE
    // Cette méthode permet de retrouver dans la liste
    // des élèves, l'élève dont le nom est passé en paramètre.
    // Soit on le trouve et dans ce cas on retourne l'objet
    // Soit on ne le trouve pas et dans ce cas on retourne null;
    public Eleve rechercherEleve(String nomEleve)
    {
        Eleve unEleve = null;
        for (Eleve eleve : lesEleves)
        {
            if(eleve.getNomEleve().equals(nomEleve))
            {
                unEleve = eleve;
                break;
            }
        }
        return unEleve;
    }

    @FXML
    public void btnModifierClicked(Event event)
    {

    }
}