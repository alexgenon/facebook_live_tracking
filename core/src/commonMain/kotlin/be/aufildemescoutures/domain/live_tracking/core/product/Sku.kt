package be.aufildemescoutures.domain.live_tracking.core.product

data class Sku(val id:Int,val description: String, val price:Int){
    companion object {
        fun NotFoundSku(id:Int) = Sku(id,"",0)
    }
}
