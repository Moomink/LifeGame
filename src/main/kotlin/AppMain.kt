import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage

class AppMain : Application() {

    override fun start(primaryStage: Stage) {
        primaryStage.title = "KotlinでJavaFX"
        val fxml = javaClass.getResource("fxml/hello.fxml")

        val root = FXMLLoader.load<Pane>(fxml)

        val scene = Scene(root)

        primaryStage.scene = scene

        primaryStage.show()

    }

}