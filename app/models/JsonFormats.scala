package models

import play.api.libs.json._
import play.api.libs.json.JsObject
import play.api.libs.json.JsNumber

object JsonFormats {

  implicit object WineFormat extends Format[Wine] {
    def reads(json: JsValue): JsResult[Wine] = JsSuccess(Wine(
      (json \ "id").asOpt[Long],
      (json \ "name").as[String],
      (json \ "year").as[String],
      (json \ "grapes").as[String],
      (json \ "country").as[String],
      (json \ "region").as[String],
      (json \ "description").as[String],
      (json \ "picture").as[String]
    ))

    def writes(o: Wine): JsValue = JsObject(
      List(
        "id" -> o.id.map {
          JsNumber(_)
        }.get,
        "name" -> JsString(o.name),
        "year" -> JsString(o.year),
        "grapes" -> JsString(o.grapes),
        "country" -> JsString(o.country),
        "region" -> JsString(o.region),
        "description" -> JsString(o.description),
        "picture" -> JsString(o.picture)
      )
    )
  }

}
