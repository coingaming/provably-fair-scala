package com.heathmont.provablyfair.cards

import java.math.BigInteger

import com.heathmont.provablyfair.ProvablyFairData.Separator
import com.heathmont.provablyfair.{MersenneTwisterFast, ProvablyFairData}
import com.roundeights.hasher.Algo

import scala.collection.mutable

case class CardProvablyFairData(
  initialData: String,
  dealerSeed: String)
  extends ProvablyFairData

object CardProvablyFairData {
  def generateHashedInitialDeck(initialDeckRaw: String, serverSeed: String): String = {
    val hashableValues = List(initialDeckRaw, Separator, serverSeed)
    hashSha256(hashableValues)
  }

  def shuffleFairly(playerSeed: String, cards: List[Card]): String = {

    def buildRNG(playerSeed: String): MersenneTwisterFast = {
      val lastElements = hashSha256(List(playerSeed)).takeRight(8)
      val seed = new BigInteger(lastElements, 16)
      new MersenneTwisterFast(seed.longValue())
    }

    val rng = buildRNG(playerSeed)
    val shuffled = fisherYatesShuffle(mutable.Seq(cards: _*), rng)

    CardEncoding.encodeCards(shuffled)
  }

  def hashSha256(values: List[String]) = {
    values.foldLeft(Algo.sha256.foldable) { (accum, nextSeed) => accum(nextSeed) }
      .done
      .hex
  }

  def fisherYatesShuffle(
    cards: scala.collection.mutable.Seq[Card],
    rnd: MersenneTwisterFast): List[Card] = {
    require(cards.nonEmpty)

    cards.indices.reverse.foreach { n =>
      val randomIdx = rnd.nextInt(n + 1)
      val tmp = cards(randomIdx)
      cards.update(randomIdx, cards(n))
      cards(n) = tmp
    }

    cards.toList
  }
}
