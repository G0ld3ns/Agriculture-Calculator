import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Agricultural Investment Calculator");

        // Dropdown to select category
        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("Crops", "Animals", "Vehicles");
        categoryBox.setPromptText("Select a category");
        
        // Input fields
        TextField inputFieldCrops = new TextField();
        inputFieldCrops.setPromptText("Enter land size (Ha)");

        ComboBox<String> cropBox = new ComboBox<>();
        cropBox.getItems().addAll("Wheat", "Corn", "Canola");
        cropBox.setPromptText("Select a crop type");

        TextField inputFieldAnimals = new TextField();
        inputFieldAnimals.setPromptText("Enter number of animals");
        
        ComboBox<String> animalBox = new ComboBox<>();
        animalBox.getItems().addAll("Cow", "Sheep", "Pig");
        animalBox.setPromptText("Select animal type");

        TextField inputFieldVehicles = new TextField();
        inputFieldVehicles.setPromptText("Enter price of vehicle");

        TextField inputVehicleHa = new TextField();
        inputVehicleHa.setPromptText("Enter land size (Ha)");

        ComboBox<String> vehicleBox = new ComboBox<>();
        vehicleBox.getItems().addAll("Tractor", "Harvester", "Attachment");
        vehicleBox.setPromptText("Select vehicle type");
        
        Button calculateButton = new Button("Calculate");
        Label resultLabel = new Label("Results will appear here");
        
        // Containers for specific input fields
        VBox cropInputs = new VBox(10, cropBox, inputFieldCrops);
        VBox animalInputs = new VBox(10, animalBox, inputFieldAnimals );
        VBox vehicleInputs = new VBox(10, vehicleBox, inputVehicleHa, inputFieldVehicles);

        // Main layout (proper order)
        VBox layout = new VBox(10, label, categoryBox, cropInputs, animalInputs, vehicleInputs, calculateButton, resultLabel);

        // Initially hide all specific input areas but keep them in the layout
        cropInputs.setVisible(false);
        cropInputs.setManaged(false);

        animalInputs.setVisible(false);
        animalInputs.setManaged(false);

        vehicleInputs.setVisible(false);
        vehicleInputs.setManaged(false);

        // Show specific input fields based on the selected category
        categoryBox.setOnAction(e -> {
            String category = categoryBox.getValue();
        
            cropInputs.setVisible("Crops".equals(category));
            cropInputs.setManaged("Crops".equals(category));

            animalInputs.setVisible("Animals".equals(category));
            animalInputs.setManaged("Animals".equals(category));

            vehicleInputs.setVisible("Vehicles".equals(category));
            vehicleInputs.setManaged("Vehicles".equals(category));
        });

        // Calculation logic
        calculateButton.setOnAction(e -> {
            String category = categoryBox.getValue();
            double result = 0;
            double result2 = 0;

            try {
                if ("Crops".equals(category)) 
                {
                    double landSize = Double.parseDouble(inputFieldCrops.getText());
                    String cropType = cropBox.getValue();
                
                    if ("Corn".equals(cropType)) 
                    {
                        result = landSize * 7;
                        result2 = landSize * 0.25;
                       

                        String formattedCorn = String.format("%.2f", result);
                        String formattedCornSeed = String.format("%.2f", result2);

                        resultLabel.setText("Estimated tons of seeds needed for your fields: " + formattedCornSeed + " Tons\n" + "Estimated tons of crop output per year: " + formattedCorn);

                    } 
                    else if ("Wheat".equals(cropType)) 
                    {
                        result = landSize * 10;
                    } 
                    else if ("Canola".equals(cropType)) 
                    {
                        result = landSize * 3.5;
                    } 
                    else 
                    {
                        result = landSize * 2.5;
                    }
                }
                else if ("Animals".equals(category)) 
                {
                    double animalCount = Double.parseDouble(inputFieldAnimals.getText());
                    String animalType = animalBox.getValue();

                    if ("Cow".equals(animalType))
                    {
                        result = animalCount * 4.1;
                        result2 = animalCount * 0.2;
                        
                        String formattedHay = String.format("%.2f", result);
                        String formattedFlour = String.format("%.2f", result2);

                        resultLabel.setText("Estimated tons of hay needed per year: " + formattedHay + " Tons\n" + "Estimated tons of flour needed per year: " + formattedFlour + " Tons");
                    }
                    else if ("Sheep".equals(animalType))
                    {
                        result = animalCount * 0.9;
                        result2 = animalCount * 4;
                        double blocks = 0;
                        blocks = animalCount * 2;

                        String formattedGrass = String.format("%.2f", result);
                        String formattedSalt = String.format("%.2f", result2);

                        resultLabel.setText("Estimated tons of hay needed per year: " + formattedGrass + " Tons\n" + "Estimated kilograms of salt needed per year: " + formattedSalt + " Kg (Around " + blocks + " blocks)");
                    }
                    else if ("Pig".equals(animalType))
                    {
                        result = animalCount * 0.6;
                        resultLabel.setText("Estimated tons of food needed per year: " + result + " Tons");
                    }
                    
                }
                else if ("Vehicles".equals(category)) 
                {
                    double vehiclePrice = Double.parseDouble(inputFieldVehicles.getText());
                    result = vehiclePrice / 12; // Example: Payback in 12 months
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter valid numeric values.");
            }
        });

        Scene scene = new Scene(layout, 400, 300);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

        primaryStage.setTitle("Agricultural Investment Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//javac --module-path "C:/JavaFX/javafx-sdk-23.0.2/lib" --add-modules javafx.controls,javafx.fxml Main.java
//java --module-path "C:/JavaFX/javafx-sdk-23.0.2/lib" --add-modules javafx.controls,javafx.fxml Main
