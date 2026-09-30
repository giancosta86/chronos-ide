package info.gianlucacosta.chronos.ide

import javafx.fxml.FXMLLoader
import javafx.scene.layout.Pane

import info.gianlucacosta.chronos.chronos_ide.{ArtifactInfo => IdeInfo}

import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, ButtonBar, ButtonType}


class AboutBox extends Alert(AlertType.None) {
  title = s"About ${IdeInfo.name}..."

  dialogPane().setContent({
    val loader = new FXMLLoader(App.getClass.getResource("AboutBox.fxml"))

    loader.load[Pane]
  })


  buttonTypes = Seq(
    new ButtonType("OK", ButtonBar.ButtonData.OKDone)
  )
}
