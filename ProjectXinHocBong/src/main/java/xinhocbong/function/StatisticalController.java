package xinhocbong.function;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import xinhocbong.alertbox.StudentInformationAlert;
import xinhocbong.api.AllStudentInformationApi;
import xinhocbong.users.User;
import xinhocbong.users.UsersStatistical;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class StatisticalController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    //tạo bảng có tên là table_users
    @FXML
    private TableView<User> table_users;

    @FXML
    private TableColumn<User, String> col_BrithDate;

    @FXML
    private TableColumn<User, String> col_Mssv;

    @FXML
    private TableColumn<User, String> col_Name;
//    @FXML
//    private TableColumn<UsersStatistical, String> col_FamilySituation;

//    @FXML
//    private TableColumn<UsersStatistical, String> col_Gender;

//    @FXML
//    private TableColumn<UsersStatistical, Float> col_OverallScore;

    @FXML
    private TextField keyWordTextFieldStatistical;

    //    ObservableList<UsersStatistical> listM;
    ObservableList<User> dataList;
    int index = -1;
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;

    public void initialize(URL url, ResourceBundle rb) {
        col_Mssv.setCellValueFactory(new PropertyValueFactory<User, String>("mssv"));
        col_Name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        col_BrithDate.setCellValueFactory(new PropertyValueFactory<User, String>("brithDate"));
        //gọi đến hàm gọi API
        dataList = FXCollections.observableArrayList(AllStudentInformationApi.getDataAllUsers());

//        table_users.setItems(listM);
        table_users.setItems(dataList);
        searchUserStatistical();

// click chuột vào một học sinh sẽ gọi đến bảng AlertBox.fxml xuất ra thông tin học sinh
        table_users.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getClassLoader().getResource("AlertBox.fxml"));
                try {
                    Loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(StatisticalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                StudentInformationAlert alertBoxController = Loader.getController();
                if (table_users.getSelectionModel().getSelectedItem() != null) {
                    alertBoxController.setData(
                            table_users.getSelectionModel().getSelectedItem().getMssv(),
                            table_users.getSelectionModel().getSelectedItem().getName(),
                            table_users.getSelectionModel().getSelectedItem().getGender(),
                            table_users.getSelectionModel().getSelectedItem().getBrithDate(),
                            table_users.getSelectionModel().getSelectedItem().getOverallScore(),
                            table_users.getSelectionModel().getSelectedItem().getFamilySituation());
                    Parent p = Loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(p));
                    stage.show();
                }
            }
        });
    }

//    public void exitStatisticalActionn(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Menu.fxml"));
//        root = loader.load();
//        MenuController scene1Controller = loader.getController();
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

    //    quay trở lại Login.fxml
    public void exitStatisticalAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Login.fxml"));
        root = loader.load();
        LoginController scene1Controller = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//    @FXML
//    void removeStatistical(ActionEvent event) {
//        int selectedID = table_users.getSelectionModel().getSelectedIndex();
//        table_users.getItems().remove(selectedID);
//    }

    //  gọi đến bảng StudentsView.fxm để xuất ra tất cả học sinh
    public void infoAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("StudentsView.fxml"));
        root = loader.load();
        StudentsViewController scene1Controller = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //    gọi đến bảng ScholarStudentsView.fxml để xuất ra tất cả học sinh đã có thông tin học bổng
    public void infoActionRecivedSholar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ScholarStudentsView.fxml"));
        root = loader.load();
        ScholarStudentsViewController scene1Controller = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //    gọi đến AddStudent.fxml để thêm một học sinh
    @FXML
    public void addAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AddStudent.fxml"));
        root = loader.load();
        AddStudentController scene1Controller = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //    gọi đến AddStudent.fxml để thêm thông tin của một tổ chức
    @FXML
    public void addOrganizationAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AddOrganization.fxml"));
        root = loader.load();
        AddOrganizationController scene1Controller = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // thanh tìm kiếm (MSSV,NAME,BRITHDATE)
    @FXML
    private void searchUserStatistical() {
        FilteredList<User> filteredData = new FilteredList<>(dataList, b -> true);
        keyWordTextFieldStatistical.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (person.getMssv().toLowerCase(Locale.ROOT).contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getBrithDate().toLowerCase().contains(lowerCaseFilter))
                    return true;
                else
                    return false;
            });
        });
        SortedList<User> sortedList = new SortedList<>(filteredData);
        sortedList.comparatorProperty().bind(table_users.comparatorProperty());
        table_users.setItems(sortedList);


    }
}