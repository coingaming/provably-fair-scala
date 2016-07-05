package com.heathmont.provablyfair.cards

object CardEncoding {
  type CardValue = String
  type EncodedValue = String

  lazy val cards2Encoding: Map[CardValue, EncodedValue] = clubsEncoded ++ diamondsEncoded ++ heartsEncoded ++ spadesEncoded

  lazy val encoding2Cards: Map[EncodedValue, CardValue] = cards2Encoding.map(_.swap)

  private val clubsEncoded = Map(
    "Ac" -> "0", "2c" -> "1", "3c" -> "2", "4c" -> "3", "5c" -> "4", "6c" -> "5",
    "7c" -> "6", "8c" -> "7", "9c" -> "8", "10c" -> "9", "Jc" -> "a", "Qc" -> "b", "Kc" -> "c")

  private val diamondsEncoded = Map(
    "Ad" -> "d", "2d" -> "e", "3d" -> "f", "4d" -> "g", "5d" -> "h", "6d" -> "i",
    "7d" -> "j", "8d" -> "k", "9d" -> "l", "10d" -> "m", "Jd" -> "n", "Qd" -> "o", "Kd" -> "p")

  private val heartsEncoded = Map(
    "Ah" -> "q", "2h" -> "r", "3h" -> "s", "4h" -> "t", "5h" -> "u", "6h" -> "v",
    "7h" -> "w", "8h" -> "x", "9h" -> "y", "10h" -> "z", "Jh" -> "A", "Qh" -> "B", "Kh" -> "C")

  private val spadesEncoded = Map(
    "As" -> "D", "2s" -> "E", "3s" -> "F", "4s" -> "G", "5s" -> "H", "6s" -> "I", "7s" -> "J",
    "8s" -> "K", "9s" -> "L", "10s" -> "M", "Js" -> "N", "Qs" -> "O", "Ks" -> "P")

  def encodeCards(cards: List[Card]): String = {
    cards.flatMap(x => cards2Encoding.get(x.srToString)).mkString
  }

  def decodeCards(encoded: String): List[Card] = {
    encoded.flatMap(x => encoding2Cards.get(x.toString).map(Card.of)).toList
  }
}
