package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json.toJson

import models._
import models.JsonFormats._


object WineApi extends Controller {

  def list = Action {
    Ok(toJson(WineRepository.findAll))
  }

  def find(id: Long) = Action {
    WineRepository.find(id).map { x => Ok(toJson(x)) }.getOrElse(NotFound)
  }

  def create() = Action(parse.json) { request =>
    WineRepository.create(request.body.as[Wine])
    Created
  }

  def update(id: Long) = Action(parse.json) { request =>
    val wine = request.body.as[Wine]
    WineRepository.update(wine.copy(id = Some(id)))
    Ok
  }

  def delete(id: Long) = Action {
    WineRepository.delete(id)
    Ok
  }

  def search(name: String) = Action {
     Ok(toJson(WineRepository.findByName(name)))
  }

}
