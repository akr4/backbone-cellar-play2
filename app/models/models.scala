package models

import play.api.db._
import play.api.Play.current
import play.api.libs.json._
import scala.slick.driver.MySQLDriver.simple._
import play.api.libs.json.JsObject

case class Wine(
  id: Option[Long],
  name: String,
  year: String,
  grapes: String,
  country: String,
  region: String,
  description: String,
  picture: String
)

object Wines extends Table[Wine]("wine") {
  def id = column[Long]("id", O PrimaryKey, O AutoInc)
  def name = column[String]("name", O NotNull)
  def year = column[String]("year", O NotNull)
  def grapes = column[String]("grapes", O NotNull)
  def country = column[String]("country", O NotNull)
  def region = column[String]("region", O NotNull)
  def description = column[String]("description", O NotNull)
  def picture = column[String]("picture", O NotNull)
  def * = id.? ~ name ~ year ~ grapes ~ country ~ region ~ description ~ picture <> (Wine, Wine.unapply _)
}

object WineRepository {

  implicit lazy val db = Database.forDataSource(DB.getDataSource())

  def findAll: Seq[Wine] = {
    db.withSession { implicit s: Session =>
      (for (w <- Wines) yield w.*).list
    }
  }

  def find(id: Long): Option[Wine] = {
    db.withSession { implicit s: Session =>
      (for (w <- Wines if w.id === id) yield w).firstOption
    }
  }

  def create(wine: Wine) {
    db.withSession { implicit s: Session =>
      Wines.insert(wine)
    }
  }

  def update(wine: Wine) {
    db.withSession { implicit s: Session =>
      (for (w <- Wines if w.id === wine.id) yield w.*).update(wine)
    }
  }

  def delete(id: Long) {
    db.withSession { implicit s: Session =>
      Wines.where(_.id === id).delete
    }
  }

  def findByName(name: String): Seq[Wine] = {
    db.withSession { implicit s: Session =>
      Wines.where(_.name === name).map(_.*).list
    }
  }
}