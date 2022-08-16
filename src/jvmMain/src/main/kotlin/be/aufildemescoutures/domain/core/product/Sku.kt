package be.aufildemescoutures.domain.core.product

data class Sku(val id:Int,val description: String, val price:Int){
    companion object {
        fun NotFoundSku(id:Int) = Sku(id,"",0)
    }
}
