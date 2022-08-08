package be.aufildemescoutures.frontend.validation

import be.aufildemescoutures.frontend.ServerConfig
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.useState

external interface CommentsToValidateProps : Props {
    var serverConfig: ServerConfig
}

val CommentsToValidate = FC<CommentsToValidateProps> { props ->
    div {
      + "Will now try to connect to "
    }
}
