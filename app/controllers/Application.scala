package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index())
  }

  def javascriptRoutes = Action { implicit request =>
    Ok(
      Routes.javascriptRouter()(
        routes.javascript.WineApi.list,
        routes.javascript.WineApi.find,
        routes.javascript.WineApi.create,
        routes.javascript.WineApi.update,
        routes.javascript.WineApi.delete,
        routes.javascript.WineApi.search
      )).as("text/javascript")
  }

}