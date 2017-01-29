package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

import doobie.imports._
import cats._, cats.data._, cats.implicits._
import fs2.interop.cats._
import playdoobie.{SettingRepository => Repo}

@Singleton
class SettingController @Inject()(t : Transactor[IOLite]) 
  extends Controller {


  def jsonOk(setting : String) =  Ok(setting).as("application/json")

  def listSettings(startString : Option[String]) = Action { implicit request =>
    jsonOk(Repo.listSettings(startString).map(_.asJson.noSpaces).transact(t).unsafePerformIO)
  }
  
  def getSettingByKey(key : String) = Action {
    val App = Applicative[ConnectionIO].compose[Option]
    App.map(Repo.getSetting(key))(_.asJson.noSpaces).transact(t).unsafePerformIO match {
      case Some(s) => jsonOk(s)
      case None => NotFound(s"setting with key $key not found")
    }
  }

}
