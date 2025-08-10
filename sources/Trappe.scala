package myfunlaby

import com.funlabyrinthe.core.*
import com.funlabyrinthe.mazes.*
import com.funlabyrinthe.mazes.std.*

object Trappe extends Module:
  override protected def createComponents()(using Universe): Unit =
    val magicalButton1 = new MagicalButton1
    val antiMagicalButton1 = new AntiMagicalButton1
    val magicalButton2 = new MagicalButton2
    val antiMagicalButton2 = new AntiMagicalButton2
    val buttonGoldenBlock = new ButtonGoldenBlock
  end createComponents
  
  def magicalButton1(using Universe): MagicalButton1 =
    myComponentByID("magicalButton1")
  def antiMagicalButton1(using Universe): AntiMagicalButton1 =
    myComponentByID("antiMagicalButton1")
  def magicalButton2(using Universe): MagicalButton2 =
    myComponentByID("magicalButton2")
  def antiMagicalButton2(using Universe): AntiMagicalButton2 =
    myComponentByID("antiMagicalButton2")
  def buttonGoldenBlock(using Universe): ButtonGoldenBlock =
    myComponentByID("buttonGoldenBlock")
end Trappe

export Trappe.*

class MagicalButton1(using ComponentInit) extends PushButton:
  override def buttonDown(context: MoveContext): Unit = {
    import context.*

    pos.map(11, 11, 1) += eastArrow
    enabled = false
    antiMagicalButton1.enabled = true
  }
end MagicalButton1

class AntiMagicalButton1(using ComponentInit) extends PushButton:
  enabled = false
  
  override def buttonDown(context: MoveContext): Unit = {
    import context.*

    pos.map(11, 11, 1) += northArrow
    enabled = false
    magicalButton1.enabled = true
  }
end AntiMagicalButton1

class MagicalButton2(using ComponentInit) extends PushButton:
  override def buttonDown(context: MoveContext): Unit = {
    import context.*

    pos.map(1, 8, 0) += noEffect
    enabled = false
    antiMagicalButton2.enabled = true
  }
end MagicalButton2

class AntiMagicalButton2(using ComponentInit) extends PushButton:
  enabled = false
  
  override def buttonDown(context: MoveContext): Unit = {
    import context.*

    pos.map(1, 8, 0) += northArrow
    enabled = false
    magicalButton2.enabled = true
  }
end AntiMagicalButton2

class ButtonGoldenBlock(using ComponentInit) extends PushButton:
  override def buttonDown(context: MoveContext): Unit = {
    import context.*

    pos.map(11, 10, 2) += goldenBlock
    pos.map(10, 13, 2) += noEffect
    pos.map(10, 11, 2) += crossroads

    player.showMessage(
      "La mauvaise nouvelle, c'est que ce bouton a replacé le bloc en or. "
        + "L'autre mauvaise nouvelle, c'est qu'il n'a pas replacé la clé d'or."
    )

    enabled = false
  }
end ButtonGoldenBlock