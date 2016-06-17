package com.heathmont.provablyfair.cards

import com.heathmont.provablyfair.ProvablyFairData

case class CardProvablyFairData(
  initialData: String,
  dealerSeed: String)
  extends ProvablyFairData

object CardProvablyFairData {
}
