package be.aufildemescoutures.ui

import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.TemplateInstance
import java.awt.PageAttributes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("ui")
class ui {
    @CheckedTemplate
    object Templates {
        @JvmStatic
        external fun validateComments():TemplateInstance
    }

    @GET
    @Path("validation")
    @Produces(MediaType.TEXT_HTML)
    fun validationComments() = Templates.validateComments()
}