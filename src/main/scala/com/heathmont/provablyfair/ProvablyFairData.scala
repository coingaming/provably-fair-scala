package com.heathmont.provablyfair

import com.roundeights.hasher.Algo
import ProvablyFairData.Separator

trait ProvablyFairData {
  def initialData: String

  def dealerSeed: String

  def playerSeed: Option[String] = None

  def finalData: Option[String] = None

  protected def initialDataHashed: String = hashSha256(List(initialData, Separator, dealerSeed))

  private def hashSha256(values: List[String]) = {
    values.foldLeft(Algo.sha256.foldable) { (accum, nextSeed) => accum(nextSeed) }
      .done
      .hex
  }
}

object ProvablyFairData {
  val Separator: String = "|"
}
