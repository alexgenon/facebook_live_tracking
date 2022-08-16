package be.aufildemescoutures.frontend

class ServerConfig(val serverUrl: String, val httpProtocol: String, val wsProtocol: String) {
    fun getFullHttpURL ()= this.httpProtocol + this.serverUrl
    fun getFullWsURL()=this.wsProtocol+this.serverUrl
}
