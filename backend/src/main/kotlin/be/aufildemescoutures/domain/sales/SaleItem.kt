package be.aufildemescoutures.domain.sales

import be.aufildemescoutures.domain.live_tracking.core.customer.Customer
import be.aufildemescoutures.domain.live_tracking.core.product.Sku
import kotlinx.datetime.Instant

data class SaleItem (val timestamp:Instant, val buyer: Customer, val sku: Sku, val status:SaleStatus=SaleStatus.SUCCESS)

enum class SaleStatus{SUCCESS,FAILED,CANCELLED,WAITING_LIST}
