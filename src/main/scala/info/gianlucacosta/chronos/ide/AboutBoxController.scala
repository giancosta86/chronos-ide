package info.gianlucacosta.chronos.ide

import javafx.fxml.FXML
import javafx.scene.control.Label

import info.gianlucacosta.chronos.chronos_ide.{ArtifactInfo => IdeInfo}
import info.gianlucacosta.chronos.{ArtifactInfo => Language}
import info.gianlucacosta.helios.desktop.DesktopUtils


class AboutBoxController {
  @FXML
  private var languageLabel: Label = _

  @FXML
  private var titleLabel: Label = _

  @FXML
  private var versionLabel: Label = _

  @FXML
  private var copyrightLabel: Label = _

  @FXML
  private var licenseLabel: Label = _

  @FXML
  private var additionalInfoLabel: Label = _


  def initialize {
    languageLabel.setText(s"${Language.name.toUpperCase} ${Language.version}")

    titleLabel.setText(IdeInfo.name)

    versionLabel.setText(s"Version ${IdeInfo.version}")

    copyrightLabel.setText(s"Copyright © ${IdeInfo.copyrightYears} Gianluca Costa.")

    licenseLabel.setText(
      "This software is released under the following license:\n"
        + "\n"
        + s"\t${IdeInfo.license}"
    )

    additionalInfoLabel.setText(
      "For further information, please refer to the LICENSE and README files."
    )
  }


  def showLanguageWebsite() {
    DesktopUtils.openBrowser(Language.website)
  }


  def showOmniEditorPage() {
    DesktopUtils.openBrowser("https://github.com/giancosta86/OmniEditor")
  }
}
