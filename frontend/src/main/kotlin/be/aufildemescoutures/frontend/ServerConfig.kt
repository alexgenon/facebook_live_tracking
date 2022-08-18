package be.aufildemescoutures.frontend

class ServerConfig(val serverUrl: String, val port:Int, val httpProtocol: String, val wsProtocol: String) {
    fun getFullHttpURL ()= "${this.httpProtocol}${this.serverUrl}:${this.port}"
    fun getFullWSURL()="${this.wsProtocol}${this.serverUrl}:${this.port}"
}
