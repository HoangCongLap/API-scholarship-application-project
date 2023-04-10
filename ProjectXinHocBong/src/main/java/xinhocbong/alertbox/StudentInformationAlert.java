package xinhocbong.alertbox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class StudentInformationAlert {
    public TextFlow text;
    public Text textData;

    @FXML
    private void Ok(ActionEvent ae) {

        Stage stage = (Stage) text.getScene().getWindow();
        stage.close();
    }

    public void setData(String mssv, String name, String gender, String brithDate, float overallScore, String familySituation) {
        textData.setText("\n" + "   \tMSSV : " + mssv + "\n" + "   \tNAME : " + name + "\n" + "   \tGENDER: " + gender + "\n"
                + "   \tBRITHDATE : " + brithDate + "\n" + "   \tOVERALLSCORE : " + overallScore + "\n" + "   \tFAMILYSITUATUON : " + familySituation);

    }

}
