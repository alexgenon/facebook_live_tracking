package be.aufildemescoutures.domain.sales

import be.aufildemescoutures.domain.core.customer.Customer
import be.aufildemescoutures.domain.core.product.Sku
import java.time.LocalDateTime

data class SaleItem (val timestamp:LocalDateTime, val buyer: Customer, val sku: Sku, val status:SaleStatus=SaleStatus.SUCCESS)

enum class SaleStatus{SUCCESS,FAILED,CANCELLED,WAITING_LIST}