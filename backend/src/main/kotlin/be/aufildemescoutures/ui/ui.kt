package be.aufildemescoutures.ui

import be.aufildemescoutures.domain.live_tracking.LiveTrackingService
import be.aufildemescoutures.domain.sales.InventoryItem
import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.TemplateInstance
import kotlinx.datetime.*
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("ui")
class ui {
    @Inject
    @field:Default
    lateinit var liveTrackingService: LiveTrackingService

    @CheckedTemplate
    object Templates {
        @JvmStatic
        external fun controlPanel(videoId:String?,liveId:String):TemplateInstance
        @JvmStatic
        external fun reviewPanel(liveInventory:List<List<InventoryItem>>):TemplateInstance
    }

    @GET
    @Path("control_panel")
    @Produces(MediaType.TEXT_HTML)
    fun controlPanel()= Templates.controlPanel(this.liveTrackingService.videoId
        ,this.liveTrackingService.liveId?:("Live-"))

    @POST
    @Path("control_panel/stop_live")
    @Produces(MediaType.TEXT_PLAIN)
    fun stopLive():String {
        this.liveTrackingService.stopTracking()
        return "(${this.liveTrackingService.videoId},${this.liveTrackingService.liveId}) stopped"
    }

    @GET
    @Path("review_panel")
    @Produces(MediaType.TEXT_HTML)
    fun reviewPanel()=Templates.reviewPanel(inventorySplitBy10())

    private fun inventorySplitBy10() = (1..100)
        .map { InventoryItem(it) }
        .groupBy {it.sku.id/10 }
        .values.toList()
}
