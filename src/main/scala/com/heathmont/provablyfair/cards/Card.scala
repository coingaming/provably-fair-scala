package com.heathmont.provablyfair.cards

import com.heathmont.provablyfair.cards.Rank._
import com.heathmont.provablyfair.cards.Suit._

case class Card(suit: Suit, rank: Rank) {
  def isAce: Boolean = rank == Ace

  override def toString = srToString

  def srToString = (suit, rank) match {
    case (Spades, Ace)   => "As"
    case (Spades, Two)   => "2s"
    case (Spades, Three) => "3s"
    case (Spades, Four)  => "4s"
    case (Spades, Five)  => "5s"
    case (Spades, Six)   => "6s"
    case (Spades, Seven) => "7s"
    case (Spades, Eight) => "8s"
    case (Spades, Nine)  => "9s"
    case (Spades, Ten)   => "10s"
    case (Spades, Jack)  => "Js"
    case (Spades, Queen) => "Qs"
    case (Spades, King)  => "Ks"

    case (Hearts, Ace)   => "Ah"
    case (Hearts, Two)   => "2h"
    case (Hearts, Three) => "3h"
    case (Hearts, Four)  => "4h"
    case (Hearts, Five)  => "5h"
    case (Hearts, Six)   => "6h"
    case (Hearts, Seven) => "7h"
    case (Hearts, Eight) => "8h"
    case (Hearts, Nine)  => "9h"
    case (Hearts, Ten)   => "10h"
    case (Hearts, Jack)  => "Jh"
    case (Hearts, Queen) => "Qh"
    case (Hearts, King)  => "Kh"

    case (Diamonds, Ace)   => "Ad"
    case (Diamonds, Two)   => "2d"
    case (Diamonds, Three) => "3d"
    case (Diamonds, Four)  => "4d"
    case (Diamonds, Five)  => "5d"
    case (Diamonds, Six)   => "6d"
    case (Diamonds, Seven) => "7d"
    case (Diamonds, Eight) => "8d"
    case (Diamonds, Nine)  => "9d"
    case (Diamonds, Ten)   => "10d"
    case (Diamonds, Jack)  => "Jd"
    case (Diamonds, Queen) => "Qd"
    case (Diamonds, King)  => "Kd"

    case (Clubs, Ace)   => "Ac"
    case (Clubs, Two)   => "2c"
    case (Clubs, Three) => "3c"
    case (Clubs, Four)  => "4c"
    case (Clubs, Five)  => "5c"
    case (Clubs, Six)   => "6c"
    case (Clubs, Seven) => "7c"
    case (Clubs, Eight) => "8c"
    case (Clubs, Nine)  => "9c"
    case (Clubs, Ten)   => "10c"
    case (Clubs, Jack)  => "Jc"
    case (Clubs, Queen) => "Qc"
    case (Clubs, King)  => "Kc"
  }
}

sealed trait Suit {
  def name: String
}

object Suit {
  case object Spades extends Suit {
    def name: String = "s"
  }

  case object Hearts extends Suit {
    def name: String = "h"
  }

  case object Diamonds extends Suit {
    def name: String = "d"
  }

  case object Clubs extends Suit {
    def name: String = "c"
  }
}


sealed trait Rank {
  def value: Int
}

object Rank {
  case object Ace extends Rank {
    def value: Int = 11
  }

  case object Two extends Rank {
    override val value: Int = 2
  }

  case object Three extends Rank {
    override val value: Int = 3
  }

  case object Four extends Rank {
    override val value: Int = 4
  }

  case object Five extends Rank {
    override val value: Int = 5
  }

  case object Six extends Rank {
    override val value: Int = 6
  }

  case object Seven extends Rank {
    override val value: Int = 7
  }

  case object Eight extends Rank {
    override val value: Int = 8
  }

  case object Nine extends Rank {
    override val value: Int = 9
  }

  case object Ten extends Rank {
    override val value: Int = 10
  }

  case object Jack extends Rank {
    override val value: Int = 10
  }

  case object Queen extends Rank {
    override val value: Int = 10
  }

  case object King extends Rank {
    override val value: Int = 10
  }
}


object Card {

  lazy val cards: Set[Card] = spades ++ hearts ++ diamonds ++ clubs
  lazy val value2Card: Map[String, Card] = cards.map(x => x.srToString.toUpperCase() -> x).toMap

  private val suits = List(Hearts, Diamonds, Clubs, Spades)

  private val ranks = List(Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King)

  private val spades = Set(
    Card(Spades, Ace), Card(Spades, Two), Card(Spades, Three), Card(Spades, Four), Card(Spades, Five), Card(Spades, Six),
    Card(Spades, Seven), Card(Spades, Eight), Card(Spades, Nine), Card(Spades, Ten), Card(Spades, Jack), Card(Spades, Queen),
    Card(Spades, King))

  private val hearts = Set(
    Card(Hearts, Ace), Card(Hearts, Two), Card(Hearts, Three), Card(Hearts, Four), Card(Hearts, Five), Card(Hearts, Six),
    Card(Hearts, Seven), Card(Hearts, Eight), Card(Hearts, Nine), Card(Hearts, Ten), Card(Hearts, Jack), Card(Hearts, Queen),
    Card(Hearts, King))

  private val diamonds = Set(
    Card(Diamonds, Ace), Card(Diamonds, Two), Card(Diamonds, Three), Card(Diamonds, Four), Card(Diamonds, Five), Card(Diamonds, Six),
    Card(Diamonds, Seven), Card(Diamonds, Eight), Card(Diamonds, Nine), Card(Diamonds, Ten), Card(Diamonds, Jack), Card(Diamonds, Queen),
    Card(Diamonds, King))

  private val clubs = Set(
    Card(Clubs, Ace), Card(Clubs, Two), Card(Clubs, Three), Card(Clubs, Four), Card(Clubs, Five), Card(Clubs, Six),
    Card(Clubs, Seven), Card(Clubs, Eight), Card(Clubs, Nine), Card(Clubs, Ten), Card(Clubs, Jack), Card(Clubs, Queen),
    Card(Clubs, King))

  def deck: List[Card] = for (s <- suits; r <- ranks) yield Card(s, r)

  def of(value: String): Card = ofOption(value).getOrElse(sys.error(s"No such card ${ value.toUpperCase } in $value2Card"))

  def ofOption(value: String): Option[Card] = value2Card.get(value.toUpperCase)

  def ofDecks(numberOfDecks: Int): List[Card] = List.fill(numberOfDecks)(Card.cards).flatten
}
